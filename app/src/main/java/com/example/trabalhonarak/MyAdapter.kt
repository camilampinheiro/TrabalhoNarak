package com.example.trabalhonarak

import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val obras: ArrayList<Obra>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textviewTitulo: TextView = itemView.findViewById(R.id.textViewTitulo)
        var textViewDesc: TextView = itemView.findViewById(R.id.textViewDesc)
        var imageView: ImageView = itemView.findViewById(R.id.imageViewSelected)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.item_rv, parent, false)
        return MyViewHolder(item)
    }

    override fun getItemCount(): Int {
        return obras.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.textviewTitulo.text = obras[position].titulo
        holder.textViewDesc.text = obras[position].desc

        val imageBase64 = obras[position].image
        if (imageBase64.isNotEmpty()) {
            val decodedByteArray = Base64.decode(imageBase64, Base64.DEFAULT)
            val bitmap = BitmapFactory.decodeByteArray(decodedByteArray, 0, decodedByteArray.size)
            holder.imageView.setImageBitmap(bitmap)
        } else {
            holder.imageView.setImageResource(android.R.color.transparent)
        }
    }
}
