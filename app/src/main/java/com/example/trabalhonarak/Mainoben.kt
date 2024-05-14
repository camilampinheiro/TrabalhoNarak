package com.example.trabalhonarak

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class Mainoben : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_mainoben)
        val button = findViewById<ImageButton>(R.id.imageButton17)
        val button1 = findViewById<ImageButton>(R.id.imageButton19)

        button.setOnClickListener(){
            TrocarTela()
        }
        button1.setOnClickListener(){
            TrocarTela1()
        }
    }
    private fun TrocarTela() {
        val intent = Intent(this, Mainen::class.java)
        startActivity(intent)
    }
    private fun TrocarTela1() {
        val intent = Intent(this, Mainlobpt::class.java)
        startActivity(intent)
    }
}