package com.example.trabalhonarak

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class Mainexobpt : AppCompatActivity() {

    private lateinit var pesquisa: EditText
    private val db = FirebaseFirestore.getInstance()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_mainexcobpt)

        val button = findViewById<Button>(R.id.button13)
        val button1 = findViewById<ImageButton>(R.id.imageButton13)
        val button2 = findViewById<ImageButton>(R.id.imageButton6)
        pesquisa = findViewById(R.id.editTextText2)

        button.setOnClickListener {
            val nomeDaObra = pesquisa.text.toString()
            if (nomeDaObra.isNotEmpty()) {
                excluirObra(nomeDaObra)
            } else {
                Toast.makeText(this, "Digite o nome da obra", Toast.LENGTH_SHORT).show()
            }
        }

        button1.setOnClickListener {
            TrocarTela1()
        }
        button2.setOnClickListener {
            TrocarTela2()
        }

        pesquisa.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                pesquisa.hint = ""
            } else {
                pesquisa.hint = "Nome da Obra"
            }
        }
    }

    private fun excluirObra(nomeDaObra: String) {
        db.collection("obras")
            .whereEqualTo("nome", nomeDaObra)
            .get()
            .addOnSuccessListener { documents ->
                if (documents.isEmpty) {
                    Toast.makeText(this, "Obra não encontrada", Toast.LENGTH_SHORT).show()
                } else {
                    for (document in documents) {
                        db.collection("obras").document(document.id)
                            .delete()
                            .addOnSuccessListener {
                                Toast.makeText(this, "Obra excluída com sucesso", Toast.LENGTH_SHORT).show()
                                TrocarTela()
                            }
                            .addOnFailureListener { e ->
                                Toast.makeText(this, "Erro ao excluir obra: $e", Toast.LENGTH_SHORT).show()
                            }
                        break // Supondo que estamos interessados apenas na primeira obra encontrada
                    }
                }
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this, "Erro ao buscar obra: $exception", Toast.LENGTH_SHORT).show()
            }
    }

    private fun TrocarTela() {
        val intent = Intent(this, Mainadmpt::class.java)
        startActivity(intent)
    }

    private fun TrocarTela1() {
        val intent1 = Intent(this, Mainadmpt::class.java)
        startActivity(intent1)
    }

    private fun TrocarTela2() {
        val intent2 = Intent(this, Mainpt::class.java)
        startActivity(intent2)
    }
}
