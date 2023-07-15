package com.example.movilescomp2023

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AlertDialog

class BListView : AppCompatActivity() {
    var arreglo = BBaseDatosMemoria.arregloBEntrenador
    var idItemSelecionado = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blist_view)

        val listVew = findViewById<ListView>(R.id.lv_list_view)
        val adaptador = ArrayAdapter(
            this, //Contexto
            android.R.layout.simple_list_item_1, //como se va a ver (XML)
            arreglo
        )
        listVew.adapter = adaptador
        adaptador.notifyDataSetChanged()

        registerForContextMenu(listVew)

    }


    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        //llenamos las opciones dle menu

        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        //obtener el id del arrayListselecionado
        val info =menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idItemSelecionado = id

    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.mi_editar -> {
                "${idItemSelecionado}"
                return true
            }
            R.id.mi_eliminar -> {
                "${idItemSelecionado}"
                abrirDialogo()
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }
    fun abrirDialogo(){
        val builder =AlertDialog.Builder(this)
        builder.setTitle("Desea eliminar")
        builder.setPositiveButton("Aceptar",
            DialogInterface.OnClickListener { dialog, which ->
                //al aceptar eliminar el registro
            }
        )
        builder.setNegativeButton(
            "Cancelar",
            null
        )
        val opciones = resources.getStringArray(
            R.array.string_array_opciones_dialogo
        )
        val selecionPrevia = booleanArrayOf(
            true, //lunes seleccionado
            false, //martes no selecionado
            false, //miercoles no selecionado
        )
        builder.setMultiChoiceItems(
            opciones,
            selecionPrevia,
            {dialog,
             which,
             isChecked ->
                "Dio clic en el item ${which}"
            }
        )
        val dialogo = builder.create()
        dialogo.show()
    }


    fun anadirEntrenador(
        adaptador: ArrayAdapter<BEntrenador>
    ){
        arreglo.add(
            BEntrenador(
                1,
                "Elisa",
                "Descripcion"
            )
        )
        adaptador.notifyDataSetChanged()
    }
}