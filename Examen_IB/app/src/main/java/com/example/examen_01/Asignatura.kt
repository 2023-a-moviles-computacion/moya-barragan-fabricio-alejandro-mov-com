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

class Asignatura : AppCompatActivity() {
    companion object{
        var idAsignaturaSeleccionado = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_asignatura)
        showListViewAsignatura()

        val nombre = findViewById<EditText>(R.id.editText_nombreAsi)
        nombre.requestFocus()
        val autor = findViewById<EditText>(R.id.editText_autorAsi)
        val fecha = findViewById<EditText>(R.id.editText_fechaAsi)
        val estadoFav = findViewById<EditText>(R.id.editText_estadoAsi)

        val btnInsertar = findViewById<Button>(R.id.btn_insertarAsi)
        btnInsertar.setOnClickListener {
            val asignatura = DbAsignatura(
                null,
                nombre.text.toString(),
                autor.text.toString(),
                fecha.text.toString(),
                estadoFav.text.toString(),
                this
            )
            val resultado = asignatura.insertAsignatura()

            if (resultado > 0) {
                Toast.makeText(this, "REGISTRO GUARDADO", Toast.LENGTH_LONG).show()
                cleanEditText()
                showListViewAsignatura()
            } else {
                Toast.makeText(this, "ERROR EN INSERTAR REGISTRO", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun cleanEditText() {
        val nombre = findViewById<EditText>(R.id.editText_nombreAsi)
        nombre.setText("")
        val autor = findViewById<EditText>(R.id.editText_autorAsi)
        autor.setText("")
        val fecha = findViewById<EditText>(R.id.editText_fechaAsi)
        fecha.setText("")
        val estado = findViewById<EditText>(R.id.editText_estadoAsi)
        estado.setText("")
        nombre.requestFocus()
    }

    fun showListViewAsignatura() {
        // ListView Temaes
        val asignatura = DbAsignatura(null, "", "", "", "", this)
        val listView = findViewById<ListView>(R.id.listview_asignatura)
        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            asignatura.showAsignaturaes()
        )
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()
        registerForContextMenu(listView)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        // Llenamos las opciones del menu
        val inflater = menuInflater
        inflater.inflate(R.menu.menuasignatura, menu)
        // Obtener el id del ArrayListSeleccionado
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idAsignaturaSeleccionado = id
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mi_editar_asig -> {
                irActividad(ActualizarAsignatura::class.java)
                return true
            }
            R.id.mi_eliminar_asig -> {
                abrirDialogo()
                return true
            }
            R.id.mi_vertemaes -> {
                irActividad(verTemaes::class.java)
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun abrirDialogo() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("¿Desea eliminar esta Asignatura?")
        builder.setPositiveButton(
            "SI",
            DialogInterface.OnClickListener { dialog, which ->
                val asignatura = DbAsignatura(null, "", "", "", "", this)
                val resultado = asignatura.deleteAsignatura(idAsignaturaSeleccionado)
                if (resultado > 0) {
                    Toast.makeText(this, "REGISTRO ELIMINADO", Toast.LENGTH_LONG).show()
                    showListViewAsignatura()
                } else {
                    Toast.makeText(this, "ERROR AL ELIMINAR REGISTRO", Toast.LENGTH_LONG).show()
                }
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