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

    companion object {
        var idAsignaturaSeleccionado = 0
    }

    // Declaración de vistas
    private lateinit var btnInsertar: Button
    private lateinit var editText_nombreAsi: EditText
    private lateinit var editText_autorAsi: EditText
    private lateinit var editText_fechaAsi: EditText
    private lateinit var editText_estadoAsi: EditText
    private lateinit var listView_asignatura: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_asignatura)

        // Inicialización de vistas
        btnInsertar = findViewById(R.id.btn_insertarAsi)
        editText_nombreAsi = findViewById(R.id.editText_nombreAsi)
        editText_autorAsi = findViewById(R.id.editText_autorAsi)
        editText_fechaAsi = findViewById(R.id.editText_fechaAsi)
        editText_estadoAsi = findViewById(R.id.editText_estadoAsi)
        listView_asignatura = findViewById(R.id.listview_asignatura)

        btnInsertar.setOnClickListener {
            val asignatura = DbAsignatura(
                null,
                editText_nombreAsi.text.toString(),
                editText_autorAsi.text.toString(),
                editText_fechaAsi.text.toString(),
                editText_estadoAsi.text.toString()
            )
            val resultado = asignatura.insertAsignatura()

            if (resultado) {
                Toast.makeText(this, "REGISTRO GUARDADO", Toast.LENGTH_LONG).show()
                cleanEditText()
                showListViewAsignatura()
            } else {
                Toast.makeText(this, "ERROR EN INSERTAR REGISTRO", Toast.LENGTH_LONG).show()
            }
        }

        showListViewAsignatura()
        registerForContextMenu(listView_asignatura)
    }

    fun cleanEditText() {
        editText_nombreAsi.setText("")
        editText_autorAsi.setText("")
        editText_fechaAsi.setText("")
        editText_estadoAsi.setText("")
        editText_nombreAsi.requestFocus()
    }

    fun showListViewAsignatura() {
        // Aquí necesitas implementar la lógica para obtener la lista de asignaturas y mostrarla en el ListView.
        // Por ahora, solo he mantenido esta función vacía.
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menuasignatura, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        idAsignaturaSeleccionado = info.position
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mi_editar_asig -> {
                irActividad(ActualizarAsignatura::class.java)
                true
            }
            R.id.mi_eliminar_asig -> {
                abrirDialogo()
                true
            }
            R.id.mi_vertemaes -> {
                irActividad(verTemaes::class.java)
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun abrirDialogo() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("¿Desea eliminar esta Asignatura?")
        builder.setPositiveButton("SI") { _, _ ->
            val asignatura = DbAsignatura(null, "", "", "", "")
            if (asignatura.deleteAsignatura(idAsignaturaSeleccionado.toString())) {
                Toast.makeText(this, "REGISTRO ELIMINADO", Toast.LENGTH_LONG).show()
                showListViewAsignatura()
            } else {
                Toast.makeText(this, "ERROR AL ELIMINAR REGISTRO", Toast.LENGTH_LONG).show()
            }
        }
        builder.setNegativeButton("NO", null)

        val dialogo = builder.create()
        dialogo.show()
    }

    fun irActividad(clase: Class<*>) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}
