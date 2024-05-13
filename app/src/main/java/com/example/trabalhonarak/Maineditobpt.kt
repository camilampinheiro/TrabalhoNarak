package com.example.trabalhonarak

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class Maineditobpt : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_maineditobpt)

    }
    fun TrocarTela(){
        var intent = Intent(this, Mainadobpt::class.java)
        startActivity(intent)
    }
}