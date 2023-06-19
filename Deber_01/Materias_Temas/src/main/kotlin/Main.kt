import java.text.SimpleDateFormat
import java.util.Date

fun main(args: Array<String>) {
    //Agregando Temas

    //Agregando Asignaturas

    //Menú
    do {
        var opcionUsuario = false
        println(
            "APLICACIONES MÓVILES - CRUD -> TEMA - ASIGNATURA:" +
                    "\n1) Temas" +
                    "\n2) Asignaturas" +
                    "\n3) Salir"
        )
        var opTabla = readln().toInt()
        if (opTabla != 3) {
            var opcionAux = false
            var textoConsola = ""
            if (opTabla == 1) {
                textoConsola += "Elija una opción:" +
                        "\n1) Ingresar nuevo tema" +
                        "\n2) Ver temas" +
                        "\n3) Actualizar tema" +
                        "\n4) Eliminar tema" +
                        "\n5) Volver"
            } else {
                textoConsola += "Elija una opción:" +
                        "\n1) Ingresar nueva asignatura" +
                        "\n2) Ver asignaturas" +
                        "\n3) Actualizar asignatura" +
                        "\n4) Eliminar asignatura" +
                        "\n5) Volver"
            }
            while (!opcionAux) {
                println(textoConsola)
                var opcionCrud = readln().toInt()
                when (opcionCrud) {
                    (1) -> {
                        if (opTabla == 1) {
                            print("Nombre tema: ")
                            var nombre = readln()
                            print("Autor tema: ")
                            var autor = readln()
                            print("Calificación: ")
                            var calificacion = readln().toDouble()
                            print("Fecha publicación (AAAA-MM-DD): ")
                            var fecha = readln()
                            var fechaSplit = fecha.split("-")
                            var fechaAux: Date =
                                Date(fechaSplit[0].toInt() - 1900, fechaSplit[1].toInt() - 1, fechaSplit[2].toInt())

                            var newTema =
                                Tema(Tema.getNumTemas() + 1, nombre, autor, calificacion, fechaAux)
                            newTema.insertTema()

                        } else {
                            print("Nombre asignatura: ")
                            var nombre = readln()
                            print("Profesor asignatura: ")
                            var autor = readln()
                            print("Fecha inicio (AAAA-MM-DD): ")
                            var fecha = readln()
                            var fechaSplit = fecha.split("-")
                            var fechaAux: Date =
                                Date(fechaSplit[0].toInt() - 1900, fechaSplit[1].toInt() - 1, fechaSplit[2].toInt())
                            print("Estado favorito 1)SI 2)NO: ")
                            var estadoAux = readln().toInt()
                            var estado: Boolean

                            if (estadoAux == 1) {
                                estado = true
                            } else {
                                estado = false
                            }

                            println("\n***LISTA DE TEMAS DISPONIBLES***")
                            Tema.selectTema()

                            print("Seleccione los temas a agregar a la asignatura (1,2,...): ")
                            var stringTemas = readLine().toString()
                            var splitTemas = stringTemas.split(",")
                            var intTemasArray = splitTemas.map { it.toInt() }.toTypedArray()
                            var listaAsignatura: ArrayList<Tema> = Asignatura.setArrayListTemaAsignatura(intTemasArray)

                            var newAsignatura = Asignatura(Asignatura.getNumAsignatura() + 1, nombre, autor, fechaAux, estado, listaAsignatura)
                            newAsignatura.insertAsignatura(intTemasArray.size)
                        }
                    }

                    (2) -> {
                        if (opTabla == 1) {
                            println("LISTA DE TEMAS:")
                            Tema.selectTema()
                        } else {
                            println("LISTA DE ASIGNATURAS")
                            Asignatura.selectAsignatura()
                        }
                    }

                    (3) -> {
                        if (opTabla == 1) {
                            print("Ingrese el nombre del tema ha actualizar: ")
                            var searchTema = readln()
                            Tema.updateTema(searchTema)
                        } else {
                            print("Ingrese el nombre de la asignatura ha actualizar: ")
                            var searchAsignatura = readln()
                            Asignatura.updateAsignatura(searchAsignatura)
                        }
                    }

                    (4) -> {
                        if (opTabla == 1) {
                            print("Ingrese el nombre del tema ha eliminar: ")
                            var searchTema = readln()
                            Tema.deleteTema(searchTema)
                        } else {
                            print("Ingrese el nombre de la asignatura ha eliminar: ")
                            var searchAsignatura = readln()
                            Asignatura.deleteAsignatura(searchAsignatura)
                        }
                    }

                    (5) -> {
                        opcionAux = true
                    }
                }
            }
        } else {
            opcionUsuario = true
        }
    } while (!opcionUsuario)
}
