import java.awt.event.ItemEvent
import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption
import java.text.SimpleDateFormat
import java.util.*


class Asignatura(
    //Atributos
    private var idAsignatura: Int,
    private var nombreAsignatura: String,
    private var profesorAsignatura: String,
    private var fecha: Date,
    private var estadoFavorito: Boolean,
    private var listaTemas: ArrayList<Tema>?
) {
    init {
        idAsignatura
        nombreAsignatura
        profesorAsignatura
        fecha
        estadoFavorito
        listaTemas
    }

    fun setidAsignatura(idAsignatura: Int) {
        this.idAsignatura = idAsignatura
    }

    fun setnombreAsignatura(nombreAsignatura: String) {
        this.nombreAsignatura = nombreAsignatura
    }

    fun setprofesorAsignatura(profesorAsignatura: String) {
        this.profesorAsignatura = profesorAsignatura
    }

    fun setfecha(fecha: Date) {
        this.fecha = fecha
    }

    fun setestadoFavorito(estadoFavorito: Boolean) {
        this.estadoFavorito = estadoFavorito
    }

    fun getidAsignatura(): Int {
        return idAsignatura
    }

    fun getnombreAsignatura(): String {
        return nombreAsignatura
    }

    fun getprofesorAsignatura(): String {
        return profesorAsignatura
    }

    fun getfecha(): Date {
        return fecha
    }

    fun getestadoFavorito(): Boolean {
        return estadoFavorito
    }

    companion object {
        fun selectAsignatura() {
            //Leer archivo
            var path = Paths.get("src/main/resources/text/asignatura.txt")
            Files.lines(path, Charsets.UTF_8).forEach {
                var valorCadena = it.split(",")
                print(
                    "Núm. asignatura: " + valorCadena[0] + "\n"
                            + "Nombre: " + valorCadena[1] + "\n"
                            + "Profesor: " + valorCadena[2] + "\n"
                            + "Fecha: " + valorCadena[3] + "\n"
                            + "Favorito: " + valorCadena[4] + "\n"
                )
                println("Lista de temas:")
                var path = Paths.get("src/main/resources/text/tema.txt")
                Files.lines(path, Charsets.UTF_8).forEach {
                    var splitTemas = it.split(",")
                    var idTema = splitTemas[0]
                    for (i in 5..valorCadena.size - 1) {
                        if (idTema == valorCadena[i]) {
                            println("\t" + splitTemas[0] + ") " + splitTemas[1] + " - " + splitTemas[2])
                        }
                    }
                }
            }
            println()
        }

        fun updateAsignatura(nombreTema: String) {
            //Leer archivo
            var path = Paths.get("src/main/resources/text/asignatura.txt")
            var flag = false
            var archivoUpdate = ""
            Files.lines(path, Charsets.UTF_8).forEach {
                var valorCadena = it.split(",")
                if (valorCadena[1] == nombreTema) {
                    var opcionUpdate = true
                    print(
                        "Núm. asignatura: " + valorCadena[0] + "\n"
                                + "Nombre: " + valorCadena[1] + "\n"
                                + "Profesor: " + valorCadena[2] + "\n"
                                + "Fecha: " + valorCadena[3] + "\n"
                                + "Favorito: " + valorCadena[4] + "\n"
                    )
                    println("Lista de temas:")
                    var path = Paths.get("src/main/resources/text/tema.txt")
                    Files.lines(path, Charsets.UTF_8).forEach {
                        var splitTemas = it.split(",")
                        var idTema = splitTemas[0]
                        for (i in 5..valorCadena.size - 1) {
                            if (idTema == valorCadena[i]) {
                                println("\t" + splitTemas[0] + ") " + splitTemas[1] + " - " + splitTemas[2])
                            }
                        }
                    }
                    //Ver que atributo desea modificar
                    var newString: String = ""
                    var arrayCadena = arrayOf<String>("0", "0", "0", "0", "0")
                    do {
                        println("Elija el atributo a modificar: 1) Nombre, 2) Profesor, 3) Fecha, 4) Favorito, 5) Lista temas")
                        var atributoUpdate = readln().toInt()
                        when (atributoUpdate) {
                            (1) -> {
                                print("Ingrese el nuevo nombre: ")
                                var nombre = readln()
                                arrayCadena.set(0, nombre)
                            }

                            (2) -> {
                                print("Ingrese el nuevo profesor: ")
                                var profesor = readln()
                                arrayCadena.set(1, profesor)
                            }

                            (3) -> {
                                print("Ingrese la nueva fecha de inicio (AAAA-MM-DD): ")
                                var fecha = readln()
                                var auxFecha = fecha.split("-")
                                val formato = SimpleDateFormat("yyyy-MM-dd")
                                var newFecha: Date =
                                    Date(auxFecha[0].toInt() - 1900, auxFecha[1].toInt() - 1, auxFecha[2].toInt())
                                arrayCadena.set(2, formato.format(newFecha))
                            }

                            (4) -> {
                                print("Ingrese el nuevo estado de favorito: ")
                                var favorito = readln()
                                arrayCadena.set(3, favorito)
                            }

                            (5) -> {
                                print("Seleccione una acción 1) Agregar tema a la asignatura 2) Eliminar tema de la asignatura: ")
                                var opcionLista = readln().toInt()
                                if (opcionLista == 1) {
                                    println("***LISTA DE TEMAS***")
                                    Tema.selectTema()
                                    print("Seleccione los temas a agregar a la asignatura (1,2,...): ")
                                    var newListTemas = readln()
                                    arrayCadena.set(4, updateListaTemas(newListTemas, valorCadena[0].toInt()))
                                } else {
                                    print("Seleccione los temas a eliminar de la asignatura (1,2,...): ")
                                    var deleteList = readln()
                                    var auxLista = deleteListaTemas(deleteList, valorCadena[0].toInt())
                                    arrayCadena.set(4, auxLista)
                                }
                            }
                        }
                        //Ver si desea seguir actualizando la asignatura elegida
                        println("¿Desea seguir actualizando la asignatura elegida 1) SI - 2) NO?")
                        var auxOpcion = readln().toInt()
                        if (auxOpcion == 2) {
                            opcionUpdate = false //Terminar update de asignatura
                            for (i in 0..arrayCadena.size - 1) {
                                if (arrayCadena[i] == "0") {
                                    if (i == 4) { // Tomando lista de temas original de la asignatura
                                        for (j in 5..valorCadena.size - 1) {
                                            if (j == valorCadena.size - 1) {
                                                arrayCadena[i] += valorCadena[j]
                                            } else {
                                                arrayCadena[i] += valorCadena[j] + ","
                                            }
                                        }
                                    } else {
                                        arrayCadena[i] = valorCadena[i + 1]
                                    }
                                }
                            }
                            archivoUpdate += valorCadena[0] + "," + arrayCadena[0] + "," + arrayCadena[1] + "," + arrayCadena[2] + "," + arrayCadena[3] + "," + arrayCadena[4] + "\n"
                        }
                    } while (opcionUpdate == true)
                    flag = true
                } else {
                    archivoUpdate += it + "\n"
                }
            }
            if (!flag) {
                println("ASIGNATURA NO ENCONTRADA")
            } else {
                File("src/main/resources/text/asignatura.txt").printWriter().use { out -> out.print(archivoUpdate) }
                println("ASIGNATURA ACTUALIZADA")
            }
        }

        fun updateListaTemas(lista: String, id: Int): String {
            var newLista = ""
            var path = Paths.get("src/main/resources/text/asignatura.txt")
            Files.lines(path, Charsets.UTF_8).forEach {
                var valorCadena = it.split(",")
                if (valorCadena[0].toInt() == id) {
                    for (i in 5..valorCadena.size - 1) {
                        if (i == valorCadena.size - 1) {
                            newLista += valorCadena[i]
                        } else {
                            newLista += valorCadena[i] + ","
                        }
                    }
                }
            }
            return newLista + "," + lista
        }

        fun deleteListaTemas(lista: String, id: Int): String {
            var newLista = ""
            var path = Paths.get("src/main/resources/text/asignatura.txt")
            var splitListaParam = lista.split(",")
            Files.lines(path, Charsets.UTF_8).forEach {
                var valorCadena = it.split(",")
                if (valorCadena[0].toInt() == id) {
                    for (i in 5..valorCadena.size - 1) {
                        var bandera = false
                        for (j in 0..splitListaParam.size - 1) {
                            if (valorCadena[i] != splitListaParam[j]) {
                                bandera = true
                            } else {
                                bandera = false
                                break
                            }
                        }
                        if (bandera == true) {
                            newLista += valorCadena[i] + ","
                        }
                    }
                }
            }
            return removeLastNchars(newLista, 1)
        }

        fun removeLastNchars(str: String, n: Int): String {
            return str.substring(0, str.length - n)
        }

        fun deleteAsignatura(nombreAsignatura: String) {
            //Leer archivo
            var path = Paths.get("src/main/resources/text/asignatura.txt")
            var flag = false
            var archivoUpdate = ""
            Files.lines(path, Charsets.UTF_8).forEach {
                var valorCadena = it.split(",")
                if (valorCadena[1] == nombreAsignatura) {
                    println("ASIGNATURA ELIMINADA")
                    flag = true
                } else {
                    archivoUpdate += it + "\n"
                }
            }
            if (!flag) {
                println("ASIGNATURA NO ENCONTRADA")
            } else {
                File("src/main/resources/text/asignatura.txt").printWriter().use { out -> out.print(archivoUpdate) }
            }
        }

        fun getNumAsignatura(): Int {
            var path = Paths.get("src/main/resources/text/asignatura.txt")
            var numTotal = 0
            Files.lines(path, Charsets.UTF_8).forEach {
                numTotal += 1
            }
            return numTotal
        }

        fun setArrayListTemaAsignatura(arrayTemas: Array<Int>): ArrayList<Tema> {
            var path = Paths.get("src/main/resources/text/tema.txt")
            var listaTemas: ArrayList<Tema> = ArrayList()
            var i = 0
            Files.lines(path, Charsets.UTF_8).forEach {
                var stringSplit = it.split(",")
                if (i < arrayTemas.size) {
                    if (stringSplit[0] == arrayTemas[i].toString()) {
                        var splitFecha = stringSplit[4].split("-")
                        var temaAux = Tema(
                            stringSplit[0].toInt(),
                            stringSplit[1],
                            stringSplit[2],
                            stringSplit[3].toDouble(),
                            Date(splitFecha[0].toInt(), splitFecha[1].toInt(), splitFecha[2].toInt())
                        )
                        listaTemas.add(temaAux)
                        i++
                    }
                }
            }
            return listaTemas
        }
    }

    fun insertAsignatura(sizeArrayTemas: Int) {
        //Enviar al archivo
        var path = Paths.get("src/main/resources/text/asignatura.txt")
        val formato = SimpleDateFormat("yyyy-MM-dd")
        var data =
            this.idAsignatura.toString() + "," + this.nombreAsignatura + "," + this.profesorAsignatura + "," + formato.format(this.fecha) + "," + this.estadoFavorito + ","
        var i = 1
        for (item in this.listaTemas!!) {
            if (i < sizeArrayTemas) {
                data += item.getIdTema().toString() + ","
            } else {
                data += item.getIdTema().toString()
            }
            i++
        }
        data += "\n"
        try {
            Files.write(path, data.toByteArray(), StandardOpenOption.APPEND)
            println("ASIGNATURA AGREGADA\n")
        } catch (e: IOException) {
            println("Fallo al ingresar asignatura al archivo")
        }
    }
}
