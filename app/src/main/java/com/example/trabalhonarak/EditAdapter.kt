package com.example.trabalhonarak

import android.app.AlertDialog
import android.content.Context
import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore

class EditAdapter(var obras: ArrayList<Obra>, val context: Context) : RecyclerView.Adapter<EditAdapter.EditViewHolder>() {

    class EditViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var editTextTitulo: EditText = itemView.findViewById(R.id.editTextText8)
        var editTextDesc: EditText = itemView.findViewById(R.id.editTextText6)
        var imageView: ImageView = itemView.findViewById(R.id.imageViewSelected)
        var buttonUpdate: Button = itemView.findViewById(R.id.buttonUpdate)
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

        holder.buttonUpdate.setOnClickListener {
            val updatedTitulo = holder.editTextTitulo.text.toString()
            val updatedDesc = holder.editTextDesc.text.toString()

            val db = FirebaseFirestore.getInstance()
            val obraRef = db.collection("obras").whereEqualTo("nome", obras[position].titulo)

            obraRef.get().addOnSuccessListener { documents ->
                for (document in documents) {
                    db.collection("obras").document(document.id).update(mapOf(
                        "nome" to updatedTitulo,
                        "descricao" to updatedDesc
                    )).addOnSuccessListener {
                        AlertDialog.Builder(context)
                            .setTitle("Sucesso")
                            .setMessage("Obra atualizada com sucesso!")
                            .setPositiveButton(android.R.string.ok) { dialog, _ ->
                                dialog.dismiss()
                                (context as AppCompatActivity).finish()
                            }
                            .show()
                    }.addOnFailureListener {
                        AlertDialog.Builder(context)
                            .setTitle("Erro")
                            .setMessage("Falha ao atualizar obra.")
                            .setPositiveButton(android.R.string.ok) { dialog, _ ->
                                dialog.dismiss()
                            }
                            .show()
                    }
                }
            }
        }
    }
}
