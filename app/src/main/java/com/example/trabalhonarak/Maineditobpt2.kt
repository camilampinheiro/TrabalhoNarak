package com.example.trabalhonarak

import android.content.Intent
import android.os.Bundle
import android.widget.Button
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

        button.setOnClickListener(){
            TrocarTela()
        }
        button1.setOnClickListener(){
            TrocarTela1()
        }
    }
    private fun TrocarTela() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
    private fun TrocarTela1() {
        val intent = Intent(this, Mainadobpt::class.java)
        startActivity(intent)
    }
}
