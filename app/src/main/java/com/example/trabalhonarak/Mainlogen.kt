package com.example.trabalhonarak

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class Mainlogen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_mainlogen)

        val button = findViewById<Button>(R.id.button10)
        val button1 = findViewById<ImageButton>(R.id.imageButton12)
        val button2 = findViewById<ImageButton>(R.id.imageButton11)

        button.setOnClickListener() {
            TrocarTela()
        }
        button1.setOnClickListener() {
            TrocarTela1()
        }
        button2.setOnClickListener() {
            TrocarTela2()
        }

        val login = findViewById<EditText>(R.id.editTextTextEmailAddress4)
        login.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                login.hint = ""
            } else {
                login.hint = "Email"
            }
        }

        val senha = findViewById<EditText>(R.id.editTextTextPassword2)
        senha.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                senha.hint = ""
            } else {
                senha.hint = "Password"
            }
        }
    }

    private fun TrocarTela() {
        val intent = Intent(this, Mainadmen::class.java)
        startActivity(intent)
    }
    private fun TrocarTela1() {
        val intent1 = Intent(this, Mainlogpt::class.java)
        startActivity(intent1)
    }
    private fun TrocarTela2() {
        val intent2 = Intent(this, Mainen::class.java)
        startActivity(intent2)
    }
}
