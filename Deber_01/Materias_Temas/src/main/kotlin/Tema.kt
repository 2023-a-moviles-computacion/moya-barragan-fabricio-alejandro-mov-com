import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption
import java.text.SimpleDateFormat
import java.util.*

class Tema(
    // Atributos
    private var idTema: Int,
    private var nombreTema: String,
    private var autorTema: String,
    private var calificacion: Double,
    private var fechaPublicacion: Date
) {
    init {
        idTema
        nombreTema
        autorTema
        calificacion
        fechaPublicacion
    }

    fun setIdTema(idTema: Int) {
        this.idTema = idTema
    }

    fun setNombreTema(nombreTema: String) {
        this.nombreTema = nombreTema
    }

    fun setAutorTema(autorTema: String) {
        this.autorTema = autorTema
    }

    fun setCalificacion(calificacion: Double) {
        this.calificacion = calificacion
    }

    fun setFechaPublicacion(fechaPublicacion: Date) {
        this.fechaPublicacion = fechaPublicacion
    }

    fun getIdTema(): Int {
        return idTema
    }

    fun getNombreTema(): String {
        return nombreTema
    }

    fun getAutorTema(): String {
        return autorTema
    }

    fun getCalificacion(): Double {
        return calificacion
    }

    fun getFechaPublicacion(): Date {
        return fechaPublicacion
    }

    companion object {
        fun selectTema() {
            // Leer archivo
            val path = Paths.get("src/main/resources/text/tema.txt")
            Files.lines(path, Charsets.UTF_8).forEach {
                val valorCadena = it.split(",")
                println(
                    "Núm. tema: " + valorCadena[0] + "\n"
                            + "Nombre: " + valorCadena[1] + "\n"
                            + "Autor: " + valorCadena[2] + "\n"
                            + "Calificación: " + valorCadena[3] + "\n"
                            + "Fecha publicación: " + valorCadena[4] + "\n"
                )
            }
        }

        fun updateTema(nombreTema: String) {
            // Leer archivo
            val path = Paths.get("src/main/resources/text/tema.txt")
            var flag = false
            var archivoUpdate = ""
            Files.lines(path, Charsets.UTF_8).forEach {
                val valorCadena = it.split(",")
                if (valorCadena[1] == nombreTema) {
                    var opcionUpdate = true
                    println(
                        "Núm. tema: " + valorCadena[0] + "\n"
                                + "Nombre: " + valorCadena[1] + "\n"
                                + "Autor: " + valorCadena[2] + "\n"
                                + "Calificación: " + valorCadena[3] + "\n"
                                + "Fecha publicación: " + valorCadena[4] + "\n"
                    )
                    // Ver qué atributo desea modificar
                    val arrayCadena = arrayOf<String>("0", "0", "0", "0")
                    do {
                        println("Elija el atributo a modificar: 1) Nombre, 2) Autor, 3) Calificación, 4) Fecha publicación")
                        val atributoUpdate = readln().toInt()
                        when (atributoUpdate) {
                            1 -> {
                                print("Ingrese el nuevo nombre: ")
                                val nombre = readln()
                                arrayCadena[0] = nombre
                            }

                            2 -> {
                                print("Ingrese el nuevo autor: ")
                                val autor = readln()
                                arrayCadena[1] = autor
                            }

                            3 -> {
                                print("Ingrese la nueva calificación: ")
                                val calificacion = readln()
                                arrayCadena[2] = calificacion
                            }

                            4 -> {
                                print("Ingrese la nueva fecha de publicación (AAAA-MM-DD): ")
                                val fecha = readln()
                                val auxFecha = fecha.split("-")
                                val newFecha: Date =
                                    Date(auxFecha[0].toInt() - 1900, auxFecha[1].toInt() - 1, auxFecha[2].toInt())
                                val formato = SimpleDateFormat("yyyy-MM-dd")
                                arrayCadena[3] = formato.format(newFecha)
                            }
                        }
                        // Ver si desea seguir actualizando el tema elegido
                        println("¿Desea seguir actualizando el tema elegido? 1) SI - 2) NO")
                        val auxOpcion = readln().toInt()
                        if (auxOpcion == 2) {
                            opcionUpdate = false // Terminar update de tema
                            for (i in 0 until arrayCadena.size) {
                                if (arrayCadena[i] == "0") {
                                    arrayCadena[i] = valorCadena[i + 1]
                                }
                            }
                            archivoUpdate += valorCadena[0] + "," + arrayCadena[0] + "," + arrayCadena[1] + "," + arrayCadena[2] + "," + arrayCadena[3] + "\n"
                        }
                    } while (opcionUpdate)
                    flag = true
                } else {
                    archivoUpdate += it + "\n"
                }
            }
            if (!flag) {
                println("TEMA NO ENCONTRADO")
            } else {
                File("src/main/resources/text/tema.txt").printWriter().use { out -> out.print(archivoUpdate) }
                println("TEMA ACTUALIZADO")
            }
        }

        fun deleteTema(nombreTema: String) {
            // Leer archivo
            val path = Paths.get("src/main/resources/text/tema.txt")
            var flag = false
            var archivoUpdate = ""
            Files.lines(path, Charsets.UTF_8).forEach {
                val valorCadena = it.split(",")
                if (valorCadena[1] == nombreTema) {
                    println("TEMA ELIMINADO")
                    flag = true
                } else {
                    archivoUpdate += it + "\n"
                }
            }
            if (!flag) {
                println("TEMA NO ENCONTRADO")
            } else {
                File("src/main/resources/text/tema.txt").printWriter().use { out -> out.print(archivoUpdate) }
            }
        }

        fun getNumTemas(): Int {
            val path = Paths.get("src/main/resources/text/tema.txt")
            var numTotal = 0
            Files.lines(path, Charsets.UTF_8).forEach {
                numTotal += 1
            }
            return numTotal
        }
    }

    fun insertTema() {
        // Enviar al archivo
        val path = Paths.get("src/main/resources/text/tema.txt")
        val formato = SimpleDateFormat("yyyy-MM-dd")
        val data =
            "$idTema,$nombreTema,$autorTema,$calificacion,${formato.format(fechaPublicacion)}\n"
        try {
            Files.write(path, data.toByteArray(), StandardOpenOption.APPEND)
            println("TEMA AGREGADO")
        } catch (e: IOException) {
            println("Fallo al ingresar tema al archivo")
        }
    }

}
