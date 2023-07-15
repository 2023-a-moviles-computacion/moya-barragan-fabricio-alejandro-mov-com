package com.example.examen_01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.examen_01.db.DbTema

class ActualizarTema : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_tema)

        val idTema = verTemaes.idTemaSeleccionada
        var tema = DbTema(null, "", "", "", "", 0, this)
        tema = tema.getTemaById(idTema)

        var id = findViewById<EditText>(R.id.et_upd_idTema)
        id.setText(tema.getidTema().toString())
        var nombre = findViewById<EditText>(R.id.et_upd_nombTema)
        nombre.setText(tema.getnombreTema())
        var autor = findViewById<EditText>(R.id.et_upd_autorTema)
        autor.setText(tema.getautorTema())
        var calificacion = findViewById<EditText>(R.id.et_upd_califTema)
        calificacion.setText(tema.getcalificacion())
        var fecha = findViewById<EditText>(R.id.et_upd_fechaTema)
        fecha.setText(tema.getfechaPublicacion())
        var fk = findViewById<EditText>(R.id.et_upd_idAlbTema)
        fk.setText(tema.getidAsignatura().toString())

        val btn_actualizar_tema = findViewById<Button>(R.id.btn_upd_Tema)
        btn_actualizar_tema.setOnClickListener {
            tema.setnombreTema(nombre.text.toString())
            tema.setautorTema(autor.text.toString())
            tema.setcalificacion(calificacion.text.toString())
            tema.setfechaPublicacion(fecha.text.toString())
            tema.setidAsignatura(fk.text.toString().toInt())
            val resultado = tema.updateTema()

            if (resultado > 0) {
                Toast.makeText(this, "REGISTRO ACTUALIZADO", Toast.LENGTH_LONG).show()
                cleanEditText()
            } else {
                Toast.makeText(this, "ERROR AL ACTUALIZAR REGISTRO", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun cleanEditText() {
        var id = findViewById<EditText>(R.id.et_upd_idTema)
        id.setText("")
        var nombre = findViewById<EditText>(R.id.et_upd_nombTema)
        nombre.setText("")
        var autor = findViewById<EditText>(R.id.et_upd_autorTema)
        autor.setText("")
        var calificacion = findViewById<EditText>(R.id.et_upd_califTema)
        calificacion.setText("")
        var fecha = findViewById<EditText>(R.id.et_upd_fechaTema)
        fecha.setText("")
        var fk = findViewById<EditText>(R.id.et_upd_idAlbTema)
        fk.setText("")
        id.requestFocus()
    }
}