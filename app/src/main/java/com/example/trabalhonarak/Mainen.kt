package com.example.trabalhonarak

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class Mainen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_mainen)

        val button = findViewById<ImageButton>(R.id.imageButton10)
        val button1 = findViewById<Button>(R.id.button9)

        button.setOnClickListener(){
            TrocarTela()
        }
        button1.setOnClickListener(){
            TrocarTela1()
        }
    }
    private fun TrocarTela() {
        val intent = Intent(this, Mainadobpt::class.java)
        startActivity(intent)
    }
    private fun TrocarTela1() {
        val intent = Intent(this, Mainaddoben::class.java)
        startActivity(intent)
    }
}