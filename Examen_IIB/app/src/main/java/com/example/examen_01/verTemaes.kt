package com.example.examen_01

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.example.examen_01.Asignatura.Companion.idAsignaturaSeleccionado
import com.example.examen_01.db.DbAsignatura
import com.example.examen_01.db.DbTema
import com.google.firebase.firestore.FirebaseFirestore

class verTemaes : AppCompatActivity() {

    private var temasList = mutableListOf<DbTema>()
    private lateinit var listView_tema: ListView

    companion object {
        private const val TAG = "verTemaes"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_temaes)

        listView_tema = findViewById(R.id.listView_tema)
        fetchTemasFromFirestore()

        listView_tema.setOnItemClickListener { _, _, position, _ ->
            val tema = temasList[position]
            //... (show context menu with options to edit or delete)
        }
    }

    private fun fetchTemasFromFirestore() {
        val db = FirebaseFirestore.getInstance()
        db.collection("Temas")
            .whereEqualTo("fkAsignatura", idAsignaturaSeleccionado)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    val tema = document.toObject(DbTema::class.java)
                    temasList.add(tema)
                }
                // Set up the ListView adapter
                setupListViewAdapter()
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents: ", exception)
            }
    }

    private fun setupListViewAdapter() {
        val temasStringsList = temasList.map { it.nombreTema }
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            temasStringsList
        )
        listView_tema.adapter = adapter
    }

    // ... (other methods and class logic)
}
