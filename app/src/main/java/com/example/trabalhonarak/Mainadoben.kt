package com.example.trabalhonarak

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class Mainadoben : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_mainadoben)

        val button = findViewById<ImageButton>(R.id.imageButton23)
        val button1 = findViewById<ImageButton>(R.id.imageButton24)

        button.setOnClickListener {
            TrocarTela()
        }
        button1.setOnClickListener {
            TrocarTela1()
        }

        val pesquisa = findViewById<EditText>(R.id.editTextText9)
        pesquisa.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                pesquisa.hint = ""
            } else {
                pesquisa.hint = "Nome da Obra"
            }
        }
    }

    private fun TrocarTela() {
        val intent = Intent(this, Mainen::class.java)
        startActivity(intent)
    }

    private fun TrocarTela1() {
        val intent1 = Intent(this, Mainadobpt::class.java)
        startActivity(intent1)
    }
}
