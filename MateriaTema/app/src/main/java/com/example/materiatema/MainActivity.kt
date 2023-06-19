package com.example.materiatema

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.io.File

data class Tema(val nombre: String, val descripcion: String)

data class Materia(val nombre: String, val temas: MutableList<Tema>)

class CRUD(private val filePath: String) {
    private val materias: MutableList<Materia> = mutableListOf()

    init {
        cargarDatos()
    }

    private fun guardarDatos() {
        val file = File(filePath)
        file.printWriter().use { out ->
            for (materia in materias) {
                out.println(materia.nombre)
                for (tema in materia.temas) {
                    out.println("${tema.nombre},${tema.descripcion}")
                }
                out.println("-----")
            }
        }
    }

    private fun cargarDatos() {
        val file = File(filePath)
        if (file.exists()) {
            var materiaActual: Materia? = null
            file.forEachLine { line ->
                if (line == "-----") {
                    materiaActual = null
                } else if (materiaActual == null) {
                    materiaActual = Materia(line, mutableListOf())
                    materias.add(materiaActual)
                } else {
                    val datosTema = line.split(",")
                    if (datosTema.size == 2) {
                        val tema = Tema(datosTema[0], datosTema[1])
                        materiaActual?.temas?.add(tema)
                    }
                }
            }
        }
    }

    fun agregarMateria(nombre: String) {
        val nuevaMateria = Materia(nombre, mutableListOf())
        materias.add(nuevaMateria)
        guardarDatos()
    }

    fun eliminarMateria(nombre: String) {
        val materia = materias.find { it.nombre == nombre }
        materias.remove(materia)
        guardarDatos()
    }

    fun actualizarMateria(nombreViejo: String, nombreNuevo: String) {
        val materia = materias.find { it.nombre == nombreViejo }
        materia?.nombre = nombreNuevo
        guardarDatos()
    }

    fun agregarTema(nombreMateria: String, tema: Tema) {
        val materia = materias.find { it.nombre == nombreMateria }
        materia?.temas?.add(tema)
        guardarDatos()
    }

    fun eliminarTema(nombreMateria: String, nombreTema: String) {
        val materia = materias.find { it.nombre == nombreMateria }
        val tema = materia?.temas?.find { it.nombre == nombreTema }
        materia?.temas?.remove(tema)
        guardarDatos()
    }

    fun actualizarTema(nombreMateria: String, nombreTemaViejo: String, nombreTemaNuevo: String, descripcionTemaNueva: String) {
        val materia = materias.find { it.nombre == nombreMateria }
        val tema = materia?.temas?.find { it.nombre == nombreTemaViejo }
        tema?.nombre = nombreTemaNuevo
        tema?.descripcion = descripcionTemaNueva
        guardarDatos()
    }

    fun imprimirMaterias() {
        for (materia in materias) {
            println("Materia: ${materia.nombre}")
            println("Temas:")
            for (tema in materia.temas) {
                println("- ${tema.nombre}: ${tema.descripcion}")
            }
            println("-----")
        }
    }
}

class MainActivity : AppCompatActivity() {
    private val crud: CRUD = CRUD("data.txt") // Ajusta el nombre y ubicación del archivo de texto

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Ejemplo de uso del CRUD

        // Agregar una materia
        crud.agregarMateria("Programación")

        // Agregar temas a una materia
        val tema1 = Tema("Variables", "Concepto y tipos de variables")
        val tema2 = Tema("Estructuras de control", "if-else, bucles, etc.")
        crud.agregarTema("Programación", tema1)
        crud.agregarTema("Programación", tema2)

        // Eliminar un tema
        crud.eliminarTema("Programación", "Variables")

        // Actualizar un tema
        crud.actualizarTema("Programación", "Estructuras de control", "Control de flujo", "if-else, bucles, etc.")

        // Eliminar una materia
        crud.eliminarMateria("Programación")

        // Imprimir las materias y temas
        crud.imprimirMaterias()
    }
}
