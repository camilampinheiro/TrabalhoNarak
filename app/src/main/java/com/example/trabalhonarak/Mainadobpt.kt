package com.example.trabalhonarak

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class Mainadobpt: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_mainadobpt)

        val button = findViewById<ImageButton>(R.id.imageButton3)
        val button1 = findViewById<ImageButton>(R.id.imageButton8)

        button.setOnClickListener(){
            TrocarTela()
        }
        button1.setOnClickListener(){
            TrocarTela1()
        }

    }

    fun TrocarTela(){
        var intent = Intent(this, Mainpt::class.java)
        startActivity(intent)
    }
    fun TrocarTela1(){
        var intent1 = Intent(this, Mainadoben::class.java)
        startActivity(intent1)
    }
}