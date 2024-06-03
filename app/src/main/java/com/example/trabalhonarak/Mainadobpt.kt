package com.example.trabalhonarak

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
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

class Mainadobpt : AppCompatActivity() {

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_mainadobpt)

        imageButton = findViewById(R.id.imageButton)
        imageButton3 = findViewById(R.id.imageButton3)
        imageButton2 = findViewById(R.id.imageButton2)
        imageView24 = findViewById(R.id.imageView24)
        imageView25 = findViewById(R.id.imageView25)
        imageView26 = findViewById(R.id.imageView26)
        imageView27 = findViewById(R.id.imageView27)
        pesquisa = findViewById(R.id.editTextText4)
        textView19 = findViewById(R.id.textView19) // Nome da obra
        textView21 = findViewById(R.id.textView21) // Descrição da obra

        imageButton3.setOnClickListener {
            TrocarTela()
        }
        imageView24.setOnClickListener {
            TrocarTela1()
        }
        imageView25.setOnClickListener {
            TrocarTela2()
        }
        imageView27.setOnClickListener {
            TrocarTela3()
        }
        imageView26.setOnClickListener {
            TrocarTela4()
        }

        pesquisa.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                pesquisa.hint = ""
            } else {
                pesquisa.hint = "Nome da Obra"
            }
        }

        // Adicionar listener para buscar obra quando o usuário terminar de digitar
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
                        // Exibir as informações da obra
                        val descricao = obra["descricao"] as? String ?: "Sem descrição"
                        val imageBase64 = obra["imageBase64"] as? String

                        textView19.text = nomeDaObra
                        textView21.text = descricao

                        if (imageBase64 != null) {
                            // Decodificar a imagem em Base64 e exibir
                            val decodedByteArray = Base64.decode(imageBase64, Base64.DEFAULT)
                            val bitmap = BitmapFactory.decodeByteArray(decodedByteArray, 0, decodedByteArray.size)
                            imageView24.setImageBitmap(bitmap)
                        } else {
                            imageView24.setImageResource(android.R.color.transparent)
                        }

                        // Esconder componentes de exemplo
                        esconderExemplos()

                        break // Supondo que estamos interessados apenas na primeira obra encontrada
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
    private fun TrocarTela1() {
        val intent1 = Intent(this, Mainobpt::class.java)
        startActivity(intent1)
    }
    private fun TrocarTela2() {
        val intent2 = Intent(this, Mainob1::class.java)
        startActivity(intent2)
    }
    private fun TrocarTela3() {
        val intent3 = Intent(this, Mainobpt::class.java)
        startActivity(intent3)
    }
    private fun TrocarTela4() {
        val intent4 = Intent(this, Mainob1::class.java)
        startActivity(intent4)
    }
}
