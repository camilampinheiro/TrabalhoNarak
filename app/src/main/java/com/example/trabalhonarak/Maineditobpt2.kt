package com.example.trabalhonarak

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class Maineditobpt2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_maineditobpt2)

        val button = findViewById<ImageButton>(R.id.imageButton22)
        val button1 = findViewById<ImageButton>(R.id.imageButton21)
        val button2 = findViewById<Button>(R.id.button17)

        button.setOnClickListener(){
            TrocarTela()
        }
        button1.setOnClickListener(){
            TrocarTela1()
        }
        button2.setOnClickListener(){
            TrocarTela2()
        }

        val pesquisa = findViewById<EditText>(R.id.editTextText8)
        pesquisa.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                pesquisa.hint = ""
            } else {
                pesquisa.hint = "Nome da Obra"
            }
        }

        val pesquisa1 = findViewById<EditText>(R.id.editTextText16)
        pesquisa1.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                pesquisa1.hint = ""
            } else {
                pesquisa1.hint = "Informação da Obra..."
            }
        }
    }
    private fun TrocarTela() {
        val intent = Intent(this, Maineditoben2::class.java)
        startActivity(intent)
    }
    private fun TrocarTela1() {
        val intent1 = Intent(this, Mainpt::class.java)
        startActivity(intent1)
    }
    private fun TrocarTela2() {
        val intent2 = Intent(this, Mainadmpt::class.java)
        startActivity(intent2)
    }
}
