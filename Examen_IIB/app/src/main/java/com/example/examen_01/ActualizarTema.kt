package com.example.examen_01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.examen_01.db.DbTema

class ActualizarTema : AppCompatActivity() {

    // Declaración de vistas y variables
    private lateinit var btn_actualizar_tema: Button
    private lateinit var et_nombreTema: EditText
    private lateinit var et_autorTema: EditText
    private lateinit var et_calificacion: EditText
    private lateinit var et_fechaPublicacion: EditText
    private lateinit var et_fkAsignatura: EditText
    private var idTemaSeleccionada: Int = -1  // Valor por defecto para identificar si no se ha establecido

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_tema)

        // Inicialización de vistas
        btn_actualizar_tema = findViewById(R.id.btn_upd_Tema)
        et_nombreTema = findViewById(R.id.et_upd_nombTema)
        et_autorTema = findViewById(R.id.et_upd_autorTema)
        et_calificacion = findViewById(R.id.et_upd_califTema)
        et_fechaPublicacion = findViewById(R.id.et_upd_fechaTema)
        et_fkAsignatura = findViewById(R.id.et_upd_idAlbTema)

        // Obtener el ID del tema seleccionado, suponiendo que lo pasas a través de un Intent con la clave "ID_TEMA"
        idTemaSeleccionada = intent.getIntExtra("ID_TEMA", -1)

        // Evento de clic para el botón de actualizar
        btn_actualizar_tema.setOnClickListener {
            val tema = DbTema(
                null,
                et_nombreTema.text.toString(),
                et_autorTema.text.toString(),
                et_calificacion.text.toString(),  // No convertimos a Double ya que en DbTema es String
                et_fechaPublicacion.text.toString(),
                et_fkAsignatura.text.toString().toInt()
            )

            if (tema.updateTema(idTemaSeleccionada.toString())) {
                Toast.makeText(this, "Tema actualizado", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Error al actualizar", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
