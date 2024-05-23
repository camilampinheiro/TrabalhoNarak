package com.example.trabalhonarak

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class Mainaddobpt : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_mainaddobpt)

        val nome = findViewById<EditText>(R.id.editTextText6)
        val button = findViewById<ImageButton>(R.id.imageButton4)
        val button1 = findViewById<ImageButton>(R.id.imageButton5)
        val button2 = findViewById<Button>(R.id.button7)

        button.setOnClickListener() {
            TrocarTela()
        }
        button1.setOnClickListener() {
            TrocarTela1()
        }
        button2.setOnClickListener() {
            FirebaseFirestore.getInstance().collection("obras").add(
                mapOf(
                    "nome" to nome.text.toString()
                )
            )
            TrocarTela2()
        }

        val pesquisa = findViewById<EditText>(R.id.editTextText6)
        pesquisa.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                pesquisa.hint = ""
            } else {
                pesquisa.hint = "Nome da Obra"
            }
        }
    }

    private fun TrocarTela() {
        val intent = Intent(this, Mainpt::class.java)
        startActivity(intent)
    }

    private fun TrocarTela1() {
        val intent1 = Intent(this, Mainaddoben::class.java)
        startActivity(intent1)
    }

    private fun TrocarTela2() {
        val intent2 = Intent(this, Mainaddobpt2::class.java)
        startActivity(intent2)
    }
}
