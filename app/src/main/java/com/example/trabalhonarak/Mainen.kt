package com.example.trabalhonarak

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class Mainen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_mainen)

        val button = findViewById<ImageButton>(R.id.imageButton10)

        button.setOnClickListener(){
            TrocarTela()
        }
    }
    private fun TrocarTela() {
        val intent = Intent(this, Mainadobpt::class.java)
        startActivity(intent)
    }
}