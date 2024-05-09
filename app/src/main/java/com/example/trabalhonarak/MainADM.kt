package com.example.trabalhonarak

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import java.io.ByteArrayOutputStream

class MainADM : AppCompatActivity() {
    private lateinit var imagem: ImageView
    private var imageBase64: String? = null

    private val getImage = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            imagem.setImageURI(uri)
            val inputStream = contentResolver.openInputStream(uri)
            val bitmap = BitmapFactory.decodeStream(inputStream)
            imageBase64 = bitmapToBase64(bitmap)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mainaddobpt)

        val nome = findViewById<EditText>(R.id.editTextText6)
        val adicionar = findViewById<Button>(R.id.button7)
        imagem = findViewById<ImageView>(R.id.imageView54)

        imagem.setOnClickListener {
            getImage.launch("image/*")
        }

        adicionar.setOnClickListener {
            val nomeObra = nome.text.toString()
            if (nomeObra.isNotEmpty() && imageBase64 != null) {
                FirebaseFirestore.getInstance().collection("obras").add(mapOf(
                    "nome" to nomeObra,
                    "imagem" to imageBase64
                )).addOnSuccessListener {
                    Toast.makeText(this, "Obra adicionada com sucesso!", Toast.LENGTH_SHORT).show()
                }.addOnFailureListener {
                    Toast.makeText(this, "Falha ao adicionar obra", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Por favor, selecione uma imagem e digite o nome da obra.", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun bitmapToBase64(bitmap: Bitmap): String {
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
        val byteArray = outputStream.toByteArray()
        return Base64.encodeToString(byteArray, Base64.DEFAULT)
    }
}
