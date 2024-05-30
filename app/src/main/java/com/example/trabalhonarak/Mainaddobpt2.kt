package com.example.trabalhonarak

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import java.io.FileNotFoundException
import java.io.InputStream

class Mainaddobpt2 : AppCompatActivity() {

    private val PICK_IMAGE_REQUEST = 1
    private var filePath: Uri? = null
    private var imgEncoded: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_mainaddobpt2)

        val desc = findViewById<EditText>(R.id.editTextText7)
        val info = findViewById<EditText>(R.id.editTextText15)
        val button1 = findViewById<ImageButton>(R.id.imageButton18)
        val button2 = findViewById<Button>(R.id.button15)
        val imageView = findViewById<ImageView>(R.id.imageViewSelected)

        button1.setOnClickListener {
            TrocarTela1()
        }
        button2.setOnClickListener {
            val obra = hashMapOf(
                "descricao" to desc.text.toString(),
                "informacao" to info.text.toString()
            )
            if (imgEncoded != null) {
                obra["imageBase64"] = imgEncoded!!
            }
            FirebaseFirestore.getInstance().collection("obras").add(obra)
                .addOnSuccessListener {
                    Log.d("Mainaddobpt2", "Obra salva com sucesso.")
                    TrocarTela2()
                }
                .addOnFailureListener { e ->
                    Log.e("Mainaddobpt2", "Erro ao salvar obra: $e")
                }
        }

        desc.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                desc.hint = ""
            } else {
                desc.hint = "Descrição da Obra"
            }
        }

        info.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                info.hint = ""
            } else {
                info.hint = "Informação da Obra"
            }
        }

        imageView.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK && data != null && data.data != null) {
            when (requestCode) {
                PICK_IMAGE_REQUEST -> {
                    filePath = data.data
                    try {
                        val imageStream: InputStream? = contentResolver.openInputStream(filePath!!)
                        // Encodifica a imagem em Base64
                        imgEncoded = Base64.encode(imageStream!!.readBytes(), Base64.DEFAULT).toString(Charsets.UTF_8)
                        // Decodifica a imagem para exibição
                        val decodedByteArray = Base64.decode(imgEncoded, Base64.DEFAULT)
                        val selectedImage = BitmapFactory.decodeByteArray(decodedByteArray, 0, decodedByteArray.size)
                        findViewById<ImageView>(R.id.imageViewSelected).setImageBitmap(selectedImage)
                    } catch (e: FileNotFoundException) {
                        e.printStackTrace()
                        Toast.makeText(this, "Erro ao carregar imagem", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun TrocarTela1() {
        val intent = Intent(this, Mainpt::class.java)
        startActivity(intent)
    }

    private fun TrocarTela2() {
        val intent = Intent(this, Mainadmpt::class.java)
        startActivity(intent)
    }
}
