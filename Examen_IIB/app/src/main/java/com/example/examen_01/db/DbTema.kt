
package com.example.examen_01.db

import com.google.firebase.firestore.FirebaseFirestore

class DbTema(
    var idTema: Int?,
    var nombreTema: String,
    var autorTema: String,
    var calificacion: String,
    var fechaPublicacion: String,
    var fkAsignatura: Int?
) {
    private val db = FirebaseFirestore.getInstance()
    private val collection = db.collection("Temas")

    fun insertTema(): Boolean {
        // Create a new document with data
        val tema = hashMapOf(
            "nombreTema" to nombreTema,
            "autorTema" to autorTema,
            "calificacion" to calificacion,
            "fechaPublicacion" to fechaPublicacion,
            "fkAsignatura" to fkAsignatura
        )
        collection.add(tema)
        return true // You can add error logic if desired
    }

    fun getTemaById(id: String): DbTema? {
        // This is an asynchronous method, you may need to use callbacks or coroutines
        var tema: DbTema? = null
        collection.document(id).get().addOnSuccessListener { document ->
            if (document != null) {
                tema = document.toObject(DbTema::class.java)
            }
        }
        return tema
    }

    fun updateTema(id: String): Boolean {
        val tema = hashMapOf(
            "nombreTema" to nombreTema,
            "autorTema" to autorTema,
            "calificacion" to calificacion,
            "fechaPublicacion" to fechaPublicacion,
            "fkAsignatura" to fkAsignatura
        )
        collection.document(id).set(tema)
        return true // You can add error logic if desired
    }

    fun deleteTema(id: String): Boolean {
        collection.document(id).delete()
        return true // You can add error logic if desired
    }

    // ... (other methods and class logic)
}
