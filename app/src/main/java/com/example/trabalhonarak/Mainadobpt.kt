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

        btn2.setOnClickListener(){
            TrocarTela2()
        }

    }
    fun TrocarTela2(){
        var intent2 = Intent(this, Maineditobpt::class.java)
        startActivity(intent2)
    }
}