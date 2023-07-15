package com.example.examen_01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.examen_01.db.DbAsignatura

class ActualizarAsignatura : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_asignatura)

        val idAsignatura = Asignatura.idAsignaturaSeleccionado
        var asignatura = DbAsignatura(null, "", "", "", "", this)
        asignatura = asignatura.getAsignaturaById(idAsignatura)

        var id = findViewById<EditText>(R.id.et_upd_idAsi)
        id.setText(asignatura.getidAsignatura().toString())
        var nombre = findViewById<EditText>(R.id.et_upd_nombAsi)
        nombre.setText(asignatura.getnombreAsignatura())
        var autor = findViewById<EditText>(R.id.et_upd_autorAsi)
        autor.setText(asignatura.getautorAsignatura())
        var fecha = findViewById<EditText>(R.id.et_upd_fechaAsi)
        fecha.setText(asignatura.getfechaPublicacion())
        var estado = findViewById<EditText>(R.id.et_upd_estadoAsi)
        estado.setText(asignatura.getestadoFavorito())

        val btn_actualizarAsignatura = findViewById<Button>(R.id.btn_upd_Asignatura)
        btn_actualizarAsignatura.setOnClickListener {
            // Asignatura actualizado
            asignatura.setnombreAsignatura(nombre.text.toString())
            asignatura.setdocenteAsignatura(autor.text.toString())
            asignatura.setfechaInicio(fecha.text.toString())
            asignatura.setestadoFavorito(estado.text.toString())
            val resultado = asignatura.updateAsignatura()

            if (resultado > 0) {
                Toast.makeText(this, "REGISTRO ACTUALIZADO", Toast.LENGTH_LONG).show()
                cleanEditText()
            } else {
                Toast.makeText(this, "ERROR AL ACTUALIZAR REGISTRO", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun cleanEditText() {
        val id = findViewById<EditText>(R.id.et_upd_idAsi)
        id.setText("")
        val nombre = findViewById<EditText>(R.id.et_upd_nombAsi)
        nombre.setText("")
        val autor = findViewById<EditText>(R.id.et_upd_autorAsi)
        autor.setText("")
        val fecha = findViewById<EditText>(R.id.et_upd_fechaAsi)
        fecha.setText("")
        val estado = findViewById<EditText>(R.id.et_upd_estadoAsi)
        estado.setText("")
        id.requestFocus()
    }

}