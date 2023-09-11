package com.example.examen_01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.examen_01.Asignatura.Companion.idAsignaturaSeleccionado
import com.example.examen_01.db.DbAsignatura


// ... (imports and other codes)

class ActualizarAsignatura : AppCompatActivity() {

    // ... (other codes)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_asignatura)

        val btn_actualizarAsignatura: Button = findViewById(R.id.btn_upd_Asignatura)
        val et_nombreAsignatura: EditText = findViewById(R.id.et_upd_nombAsi)
        val et_autorAsignatura: EditText = findViewById(R.id.et_upd_autorAsi)
        val et_fechaPublicacion: EditText = findViewById(R.id.et_upd_fechaAsi)
        val et_estadoFavorito: EditText = findViewById(R.id.et_upd_estadoAsi)

        btn_actualizarAsignatura.setOnClickListener {
            val asignatura = DbAsignatura(
                null,
                et_nombreAsignatura.text.toString(),
                et_autorAsignatura.text.toString(),
                et_fechaPublicacion.text.toString(),
                et_estadoFavorito.text.toString()
            )

            if (asignatura.updateAsignatura(idAsignaturaSeleccionado.toString())) {
                Toast.makeText(this, "Asignatura actualizada", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Error al actualizar", Toast.LENGTH_SHORT).show()
            }
        }

        // ... (otros códigos)
    }



    // ... (other codes)
}
