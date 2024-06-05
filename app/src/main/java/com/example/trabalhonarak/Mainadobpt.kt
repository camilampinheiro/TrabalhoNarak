package com.example.trabalhonarak

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

class Mainadobpt : AppCompatActivity(), TextToSpeech.OnInitListener {

    private lateinit var pesquisa: EditText
    private lateinit var imageView24: ImageView
    private lateinit var imageView25: ImageView
    private lateinit var imageView26: ImageView
    private lateinit var imageView27: ImageView
    private lateinit var textView19: TextView
    private lateinit var textView21: TextView
    private lateinit var imageButton3: ImageButton
    private lateinit var imageButton2: ImageButton
    private lateinit var imageButton: ImageButton
    private val db = FirebaseFirestore.getInstance()
    private var tts: TextToSpeech? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_mainadobpt)

        imageButton = findViewById(R.id.imageButton)
        imageButton3 = findViewById(R.id.imageButton3)
        imageButton2 = findViewById(R.id.imageButton2)
        pesquisa = findViewById(R.id.editTextText4)
        textView19 = findViewById(R.id.textView19)
        textView21 = findViewById(R.id.textView21)
        imageView26 = findViewById(R.id.imageView26)
        imageView27 = findViewById(R.id.imageView27)

        tts = TextToSpeech(this, this)

        imageButton.setOnClickListener {
            speakOut(textView19.text.toString())
        }

        imageButton2.setOnClickListener {
            speakOut(textView21.text.toString())
        }

        imageButton3.setOnClickListener {
            TrocarTela()
        }

        pesquisa.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                pesquisa.hint = ""
            } else {
                pesquisa.hint = getString(R.string.pesquisaAdOb)
            }
        }

        pesquisa.setOnEditorActionListener { _, _, _ ->
            val nomeDaObra = pesquisa.text.toString()
            if (nomeDaObra.isNotEmpty()) {
                buscarObra(nomeDaObra)
            } else {
                Toast.makeText(this, "Digite o nome da obra", Toast.LENGTH_SHORT).show()
            }
            true
        }
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val locale = Locale.getDefault()
            val result = tts!!.setLanguage(locale)

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "The Language not supported!")
                // Tenta usar o inglês como fallback
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
                    Toast.makeText(this, "Obra não encontrada", Toast.LENGTH_SHORT).show()
                } else {
                    for (document in documents) {
                        val obra = document.data
                        Log.d("Mainadobpt", "Obra encontrada: $obra")
                        val descricao = obra["descricao"] as? String ?: "Sem descrição"
                        val imageBase64 = obra["imageBase64"] as? String

                        textView19.text = nomeDaObra
                        textView21.text = descricao

                        if (imageBase64 != null) {
                            val decodedByteArray = Base64.decode(imageBase64, Base64.DEFAULT)
                            val bitmap = BitmapFactory.decodeByteArray(decodedByteArray, 0, decodedByteArray.size)
                            imageView24.setImageBitmap(bitmap)
                        } else {
                            imageView24.setImageResource(android.R.color.transparent)
                        }

                        esconderExemplos()

                        break
                    }
                }
            }
            .addOnFailureListener { exception ->
                Log.e("Mainadobpt", "Erro ao buscar obra: ", exception)
                Toast.makeText(this, "Erro ao buscar obra", Toast.LENGTH_SHORT).show()
            }
    }

    private fun esconderExemplos() {
        Log.d("Mainadobpt", "Escondendo exemplos")
        imageView25.visibility = View.GONE
        imageView26.visibility = View.GONE
        imageView27.visibility = View.GONE
        imageButton.visibility = View.GONE
        imageButton2.visibility = View.GONE
        textView19.visibility = View.VISIBLE
        textView21.visibility = View.VISIBLE
    }

    private fun TrocarTela() {
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
