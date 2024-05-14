package com.example.trabalhonarak

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class Mainlobpt : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_mainobpt)
        val button = findViewById<ImageButton>(R.id.imageButton9)
        val button1 = findViewById<ImageButton>(R.id.imageButton3)

        button.setOnClickListener() {
            TrocarTela()
        }
        button1.setOnClickListener() {
            TrocarTela1()
        }
    }

    private fun TrocarTela() {
        val intent = Intent(this, Mainoben::class.java)
        startActivity(intent)
    }

    private fun TrocarTela1() {
        val intent1 = Intent(this, Mainlobpt::class.java)
        startActivity(intent1)
    }
}