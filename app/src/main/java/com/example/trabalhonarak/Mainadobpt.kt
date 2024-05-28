package com.example.trabalhonarak

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class Mainadobpt: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_mainadobpt)

        val button = findViewById<ImageButton>(R.id.imageButton3)
        val button1 = findViewById<ImageView>(R.id.imageView24)
        val button2 = findViewById<ImageView>(R.id.imageView25)
        val buttonObra = findViewById<ImageView>(R.id.imageView27)
        val buttonObra1 = findViewById<ImageView>(R.id.imageView26)

        button.setOnClickListener(){
            TrocarTela()
        }
        button1.setOnClickListener(){
            TrocarTela1()
        }
        button2.setOnClickListener(){
            TrocarTela2()
        }
        buttonObra.setOnClickListener(){
            TrocarTela3()
        }
        buttonObra1.setOnClickListener(){
            TrocarTela4()
        }

        val pesquisa = findViewById<EditText>(R.id.editTextText4)
        pesquisa.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                pesquisa.hint = ""
            } else {
                pesquisa.hint = "Nome da Obra"
            }
        }
    }

    fun TrocarTela(){
        var intent = Intent(this, Mainpt::class.java)
        startActivity(intent)
    }
    fun TrocarTela1(){
        var intent1 = Intent(this, Mainobpt::class.java)
        startActivity(intent1)
    }
    fun TrocarTela2(){
        var intent2 = Intent(this, Mainob1::class.java)
        startActivity(intent2)
    }
    fun TrocarTela3(){
        var intent3 = Intent(this, Mainobpt::class.java)
        startActivity(intent3)
    }
    fun TrocarTela4(){
        var intent4 = Intent(this, Mainob1::class.java)
        startActivity(intent4)
    }
}