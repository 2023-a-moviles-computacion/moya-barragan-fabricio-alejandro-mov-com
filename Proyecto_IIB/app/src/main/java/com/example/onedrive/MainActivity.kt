package com.example.onedrive

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var listaDirectorios = arrayListOf<Carpeta>()
        listaDirectorios.add(Carpeta(R.drawable.buses,"BUS TIPO GUADALAJARA","Parlamento-Calderón"))
        listaDirectorios.add(Carpeta(R.drawable.buses,"BUS TIPO COLETRANS","Camal-Aeropuerto"))
        listaDirectorios.add(Carpeta(R.drawable.buses,"BUS TIPO ALBORADA","Marín-Comité del Pueblo",))

        val recyclerView = findViewById<RecyclerView>(R.id.rvDirectorio)
        initRecyclerView(listaDirectorios, recyclerView)
    }

    private fun initRecyclerView(listaDirectorios: ArrayList<Carpeta>, recyclerView: RecyclerView) {
        val adapter = AdapterCarpeta(listaDirectorios,this,recyclerView)
        recyclerView.adapter = adapter
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        adapter.notifyDataSetChanged()
        adapter.onItemClick = {
            val intent = Intent(this, DocumentoActivity::class.java)
            startActivity(intent)
        }
    }
}