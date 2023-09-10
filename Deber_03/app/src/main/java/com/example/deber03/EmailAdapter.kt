package com.example.deber03

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.deber03.model.Email

class EmailAdapter(
    private val lista: List<Email>

) : RecyclerView.Adapter<EmailAdapter.MyViewHolder>() {
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txt_user: TextView
        val txt_icon: TextView
        val txt_subject: TextView
        val txt_preview: TextView
        val txt_date: TextView
        val img_star: ImageView

        init {
            txt_user = view.findViewById(R.id.txt_user)
            txt_icon = view.findViewById(R.id.txt_icon)
            txt_subject = view.findViewById(R.id.txt_subject)
            txt_preview = view.findViewById(R.id.txt_preview)
            txt_date = view.findViewById(R.id.txt_date)
            img_star = view.findViewById(R.id.img_star)
        }

        @SuppressLint("WrongConstant")
        fun bind(email: Email){
            val hash: Int = email.user.hashCode()
            txt_icon.text = email.user.get(0).toString()
            txt_icon.background = oval(Color.rgb(hash, hash/2, 0), txt_icon)
        }

        fun oval(color: Int, view: View): ShapeDrawable{
            val shapeDrawable = ShapeDrawable(OvalShape())
            shapeDrawable.intrinsicWidth = view.width
            shapeDrawable.intrinsicHeight = view.height
            shapeDrawable.paint.color = color
            return shapeDrawable
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.email_item,
                parent,
                false
            )
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val entrenadorActual = this.lista[position]
        holder.txt_user.text = entrenadorActual.user
        holder.txt_subject.text = entrenadorActual.subject
        holder.txt_preview.text = entrenadorActual.preview
        holder.txt_date.text = entrenadorActual.date
        holder.bind(entrenadorActual)
    }

    override fun getItemCount(): Int {
        return this.lista.size
    }

}