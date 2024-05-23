package com.example.trabalhonarak

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class Mainlogpt : AppCompatActivity() {
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_mainlogpt)

        db = FirebaseFirestore.getInstance()

        val registerButton = findViewById<ImageButton>(R.id.imageButton)
        val loginButton = findViewById<Button>(R.id.button3)
        val anotherButton = findViewById<ImageButton>(R.id.imageButton3)

        registerButton.setOnClickListener {
            registerUser()
        }
        loginButton.setOnClickListener {
            login()
        }
        anotherButton.setOnClickListener {
            TrocarTela2()
        }

        val login = findViewById<EditText>(R.id.editTextTextEmailAddress)
        login.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                login.hint = ""
            } else {
                login.hint = "Email"
            }
        }

        val senha = findViewById<EditText>(R.id.editTextTextPassword)
        senha.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                senha.hint = ""
            } else {
                senha.hint = "Senha"
            }
        }
    }

    private fun registerUser() {
        val email = "usuario@gmail.com"
        val password = "12345678"
        addUserToFirestore(email, password)
    }

    private fun addUserToFirestore(email: String, password: String) {
        val user = hashMapOf("email" to email, "password" to password)
        db.collection("login")
            .add(user)
            .addOnSuccessListener {
                Toast.makeText(this, "User added to Firestore.", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Error adding user to Firestore: ${e.message}", Toast.LENGTH_LONG).show()
            }
    }

    private fun login() {
        val emailEditText = findViewById<EditText>(R.id.editTextTextEmailAddress)
        val passwordEditText = findViewById<EditText>(R.id.editTextTextPassword)

        val email = emailEditText.text.toString()
        val password = passwordEditText.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter both email and password.", Toast.LENGTH_SHORT).show()
            return
        }

        db.collection("login")
            .whereEqualTo("email", email)
            .whereEqualTo("password", password)
            .get()
            .addOnSuccessListener { documents ->
                if (documents.isEmpty) {
                    Toast.makeText(this, "Invalid email or password.", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Login successful.", Toast.LENGTH_SHORT).show()
                    TrocarTela1()
                }
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Error logging in: ${e.message}", Toast.LENGTH_LONG).show()
            }
    }

    private fun TrocarTela() {
        val intent = Intent(this, Mainlogen::class.java)
        startActivity(intent)
    }

    private fun TrocarTela1() {
        val intent1 = Intent(this, Mainadmpt::class.java)
        startActivity(intent1)
    }

    private fun TrocarTela2() {
        val intent2 = Intent(this, Mainpt::class.java)
        startActivity(intent2)
    }
}
