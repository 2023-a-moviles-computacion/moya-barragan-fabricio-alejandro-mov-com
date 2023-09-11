package com.example.examen_01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.example.examen_01.db.DbTema

class Tema : AppCompatActivity() {
    var idItemSeleccionado = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tema)

        val nombre = findViewById<EditText>(R.id.editText_tema)
        nombre.requestFocus()
        val autor = findViewById<EditText>(R.id.editText_autor)
        val calificacion = findViewById<EditText>(R.id.editText_calificacion)
        val fecha = findViewById<EditText>(R.id.editText_fecha)
        val idAsignatura = findViewById<EditText>(R.id.editText_idasignatura)

        val btnInsertar = findViewById<Button>(R.id.btn_insert)
        btnInsertar.setOnClickListener {
            val tema: DbTema = DbTema(
                null,
                nombre.text.toString(),
                autor.text.toString(),
                calificacion.text.toString(),
                fecha.text.toString(),
                idAsignatura.text.toString().toInt()
            )
            val resultado = tema.insertTema()

            if (resultado) {
                Toast.makeText(this, "REGISTRO GUARDADO", Toast.LENGTH_LONG).show()
                cleanEditText()
            } else {
                Toast.makeText(this, "ERROR AL INSERTAR REGISTRO", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun cleanEditText() {
        val nombre = findViewById<EditText>(R.id.editText_tema)
        nombre.setText("")
        val autor = findViewById<EditText>(R.id.editText_autor)
        autor.setText("")
        val calificacion = findViewById<EditText>(R.id.editText_calificacion)
        calificacion.setText("")
        val fecha = findViewById<EditText>(R.id.editText_fecha)
        fecha.setText("")
        val idAsignatura = findViewById<EditText>(R.id.editText_idasignatura)
        idAsignatura.setText("")
        nombre.requestFocus()
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menutema, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idItemSeleccionado = id
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mi_editar_asig -> {
                "${idItemSeleccionado}"
                true
            }
            R.id.mi_eliminar_asig -> {
                "${idItemSeleccionado}"
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }
}
