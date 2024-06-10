package com.example.trabalhonarak

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.KeyEvent
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore

class Maineditobpt : AppCompatActivity() {

    private lateinit var pesquisa: EditText
    private lateinit var imageButton14: ImageButton
    private lateinit var imageButton15: ImageButton
    private lateinit var rv: RecyclerView
    private val db = FirebaseFirestore.getInstance()
    private val obras = ArrayList<Obra>()
    private lateinit var adapter: EditAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_maineditobpt)

        // Inicializando as views
        rv = findViewById(R.id.rv)
        rv.layoutManager = LinearLayoutManager(this)
        adapter = EditAdapter(obras)
        rv.adapter = adapter

        pesquisa = findViewById(R.id.editTextText)
        imageButton14 = findViewById(R.id.imageButton14)
        imageButton15 = findViewById(R.id.imageButton15)

        imageButton14.setOnClickListener {
            TrocarTela()
        }

        imageButton15.setOnClickListener {
            TrocarTela1()
        }

        pesquisa.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                pesquisa.hint = ""
            } else {
                pesquisa.hint = getString(R.string.pesquisaEditar)
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

    private fun buscarObra(nomeDaObra: String) {
        Log.d("Maineditobpt", "Buscando obra: $nomeDaObra")
        db.collection("obras")
            .whereEqualTo("nome", nomeDaObra)
            .get()
            .addOnSuccessListener { documents ->
                obras.clear()
                if (documents.isEmpty) {
                    Log.d("Maineditobpt", "Obra não encontrada")
                    Toast.makeText(this, "Obra não encontrada", Toast.LENGTH_SHORT).show()
                } else {
                    for (document in documents) {
                        val obraData = document.data
                        val descricao = obraData["descricao"] as? String ?: "Sem descrição"
                        val imageBase64 = obraData["imageBase64"] as? String
                        val nome = obraData["nome"] as? String ?: "Sem nome"

                        val obra = if (imageBase64 != null) {
                            val decodedByteArray = Base64.decode(imageBase64, Base64.DEFAULT)
                            val bitmap = BitmapFactory.decodeByteArray(decodedByteArray, 0, decodedByteArray.size)
                            Obra(nome, imageBase64, descricao)
                        } else {
                            Obra(nome, "", descricao)
                        }
                        obras.add(obra)
                    }
                    adapter.notifyDataSetChanged()
                }
            }
            .addOnFailureListener { exception ->
                Log.e("Maineditobpt", "Erro ao buscar obra: ", exception)
                Toast.makeText(this, "Erro ao buscar obra", Toast.LENGTH_SHORT).show()
            }
    }

    private fun TrocarTela() {
        val intent = Intent(this, Mainadmpt::class.java)
        startActivity(intent)
    }

    private fun TrocarTela1() {
        val intent = Intent(this, Mainpt::class.java)
        startActivity(intent)
    }
}