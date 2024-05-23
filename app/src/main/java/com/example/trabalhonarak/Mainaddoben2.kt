package com.example.trabalhonarak

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class Mainaddoben2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_mainaddoben2)

        val button = findViewById<ImageButton>(R.id.imageButton26)
        val button1 = findViewById<ImageButton>(R.id.imageButton28)
        val button2 = findViewById<Button>(R.id.button19)
        val desc = findViewById<EditText>(R.id.editTextText11)

        button.setOnClickListener() {
            TrocarTela()
        }
        button1.setOnClickListener() {
            TrocarTela1()
        }
        button2.setOnClickListener() {
            FirebaseFirestore.getInstance().collection("obras").add(
                mapOf(
                    "desc" to desc.text.toString()
                )
            )
            TrocarTela2()
        }

        val pesquisa = findViewById<EditText>(R.id.editTextText11)
        pesquisa.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                pesquisa.hint = ""
            } else {
                pesquisa.hint = "Name of the work"
            }
        }

        val pesquisa1 = findViewById<EditText>(R.id.editTextText5)
        pesquisa1.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                pesquisa1.hint = ""
            } else {
                pesquisa1.hint = "Information about the work"
            }
        }
    }

    private fun TrocarTela() {
        val intent = Intent(this, Mainoben::class.java)
        startActivity(intent)
    }

    private fun TrocarTela1() {
        val intent1 = Intent(this, Mainaddobpt2::class.java)
        startActivity(intent1)
    }
    private fun TrocarTela2() {
        val intent1 = Intent(this, Mainadmen::class.java)
        startActivity(intent1)
    }
}
