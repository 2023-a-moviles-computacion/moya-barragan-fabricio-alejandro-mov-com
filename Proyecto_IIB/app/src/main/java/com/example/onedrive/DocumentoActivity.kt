package com.example.onedrive

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView

class DocumentoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_documento)
        var listaArchivos = arrayListOf<Documento>()
        listaArchivos.add(Documento(R.drawable.buses,"BUS TIPO GUADALAJARA","Parlamento-Comité"," 1 hr 8 min (a 15 min)"))


        val recyclerView = findViewById<RecyclerView>(R.id.rvArchivo)
        initRecyclerView(listaArchivos, recyclerView)
    }

    private fun initRecyclerView(listaArchivos: ArrayList<Documento>, recyclerView: RecyclerView) {
        val adapter = AdapterDocumento(listaArchivos,this,recyclerView)
        recyclerView.adapter = adapter
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        adapter.notifyDataSetChanged()
        adapter.onItemClick = {
            val intent = Intent(this, Home::class.java)
            startActivity(intent)}
    }


}