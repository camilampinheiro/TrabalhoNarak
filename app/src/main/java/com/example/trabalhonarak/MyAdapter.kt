package com.example.trabalhonarak

import android.graphics.BitmapFactory
import android.speech.tts.TextToSpeech
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(var obras: ArrayList<Obra>, private val tts: TextToSpeech) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textviewTitulo: TextView = itemView.findViewById(R.id.textViewTitulo)
        var textViewDesc: TextView = itemView.findViewById(R.id.textViewDesc)
        var imageView: ImageView = itemView.findViewById(R.id.imageViewSelected)
        var imageButton: ImageButton = itemView.findViewById(R.id.imageButton)
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

        if (obras[position].image.isNotEmpty()) {
            val decodedByteArray = Base64.decode(obras[position].image, Base64.DEFAULT)
            val bitmap = BitmapFactory.decodeByteArray(decodedByteArray, 0, decodedByteArray.size)
            holder.imageView.setImageBitmap(bitmap)
        } else {
            holder.imageView.setImageResource(android.R.color.transparent)
        }

        holder.imageButton.setOnClickListener {
            tts.speak(obras[position].desc, TextToSpeech.QUEUE_FLUSH, null, "")
        }
    }
}