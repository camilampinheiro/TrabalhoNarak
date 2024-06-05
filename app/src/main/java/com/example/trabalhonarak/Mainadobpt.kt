package com.example.trabalhonarak

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Base64
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

class Mainadobpt : AppCompatActivity(), TextToSpeech.OnInitListener {

    private lateinit var pesquisa: EditText
    private lateinit var imageView2: ImageView
    private lateinit var textView19: TextView
    private lateinit var imageButton: ImageButton
    private lateinit var imageButton3: ImageButton
    private val db = FirebaseFirestore.getInstance()
    private var tts: TextToSpeech? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_mainadobpt)

        // Inicializando as views
        imageButton = findViewById(R.id.imageButton)
        imageButton3 = findViewById(R.id.imageButton3)
        imageView2 = findViewById(R.id.imageView2)
        pesquisa = findViewById(R.id.editTextText4)
        textView19 = findViewById(R.id.textView19)

        tts = TextToSpeech(this, this)

        imageButton.setOnClickListener {
            speakOut(textView19.text.toString())
        }

        imageButton3.setOnClickListener {
            TrocarTela()
        }

        pesquisa.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                pesquisa.hint = ""
            } else {
                pesquisa.hint = "Nome da Obra"
            }
        }

        pesquisa.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                val nomeDaObra = pesquisa.text.toString()
                if (nomeDaObra.isNotEmpty()) {
                    buscarObra(nomeDaObra)
                } else {
                    Toast.makeText(this, "Digite o nome da obra", Toast.LENGTH_SHORT).show()
                }
                true
            } else {
                false
            }
        }
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val locale = Locale.getDefault()
            val result = tts!!.setLanguage(locale)

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "The Language not supported!")
                tts!!.setLanguage(Locale.US)
            }
        } else {
            Log.e("TTS", "Initialization Failed!")
        }
    }

    private fun speakOut(text: String) {
        tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
    }

    private fun buscarObra(nomeDaObra: String) {
        Log.d("Mainadobpt", "Buscando obra: $nomeDaObra")
        db.collection("obras")
            .whereEqualTo("nome", nomeDaObra)
            .get()
            .addOnSuccessListener { documents ->
                if (documents.isEmpty) {
                    Log.d("Mainadobpt", "Obra não encontrada")
                    Toast.makeText(this, "Obra não encontrada", Toast.LENGTH_SHORT).show()
                } else {
                    for (document in documents) {
                        val obra = document.data
                        Log.d("Mainadobpt", "Obra encontrada: $obra")
                        val descricao = obra["descricao"] as? String ?: "Sem descrição"
                        val imageBase64 = obra["imageBase64"] as? String

                        textView19.text = descricao
                        textView19.setTextColor(resources.getColor(android.R.color.white)) // Mudar a cor do texto para branco

                        if (imageBase64 != null) {
                            val decodedByteArray = Base64.decode(imageBase64, Base64.DEFAULT)
                            val bitmap = BitmapFactory.decodeByteArray(decodedByteArray, 0, decodedByteArray.size)
                            imageView2.setImageBitmap(bitmap)
                        } else {
                            imageView2.setImageResource(android.R.color.transparent)
                        }

                        mostrarObra()
                        break
                    }
                }
            }
            .addOnFailureListener { exception ->
                Log.e("Mainadobpt", "Erro ao buscar obra: ", exception)
                Toast.makeText(this, "Erro ao buscar obra", Toast.LENGTH_SHORT).show()
            }
    }

    private fun mostrarObra() {
        Log.d("Mainadobpt", "Mostrando obra buscada")

        // Esconder os elementos não necessários
        findViewById<ImageView>(R.id.imageView26).visibility = View.GONE
        findViewById<ImageView>(R.id.imageView27).visibility = View.GONE
        findViewById<ImageButton>(R.id.imageButton2).visibility = View.GONE
        findViewById<TextView>(R.id.textView21).visibility = View.GONE
        findViewById<ImageView>(R.id.imageView14).visibility = View.GONE

        // Exibir os elementos da obra encontrada
        imageView2.visibility = View.VISIBLE
        imageButton.visibility = View.VISIBLE
        textView19.visibility = View.VISIBLE

        Log.d("Mainadobpt", "Elementos visíveis: imageView2, imageButton, textView19")
        Log.d("Mainadobpt", "Descrição: ${textView19.text}")
    }

    private fun TrocarTela() {
        Log.d("Mainadobpt", "TrocarTela chamada")
        val intent = Intent(this, Mainpt::class.java)
        startActivity(intent)
    }

    public override fun onDestroy() {
        if (tts != null) {
            tts!!.stop()
            tts!!.shutdown()
        }
        super.onDestroy()
    }
}
