package com.example.trabalhonarak

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class MainADM2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_mainaddobpt2)
        val desc = findViewById<EditText>(R.id.editTextText7)
        val add = findViewById<Button>(R.id.button15)

        add.setOnClickListener(){
            FirebaseFirestore.getInstance().collection("obras").add(mapOf(
                "descriçao" to desc.text.toString()
            ))
        }

    }
}
