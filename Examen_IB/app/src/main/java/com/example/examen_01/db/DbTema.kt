package com.example.examen_01.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

class DbTema(
    //Atributos
    private var idTema: Int?,
    private var nombreTema: String,
    private var autorTema: String,
    private var calificacion: String,
    private var fechaPublicacion: String,
    private var fkAsignatura: Int,
    private val context: Context?
) {
    init {
        nombreTema
        autorTema
        calificacion
        fechaPublicacion
        fkAsignatura
        context
    }

    fun setidTema(idTema: Int) {
        this.idTema = idTema
    }

    fun setnombreTema(nombreTema: String) {
        this.nombreTema = nombreTema
    }

    fun setautorTema(autorTema: String) {
        this.autorTema = autorTema
    }

    fun setcalificacion(calificacion: String) {
        this.calificacion = calificacion
    }

    fun setfechaPublicacion(fechaPublicacion: String) {
        this.fechaPublicacion = fechaPublicacion
    }

    fun setidAsignatura(idAsignatura: Int) {
        this.fkAsignatura = idAsignatura
    }

    fun getidTema(): Int? {
        return idTema
    }

    fun getidAsignatura(): Int {
        return fkAsignatura
    }

    fun getnombreTema(): String {
        return nombreTema
    }

    fun getautorTema(): String {
        return autorTema
    }

    fun getcalificacion(): String {
        return calificacion
    }

    fun getfechaPublicacion(): String {
        return fechaPublicacion
    }

    fun insertTema(): Long {
        val dbHelper: DbHelper = DbHelper(this.context)
        val db: SQLiteDatabase = dbHelper.writableDatabase

        val values: ContentValues = ContentValues()
        values.put("nombre_tema", this.nombreTema)
        values.put("autor_tema", this.autorTema)
        values.put("calificacion", this.calificacion)
        values.put("fechaPublicacion", this.fechaPublicacion)
        values.put("IDasignatura", this.fkAsignatura)

        return db.insert("t_tema", null, values)
    }

    fun showTemaes(id: Int): ArrayList<DbTema> {
        val dbHelper: DbHelper = DbHelper(this.context)
        val db: SQLiteDatabase = dbHelper.writableDatabase

        var listaTemaes = ArrayList<DbTema>()
        var tema: DbTema
        var cursorTema: Cursor? = null

        // Ver si el id: Int es diferente de null
        cursorTema = db.rawQuery("SELECT * FROM t_tema WHERE IDasignatura=${id+1}", null)

        if (cursorTema.moveToFirst()) {
            do {
                tema = DbTema(null, "", "", "", "", 0, null)

                tema.setidTema(cursorTema.getString(0).toInt())
                tema.setnombreTema(cursorTema.getString(1))
                tema.setautorTema(cursorTema.getString(2))
                tema.setcalificacion(cursorTema.getString(3))
                tema.setfechaPublicacion(cursorTema.getString(4))
                tema.setidAsignatura(cursorTema.getString(5).toInt())
                listaTemaes.add(tema)
            } while (cursorTema.moveToNext())
        }

        cursorTema.close()
        return listaTemaes
    }

    fun getTemaById(id: Int): DbTema{
        val dbHelper = DbHelper(this.context)
        val db: SQLiteDatabase = dbHelper.writableDatabase

        var tema = DbTema(null, "", "", "", "",0, this.context)
        var cursor: Cursor? = null

        cursor = db.rawQuery("SELECT * FROM t_tema WHERE id_tema = ${id+1}", null)

        if (cursor.moveToFirst()) {
            do {
                tema.setidTema(cursor.getString(0).toInt())
                tema.setnombreTema(cursor.getString(1))
                tema.setautorTema(cursor.getString(2))
                tema.setcalificacion(cursor.getString(3))
                tema.setfechaPublicacion(cursor.getString(4))
                tema.setidAsignatura(cursor.getString(5).toInt())
            } while (cursor.moveToNext())
        }

        cursor.close()
        return tema
    }

    fun updateTema(): Int {
        val dbHelper = DbHelper(this.context)
        val db: SQLiteDatabase = dbHelper.writableDatabase

        val values: ContentValues = ContentValues()
        values.put("nombre_tema", this.nombreTema)
        values.put("autor_tema", this.autorTema)
        values.put("calificacion", this.calificacion)
        values.put("fechaPublicacion", this.fechaPublicacion)
        values.put("IDasignatura", this.fkAsignatura)

        return db.update("t_tema", values, "id_tema="+this.idTema, null)
    }

    fun deleteTema(id: Int): Int{
        val dbHelper = DbHelper(this.context)
        val db: SQLiteDatabase = dbHelper.writableDatabase

        return db.delete("t_tema", "id_tema="+(id+1), null)
    }

    override fun toString(): String {
        val salida =
            "Núm. tema: ${idTema}\n" +
                    "Tema: ${nombreTema}\n" +
                    "Autor: ${autorTema}\n" +
                    "Calificación: ${calificacion} \n" +
                    "Fecha de publicación: ${fechaPublicacion}\n" +
                    "Núm. Asignatura: ${fkAsignatura}"

        return salida
    }
}