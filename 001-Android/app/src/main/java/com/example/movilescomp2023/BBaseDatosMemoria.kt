package com.example.movilescomp2023

class BBaseDatosMemoria {
    companion object{
        val arregloBEntrenador = arrayListOf<BEntrenador>()
        init {
            arregloBEntrenador
                .add(
                    BEntrenador(1,"Alejandro","correo@gmail.com")
                )
            arregloBEntrenador
                .add(
                    BEntrenador(2,"Moya", "moya@epn.edu.com")
                )
            arregloBEntrenador
                .add(
                    BEntrenador(3,"Adrian", "adrian@gmail.com")
                )
        }
    }
}