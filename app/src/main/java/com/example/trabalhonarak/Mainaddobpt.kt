package com.example.trabalhonarak

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
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
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import java.io.FileNotFoundException
import java.io.InputStream

class Mainaddobpt : AppCompatActivity() {

    private val PICK_IMAGE_REQUEST = 1
    private var filePath: Uri? = null
    private var imgEncoded: String? = null
    private lateinit var imageViewSelected: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mainaddobpt)

        val nome = findViewById<EditText>(R.id.editTextText6)
        val button = findViewById<ImageButton>(R.id.imageButton4) //botao menu principal
        val button1 = findViewById<ImageButton>(R.id.imageButton11) //botao de voltar
        val button2 = findViewById<Button>(R.id.button7) //botao de adicionar
        imageViewSelected = findViewById(R.id.imageViewSelected)

        button.setOnClickListener {
            TrocarTela()
        }
        button1.setOnClickListener{
            TrocarTela1()
        }
        button2.setOnClickListener {
            val obra = hashMapOf(
                "nome" to nome.text.toString()
            )
            if (imgEncoded != null) {
                obra["imageBase64"] = imgEncoded!!
            }
            FirebaseFirestore.getInstance().collection("obras").add(obra)
                .addOnSuccessListener {
                    Log.d("Mainaddobpt", "Obra salva com sucesso.")
                    TrocarTela2()
                }
                .addOnFailureListener { e ->
                    Log.e("Mainaddobpt", "Erro ao salvar obra: $e")
                }
        }

        val pesquisa = findViewById<EditText>(R.id.editTextText6)
        pesquisa.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                pesquisa.hint = ""
            } else {
                pesquisa.hint = getString(R.string.sobreObra)
            }
        }

        val pesquisa1 = findViewById<EditText>(R.id.editTextText3)
        pesquisa1.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                pesquisa1.hint = ""
            } else {
                pesquisa1.hint = getString(R.string.nomeDaObra)
            }
        }

        imageViewSelected.setOnClickListener {
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
                        imgEncoded = Base64.encodeToString(imageStream!!.readBytes(), Base64.DEFAULT)
                        val decodedByteArray = Base64.decode(imgEncoded, Base64.DEFAULT)
                        val selectedImage = BitmapFactory.decodeByteArray(decodedByteArray, 0, decodedByteArray.size)
                        imageViewSelected.setImageBitmap(selectedImage)
                    } catch (e: FileNotFoundException) {
                        e.printStackTrace()
                        Toast.makeText(this, "Erro ao carregar imagem", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun TrocarTela() {
        val intent = Intent(this, Mainpt::class.java)
        startActivity(intent)
    }

    private fun TrocarTela1() {
        val intent1 = Intent(this, Mainadmpt::class.java)
        startActivity(intent1)
    }

    private fun TrocarTela2() {
        val intent2 = Intent(this, Mainadmpt::class.java)
        startActivity(intent2)
    }
}
