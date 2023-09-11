package com.example.examen_01

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.examen_01.db.DbAsignatura
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    private lateinit var lv_asignaturas: ListView
    private val asignaturaList: MutableList<DbAsignatura> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lv_asignaturas = findViewById(R.id.lv_asignaturas)

        fetchAsignaturasFromFirestore()

        lv_asignaturas.setOnItemClickListener { _, _, position, _ ->
            val asignatura = asignaturaList[position]
            Toast.makeText(this, "Seleccionaste: ${asignatura.nombreAsignatura}", Toast.LENGTH_SHORT).show()
            // Puedes agregar más acciones aquí, como abrir una nueva actividad
        }
    }

    private fun fetchAsignaturasFromFirestore() {
        val db = FirebaseFirestore.getInstance()
        db.collection("Asignaturas")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    val asignatura = document.toObject(DbAsignatura::class.java)
                    asignaturaList.add(asignatura)
                }
                // Update the ListView adapter
                val adapter = ArrayAdapter(
                    this,
                    android.R.layout.simple_list_item_1,
                    asignaturaList.map { it.nombreAsignatura }
                )
                lv_asignaturas.adapter = adapter
            }
            .addOnFailureListener { exception ->
                Log.w("MainActivity", "Error getting documents: ", exception)
            }
    }
}
