package com.example.trabalhonarak

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Mainpt : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_mainpt)

        val button = findViewById<Button>(R.id.button)
        val button1 = findViewById<Button>(R.id.button2)
        val button2 = findViewById<ImageButton>(R.id.imageButton)

        button.setOnClickListener(){
            TrocarTela()
        }
        button1.setOnClickListener(){
            TrocarTela1()
        }
        button2.setOnClickListener(){
            TrocarTela2()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    fun TrocarTela(){
        val intent = Intent(this, Mainadobpt::class.java)
        startActivity(intent)
    }

    fun TrocarTela1(){
        val intent1 = Intent(this, Mainlogpt::class.java)
        startActivity(intent1)
    }

    fun TrocarTela2(){
        val intent2 = Intent(this, Mainen::class.java)
        startActivity(intent2)
    }
}