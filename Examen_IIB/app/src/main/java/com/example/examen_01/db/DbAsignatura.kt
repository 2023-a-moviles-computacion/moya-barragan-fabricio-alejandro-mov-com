package com.example.examen_01.db

import com.google.firebase.firestore.FirebaseFirestore

class DbAsignatura(
    var idAsignatura: Int?,
    var nombreAsignatura: String,
    var autorAsignatura: String,
    var fechaPublicacion: String,
    var estadoFavorito: String
) {
    private val db = FirebaseFirestore.getInstance()
    private val collection = db.collection("Asignaturas")

    fun insertAsignatura(): Boolean {
        val asignatura = hashMapOf(
            "nombreAsignatura" to nombreAsignatura,
            "autorAsignatura" to autorAsignatura,
            "fechaPublicacion" to fechaPublicacion,
            "estadoFavorito" to estadoFavorito
        )
        collection.add(asignatura)
        return true
    }

    fun getAsignaturaById(id: String): DbAsignatura? {
        var asignatura: DbAsignatura? = null
        collection.document(id).get().addOnSuccessListener { document ->
            if (document != null) {
                asignatura = document.toObject(DbAsignatura::class.java)
            }
        }
        return asignatura
    }

    fun updateAsignatura(id: String): Boolean {
        val asignatura = hashMapOf(
            "nombreAsignatura" to nombreAsignatura,
            "autorAsignatura" to autorAsignatura,
            "fechaPublicacion" to fechaPublicacion,
            "estadoFavorito" to estadoFavorito
        )
        collection.document(id).set(asignatura)
        return true
    }

    fun deleteAsignatura(id: String): Boolean {
        collection.document(id).delete()
        return true
    }

    // ... (otros métodos y lógica de clase)
}
