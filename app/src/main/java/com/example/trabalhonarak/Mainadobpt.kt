package com.example.trabalhonarak

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.KeyEvent
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.util.*

class Mainadobpt : AppCompatActivity(), TextToSpeech.OnInitListener {

    private lateinit var pesquisa: EditText
    private lateinit var imageButton3: ImageButton
    private lateinit var rv: RecyclerView
    private val db = FirebaseFirestore.getInstance()
    private var tts: TextToSpeech? = null
    private val obras = ArrayList<Obra>()
    private lateinit var adapter: MyAdapter

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.act_rv)

        // Inicializando as views
        rv = findViewById(R.id.rv)
        rv.layoutManager = LinearLayoutManager(this)
        adapter = MyAdapter(obras)
        rv.adapter = adapter

        pesquisa = findViewById(R.id.editTextText4)
        imageButton3 = findViewById(R.id.imageButton3)

        tts = TextToSpeech(this, this)

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

    private fun buscarObra(nomeDaObra: String) {
        Log.d("Mainadobpt", "Buscando obra: $nomeDaObra")
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val querySnapshot = db.collection("obras")
                    .whereEqualTo("nome", nomeDaObra)
                    .get()
                    .await()

                obras.clear()
                if (querySnapshot.isEmpty) {
                    Log.d("Mainadobpt", "Obra não encontrada")
                    Toast.makeText(this@Mainadobpt, "Obra não encontrada", Toast.LENGTH_SHORT).show()
                } else {
                    for (document in querySnapshot.documents) {
                        val obraData = document.data
                        val descricao = obraData?.get("descricao") as? String ?: "Sem descrição"
                        val imageBase64 = obraData?.get("imageBase64") as? String
                        val nome = obraData?.get("nome") as? String ?: "Sem nome"

                        val obra = if (imageBase64 != null) {
                            Obra(nome, imageBase64, descricao)
                        } else {
                            Obra(nome, "", descricao)
                        }
                        obras.add(obra)
                    }
                    adapter.notifyDataSetChanged()
                }
            } catch (e: Exception) {
                Log.e("Mainadobpt", "Erro ao buscar obra: ", e)
                Toast.makeText(this@Mainadobpt, "Erro ao buscar obra", Toast.LENGTH_SHORT).show()
            }
        }
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
