package com.example.onedrive

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterCarpeta(
    private val listCarpeta: List<Carpeta>,
    private val contexto: MainActivity,
    private val recycler: RecyclerView

) : RecyclerView.Adapter<AdapterCarpeta.ViewHolder>() {

    var onItemClick : ((Carpeta) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_opciones, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val imagen = listCarpeta[position].imgCarpeta
        val nombre = listCarpeta[position].nombreCarpeta
        val peso = listCarpeta[position].pesoCarpeta
        holder.setData(imagen, nombre, peso)
        holder.itemView.setOnClickListener{
            onItemClick?.invoke(listCarpeta[position])
        }
    }

    override fun getItemCount(): Int {
        return listCarpeta.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView
        private val textView: TextView
        private val textView2: TextView


        init {
            imageView = itemView.findViewById(R.id.imDirectorio)
            textView = itemView.findViewById(R.id.tvTipoBus)
            textView2 = itemView.findViewById(R.id.tvRutaBus)

        }

        fun setData(imagen: Int, nombre: String?, peso: String?) {
            imageView.setImageResource(imagen)
            textView.text = nombre
            textView2.text = peso

        }
    }
}