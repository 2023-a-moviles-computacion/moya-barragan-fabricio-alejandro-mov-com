package com.example.examen_01.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

class DbAsignatura(
    // Atributos
    private var idAsignatura: Int?,
    private var nombreAsignatura: String,
    private var docenteAsignatura: String,
    private var fechaInicio: String,
    private var estadoFavorito: String,
    private val context: Context?
) {
    init {
        idAsignatura
        nombreAsignatura
        docenteAsignatura
        fechaInicio
        estadoFavorito
        context
    }

    fun setidAsignatura(idAsignatura: Int) {
        this.idAsignatura = idAsignatura
    }

    fun setnombreAsignatura(nombreAsignatura: String) {
        this.nombreAsignatura = nombreAsignatura
    }

    fun setdocenteAsignatura(autorAsignatura: String) {
        this.docenteAsignatura = autorAsignatura
    }

    fun setfechaInicio(fechaPublicacion: String) {
        this.fechaInicio = fechaPublicacion
    }

    fun setestadoFavorito(estadoFav: String) {
        this.estadoFavorito = estadoFav
    }

    fun getidAsignatura(): Int? {
        return idAsignatura
    }

    fun getnombreAsignatura(): String {
        return nombreAsignatura
    }

    fun getautorAsignatura(): String {
        return docenteAsignatura
    }

    fun getfechaPublicacion(): String {
        return fechaInicio
    }

    fun getestadoFavorito(): String {
        return estadoFavorito
    }

    fun insertAsignatura(): Long {
        val dbHelper: DbHelper = DbHelper(this.context)
        val db: SQLiteDatabase = dbHelper.writableDatabase

        val values: ContentValues = ContentValues()
        values.put("nombre_asignatura", this.nombreAsignatura)
        values.put("autor_asignatura", this.docenteAsignatura)
        values.put("fechaPublicacion", this.fechaInicio)
        values.put("estadoFavorito", this.estadoFavorito)

        return db.insert("t_asignatura", null, values)
    }

    fun showAsignaturaes(): ArrayList<DbAsignatura> {
        val dbHelper: DbHelper = DbHelper(this.context)
        val db: SQLiteDatabase = dbHelper.writableDatabase

        var lista = ArrayList<DbAsignatura>()
        var asignatura: DbAsignatura
        var cursor: Cursor? = null

        cursor = db.rawQuery("SELECT * FROM t_asignatura", null)

        if (cursor.moveToFirst()) {
            do {
                asignatura = DbAsignatura(null, "", "", "", "", null)

                asignatura.setidAsignatura(cursor.getString(0).toInt())
                asignatura.setnombreAsignatura(cursor.getString(1))
                asignatura.setdocenteAsignatura(cursor.getString(2))
                asignatura.setfechaInicio(cursor.getString(3))
                asignatura.setestadoFavorito(cursor.getString(4))
                lista.add(asignatura)
            } while (cursor.moveToNext())
        }

        cursor.close()
        return lista
    }

    fun getAsignaturaById(id: Int): DbAsignatura{
        val dbHelper = DbHelper(this.context)
        val db: SQLiteDatabase = dbHelper.writableDatabase

        var asignatura = DbAsignatura(null, "", "", "", "", this.context)
        var cursor: Cursor? = null

        cursor = db.rawQuery("SELECT * FROM t_asignatura WHERE id_asignatura = ${id+1}", null)

        if (cursor.moveToFirst()) {
            do {
                asignatura.setidAsignatura(cursor.getString(0).toInt())
                asignatura.setnombreAsignatura(cursor.getString(1))
                asignatura.setdocenteAsignatura(cursor.getString(2))
                asignatura.setfechaInicio(cursor.getString(3))
                asignatura.setestadoFavorito(cursor.getString(4))
            } while (cursor.moveToNext())
        }

        cursor.close()
        return asignatura
    }

    fun updateAsignatura(): Int {
        val dbHelper = DbHelper(this.context)
        val db: SQLiteDatabase = dbHelper.writableDatabase

        val values: ContentValues = ContentValues()
        values.put("nombre_asignatura", this.nombreAsignatura)
        values.put("autor_asignatura", this.docenteAsignatura)
        values.put("fechaPublicacion", this.fechaInicio)
        values.put("estadoFavorito", this.estadoFavorito)

        return db.update("t_asignatura", values, "id_asignatura="+this.idAsignatura, null)
    }

    fun deleteAsignatura(id: Int): Int{
        val dbHelper = DbHelper(this.context)
        val db: SQLiteDatabase = dbHelper.writableDatabase

        return db.delete("t_asignatura", "id_asignatura="+(id+1), null)
    }

    override fun toString(): String {
        val salida =
            "Núm. Asignatura: ${idAsignatura}\n" +
                    "Asignatura: ${nombreAsignatura}\n" +
                    "Docente: ${docenteAsignatura}\n" +
                    "Fecha de publicación: ${fechaInicio}\n" +
                    "Estado Favorito: ${estadoFavorito}"

        return salida
    }
}