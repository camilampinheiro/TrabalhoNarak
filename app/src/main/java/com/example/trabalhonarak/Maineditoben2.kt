package com.example.trabalhonarak

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class Maineditoben2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_maineditoben2)

        val button = findViewById<ImageButton>(R.id.imageButton31)
        val button1 = findViewById<ImageButton>(R.id.imageButton32)
        val button2 = findViewById<Button>(R.id.button22)

        button.setOnClickListener(){
            TrocarTela()
        }
        button1.setOnClickListener(){
            TrocarTela1()
        }
        button2.setOnClickListener(){
            TrocarTela2()
        }

        val pesquisa = findViewById<EditText>(R.id.editTextText3)
        pesquisa.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                pesquisa.hint = ""
            } else {
                pesquisa.hint = "Name of the work"
            }
        }

        val pesquisa1 = findViewById<EditText>(R.id.editTextText13)
        pesquisa1.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                pesquisa1.hint = ""
            } else {
                pesquisa1.hint = "Information about the work..."
            }
        }
    }
    private fun TrocarTela() {
        val intent = Intent(this, Mainen::class.java)
        startActivity(intent)
    }
    private fun TrocarTela1() {
        val intent1 = Intent(this, Maineditobpt2::class.java)
        startActivity(intent1)
    }
    private fun TrocarTela2() {
        val intent2 = Intent(this, Mainadmen::class.java)
        startActivity(intent2)
    }
}
