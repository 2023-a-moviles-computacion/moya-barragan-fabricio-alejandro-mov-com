package com.example.examen_01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Button
import android.widget.Toast
import com.example.examen_01.db.DbHelper

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Creando DB
        val dbHelper: DbHelper = DbHelper(this)
        val db: SQLiteDatabase = dbHelper.writableDatabase
        if (db != null) {
            //Toast.makeText(this, "BASE DE DATOS CREADA", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "ERROR AL CREAR LA BD", Toast.LENGTH_LONG).show()
        }
        // Fin creacion DB
        irActividad(Asignatura::class.java)

    }

    fun irActividad(
        clase: Class<*>
    ) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}
