package com.example.trabalhonarak

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class MainADM : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_mainaddobpt)
        val nome = findViewById<EditText>(R.id.editTextText6)
        val adicionar = findViewById<Button>(R.id.button7)

        adicionar.setOnClickListener(){
            FirebaseFirestore.getInstance().collection("obras").add(mapOf(
                "nome" to nome.text.toString()
            ))
        }

    }
}
