package com.example.examen_01.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbHelper(context: Context?) : SQLiteOpenHelper(
    context,
    "DBExamen01.db",
    null,
    1
) {
    override fun onCreate(db: SQLiteDatabase?) {
        val scriptSQLCrearTablaAsignatura =
            "CREATE TABLE t_asignatura(" +
                    "id_asignatura INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nombre_asignatura TEXT NOT NULL," +
                    "autor_asignatura TEXT NOT NULL," +
                    "fechaPublicacion TEXT NOT NULL," +
                    "estadoFavorito TEXT NOT NULL);"

        val scriptSQLCrearTablaTema =
            "CREATE TABLE t_tema(" +
                    "id_tema INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nombre_tema TEXT NOT NULL," +
                    "autor_tema TEXT NOT NULL," +
                    "calificacion TEXT NOT NULL," +
                    "fechaPublicacion TEXT NOT NULL, " +
                    "IDasignatura INTEGER NOT NULL," +
                    "FOREIGN KEY(IDasignatura) REFERENCES t_asignatura(id_asignatura));"

        db?.execSQL(scriptSQLCrearTablaAsignatura)
        db?.execSQL(scriptSQLCrearTablaTema)
    }

    // Se ejecuta cuando la version cambia
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS t_tema")
        db?.execSQL("DROP TABLE IF EXISTS t_asignatura")
        onCreate(db)
    }
}