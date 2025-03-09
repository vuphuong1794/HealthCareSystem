package com.example.healthcaresystem

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.widget.Button
import android.widget.TextView
class SignIn : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.sign_in)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_sign_in)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val nutdangki = findViewById<TextView>(R.id.signuplink)
        nutdangki.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent) // Chuyển đến SecondActivity
        }

        val nutdangnhap = findViewById<Button>(R.id.button)
        nutdangnhap.setOnClickListener {
            val intent = Intent(this, MainPage::class.java)
            startActivity(intent) // Chuyển đến SecondActivity
        }

    }
}