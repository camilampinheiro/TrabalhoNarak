package com.example.trabalhonarak

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class Mainobpt : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_mainobpt)

        val button = findViewById<ImageButton>(R.id.imageButton3)
        val buttonVoltar = findViewById<ImageButton>(R.id.imageButton10)

        button.setOnClickListener() {
            TrocarTela()
        }

        buttonVoltar.setOnClickListener() {
            TrocarTela1()
        }
    }
    private fun TrocarTela() {
        val intent = Intent(this, Mainpt::class.java)
        startActivity(intent)
    }
    private fun TrocarTela1() {
        val intent1 = Intent(this, Mainadobpt::class.java)
        startActivity(intent1)
    }
}