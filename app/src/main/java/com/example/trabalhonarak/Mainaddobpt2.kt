package com.example.trabalhonarak

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class Mainaddobpt2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_mainaddobpt2)

        val button = findViewById<ImageButton>(R.id.imageButton20)
        val button1 = findViewById<ImageButton>(R.id.imageButton18)
        val button2 = findViewById<Button>(R.id.button15)

        button.setOnClickListener() {
            TrocarTela()
        }
        button1.setOnClickListener() {
            TrocarTela1()
        }
        button2.setOnClickListener() {
            TrocarTela2()
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
    private fun TrocarTela2() {
        val intent1 = Intent(this, Mainlobpt::class.java)
        startActivity(intent1)
    }
}