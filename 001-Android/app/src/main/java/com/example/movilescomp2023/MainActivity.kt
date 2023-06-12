package com.example.movilescomp2023

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun irActividad(
    clase: Class<*>
    ){
        val intent = Intent(this.clase)
        startActivity(intent)
    }
}