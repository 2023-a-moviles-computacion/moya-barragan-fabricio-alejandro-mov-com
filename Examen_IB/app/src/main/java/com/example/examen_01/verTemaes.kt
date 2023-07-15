package com.example.examen_01

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.example.examen_01.db.DbAsignatura
import com.example.examen_01.db.DbTema

class verTemaes : AppCompatActivity() {
    companion object {
        var idTemaSeleccionada = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_temaes)
        val idAsignatura = Asignatura.idAsignaturaSeleccionado
        var asignaturaAux = DbAsignatura(null, "", "", "", "", this)

        val textViewAutor = findViewById<TextView>(R.id.tv_asignaturaVerTemaes)
        textViewAutor.text = "Asignatura: "+ asignaturaAux.getAsignaturaById(idAsignatura).getnombreAsignatura()

        val btnCrearTema = findViewById<Button>(R.id.btn_crearTema)
        btnCrearTema.setOnClickListener {
            irActividad(Tema::class.java)
        }
        showListView(idAsignatura)
    }

    fun showListView(id: Int) {
        // ListView Temaes
        val objetoTema = DbTema(null, "", "", "", "", 0, this)
        val listViewTemaes = findViewById<ListView>(R.id.listView_tema)
        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            objetoTema.showTemaes(id)
        )
        listViewTemaes.adapter = adaptador
        adaptador.notifyDataSetChanged()
        registerForContextMenu(listViewTemaes)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        // Llenamos las opciones del menu
        val inflater = menuInflater
        inflater.inflate(R.menu.menutema, menu)
        // Obtener el id del ArrayListSeleccionado
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idTemaSeleccionada = id
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mi_editar_asig -> {
                irActividad(ActualizarTema::class.java)
                return true
            }
            R.id.mi_eliminar_asig -> {
                abrirDialogo()
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun abrirDialogo() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("¿Desea eliminar este tema?")
        builder.setPositiveButton(
            "SI",
            DialogInterface.OnClickListener { dialog, which ->
                val tema = DbTema(null, "", "", "", "", 0, this)
                val resultado = tema.deleteTema(idTemaSeleccionada)
                if (resultado > 0) {
                    Toast.makeText(this, "REGISTRO ELIMINADO", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "ERROR AL ELIMINAR REGISTRO", Toast.LENGTH_LONG).show()
                }
                val idAsignatura = Asignatura.idAsignaturaSeleccionado
                showListView(idAsignatura)
            }
        )
        builder.setNegativeButton(
            "NO",
            null
        )

        val dialogo = builder.create()
        dialogo.show()
    }

    fun irActividad(
        clase: Class<*>
    ) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }


}