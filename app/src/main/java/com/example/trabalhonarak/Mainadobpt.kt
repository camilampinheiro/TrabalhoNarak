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

        var btn2:ImageButton = findViewById(R.id.imageButton3)
        var button = findViewById<ImageButton>(R.id.imageButton8)

        btn2.setOnClickListener(){
            TrocarTela1()
        }
        button.setOnClickListener(){
            TrocarTela()
        }

    }
    fun TrocarTela1(){
        var intent2 = Intent(this, Mainlobpt::class.java)
        startActivity(intent2)
    }
    fun TrocarTela(){
        var intent2 = Intent(this, Mainoben::class.java)
        startActivity(intent2)
    }
}