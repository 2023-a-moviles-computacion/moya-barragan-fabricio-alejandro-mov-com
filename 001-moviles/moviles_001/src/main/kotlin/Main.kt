import sun.jvm.hotspot.oops.OopUtilities
import java.util.*

fun main(args: Array<String>) {
    println("Hello World!")

    //INMUTABLES (NO SE REASIGNAN "="
    var inmutable = "Alejandro";
    //inmutable = "Moya"

    //Mutables (re asignar)
    var mutable : String = "Alejandro";
    mutable = "Moya"

    //duck typing
    val ejemploVariables = "Alejandro Moya"
    ejemploVariables.trim() //Quita los espacios antes y despues de una cadena de caracteres
    val edadEjemplo= "Alejandro Moya"


    //Variables primitivas
    val nombreProfesor: String = "Alejandro Moya"
    val sueldo: Double = 1.2
    val estadoCivil:Char = 'C' //Chasrs comillas simples
    val mayorEdad:Boolean= true

    //clases Java
    val fechaNacimiento: Date=Date()

    //No existe el Switch en Kotlin
    val estadoCivilWhen = "C"
    when (estadoCivilWhen){
        ("C") ->{
            println("Casado")
        }
        "S" ->{
            println("Soltero")
        }
        else ->{
            println("No sabemos")
        }
    }

    //Tambien tenemos el de una sola linea
    val coqueteo = if(estadoCivilWhen == "S") "Si" else "No"

    //void -> Unit
    fun imprimirNombre(nombre: String): Unit{
        println("Nombre : ${nombre}")
    }

    fun calcularSueldo(
        sueldo: Double, //Requerido
        tasa: Double = 12.00, //Opcional (defecto)
        bonoEspecial: Double? = null, //Opcional null ->nullaable
        // El simbolo de ? indica que puede ser nuelo en algun punto
        // Int -> Int?
        //String -> String?
        //Date -> Date?
    ):Double{
        if(bonoEspecial == null){
            return sueldo * (100/tasa)
        }else{
            return sueldo * (100/tasa) + bonoEspecial
        }

    }
     abstract class NumerosJava{
         protected val numeroUno: Int
         private val numeroDos: Int
         constructor(
             uno: Int,
             dos: Int
         ){
             this.numeroUno = uno
             this.numeroDos = dos

             println("Inicializando")
         }

         constructor(
             uno: Int?,
             dos: Int
         ): this(
             if (uno == null) 0 else uno,
             dos
         ){
             numeroUno
         }

         constructor(
             uno: Int,
             dos: Int?
         ): this(
             uno,
             if (dos == null) 0 else uno
         )

         constructor(
             uno: Int?,
             dos: Int?
         ) : this(
             if (uno ==null) 0 else uno,
             if (dos == null) 0 else uno
         )

         public fun sumar(): Int {
             val total = numeroUno+ numeroDos
             agregarHistorial(total)
             return total
         }

         companion object{
             val pi = 3.14
             fun elevarAlCuadrado(num: Int): Int{
                 return  num * num
             }
             val historialSumas = arrayListOf<Int>()
             fun agregarHistorial(valorNuevaSuma: Int){
                 historialSumas.add(valorNuevaSuma)
             }
         }
     }


    abstract class Numeros(//Constructor Primario
        //Ejemplo:
        // uno: Int, (Parametro (sin modificador de acceso))
        // public var uno: Int, //Propiedad Publica Clase numero.uno
        // var uno: Int, //Porpiedad de la clase (por defecto es PUBLIC)
        protected val numeroUno: Int, //Propiedad de la clase protected numeros.numeroUno
        protected val numeroDos: Int, //Propiedad de la clase protected numeros.numeroDos

    ){
        // var cedula: string = " (public es por defecto)
        // private valorCalculado: Int = 0 (private)

        init{
            this.numeroUno; this.numeroDos // this es opcional
            numeroUno; numeroDos //sin el "this", es lo mismo
            println("Inicializando")
        }
    }

    class Suma(
        uno: Int,
        dos: Int
    ): Numeros(uno, dos){
        init{
            this.numeroUno; numeroUno;
            this.numeroDos; numeroDos;
        }
    }
}

