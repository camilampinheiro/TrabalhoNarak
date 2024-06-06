package com.example.trabalhonarak

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(var obras:ArrayList<Obra>): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {


    class MyViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        var textviewTitulo = itemView.findViewById<TextView>(R.id.textViewTitulo)
        var textViewDesc = itemView.findViewById<TextView>(R.id.textViewDesc)
        var imageView = itemView.findViewById<ImageView>(R.id.imageViewSelected)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var item =LayoutInflater.from(parent.context).inflate(R.layout.item_rv,parent,false)
        return MyViewHolder(item)
    }

    override fun getItemCount(): Int {
        return obras.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            holder.textviewTitulo.text = obras[position].titulo
            holder.textViewDesc.text = obras[position].desc
            holder.imageView.
            setImageBitmap(
                BitmapFactory.decodeByteArray(
                    obras[position].image.encodeToByteArray(),
                    0,
                    obras[position].image.encodeToByteArray().size))
    }
}