package com.example.trabalhonarak

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class Mainaddoben : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_mainaddoben)

        val button = findViewById<ImageButton>(R.id.imageButton25)
        val button1 = findViewById<ImageButton>(R.id.imageButton27)
        val button2 = findViewById<Button>(R.id.button18)
        val nome = findViewById<EditText>(R.id.editTextText10)

        button.setOnClickListener() {
            TrocarTela()
        }
        button1.setOnClickListener() {
            TrocarTela1()
        }
        button2.setOnClickListener() {
            FirebaseFirestore.getInstance().collection("obras").add(
                mapOf(
                    "nome" to nome.text.toString()
                )
            )
            TrocarTela2()
        }
    }

    private fun TrocarTela() {
        val intent = Intent(this, Mainoben::class.java)
        startActivity(intent)
    }

    private fun TrocarTela1() {
        val intent1 = Intent(this, Mainaddobpt::class.java)
        startActivity(intent1)
    }

    private fun TrocarTela2() {
        val intent2 = Intent(this, Mainaddoben2::class.java)
        startActivity(intent2)
    }
}
