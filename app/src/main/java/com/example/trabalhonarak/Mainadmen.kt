package com.example.trabalhonarak

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class Mainadmen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_mainadmen)

        val button = findViewById<ImageButton>(R.id.imageButton13)
        val button1 = findViewById<ImageButton>(R.id.imageButton14)
        val button2 = findViewById<Button>(R.id.button11)
        val button3 = findViewById<Button>(R.id.button12)
        val button4 = findViewById<Button>(R.id.button14)

        button.setOnClickListener() {
            TrocarTela()
        }
        button1.setOnClickListener() {
            TrocarTela1()
        }
        button2.setOnClickListener() {
            TrocarTela2()
        }
        button3.setOnClickListener() {
            TrocarTela3()
        }
        button4.setOnClickListener() {
            TrocarTela4()
        }
    }

    private fun TrocarTela() {
        val intent = Intent(this, Mainen::class.java)
        startActivity(intent)
    }

    private fun TrocarTela1() {
        val intent1 = Intent(this, Mainadmpt::class.java)
        startActivity(intent1)
    }

    private fun TrocarTela2() {
        val intent2 = Intent(this, Mainaddoben::class.java)
        startActivity(intent2)
    }

    private fun TrocarTela3() {
        val intent3 = Intent(this, Mainexoben::class.java)
        startActivity(intent3)
    }

    private fun TrocarTela4() {
        val intent4 = Intent(this, Maineditoben::class.java)
        startActivity(intent4)
    }
}