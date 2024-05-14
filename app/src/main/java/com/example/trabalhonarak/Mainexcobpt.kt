package com.example.trabalhonarak

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class Mainexcobpt : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_mainexcobpt)

        val button = findViewById<Button>(R.id.button13)
        val button1 = findViewById<ImageButton>(R.id.imageButton7)
        val button2 = findViewById<ImageButton>(R.id.imageButton6)

        button.setOnClickListener(){
            TrocarTela()
        }
        button1.setOnClickListener(){
            TrocarTela1()
        }
        button2.setOnClickListener(){
            TrocarTela2()
        }
    }
    private fun TrocarTela() {
        val intent = Intent(this, Mainadobpt::class.java)
        startActivity(intent)
    }
    private fun TrocarTela1() {
        val intent = Intent(this, Mainoben::class.java)
        startActivity(intent)
    }
    private fun TrocarTela2() {
        val intent = Intent(this, Mainadobpt::class.java)
        startActivity(intent)
    }
}
