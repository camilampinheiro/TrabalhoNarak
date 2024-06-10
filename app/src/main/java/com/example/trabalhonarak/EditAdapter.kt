package com.example.trabalhonarak

import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class EditAdapter(var obras: ArrayList<Obra>) : RecyclerView.Adapter<EditAdapter.EditViewHolder>() {

    class EditViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var editTextTitulo: EditText = itemView.findViewById(R.id.editTextText8)
        var editTextDesc: EditText = itemView.findViewById(R.id.editTextText6)
        var imageView: ImageView = itemView.findViewById(R.id.imageViewSelected)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EditViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.item_rv1, parent, false)
        return EditViewHolder(item)
    }

    override fun getItemCount(): Int {
        return obras.size
    }

    override fun onBindViewHolder(holder: EditViewHolder, position: Int) {
        holder.editTextTitulo.setText(obras[position].titulo)
        holder.editTextDesc.setText(obras[position].desc)
        if (obras[position].image.isNotEmpty()) {
            val decodedByteArray = Base64.decode(obras[position].image, Base64.DEFAULT)
            val bitmap = BitmapFactory.decodeByteArray(decodedByteArray, 0, decodedByteArray.size)
            holder.imageView.setImageBitmap(bitmap)
        } else {
            holder.imageView.setImageResource(android.R.color.transparent)
        }
    }
}