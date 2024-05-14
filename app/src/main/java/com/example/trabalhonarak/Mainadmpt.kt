package com.example.trabalhonarak

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class Mainadmpt : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_mainadmpt)

        val button = findViewById<ImageButton>(R.id.imageButton3)
        val button1 = findViewById<ImageButton>(R.id.imageButton2)
        val button2 = findViewById<Button>(R.id.button4)
        val button3 = findViewById<Button>(R.id.button5)
        val button4 = findViewById<Button>(R.id.button6)

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
        val intent = Intent(this, Mainlobpt::class.java)
        startActivity(intent)
    }

    private fun TrocarTela1() {
        val intent1 = Intent(this, Mainoben::class.java)
        startActivity(intent1)
    }

    private fun TrocarTela2() {
        val intent1 = Intent(this, Mainadobpt::class.java)
        startActivity(intent1)
    }

    private fun TrocarTela3() {
        val intent1 = Intent(this, Mainexcobpt::class.java)
        startActivity(intent1)
    }

    private fun TrocarTela4() {
        val intent1 = Intent(this, Maineditobpt::class.java)
        startActivity(intent1)
    }
}

