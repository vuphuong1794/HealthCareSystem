package com.example.healthcaresystem

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.example.healthcaresystem.api.RetrofitInstance
import com.example.healthcaresystem.model.LoginRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
        val returnButton = findViewById<ImageButton>(R.id.returnButton)
        returnButton.setOnClickListener {
            val intent = Intent(this, StartScreen::class.java)
            startActivity(intent) // Chuyển đến SecondActivity
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right) // Slide left when going back
        }

        val emailInput = findViewById<EditText>(R.id.email)
        val passwordInput = findViewById<EditText>(R.id.pass)

        nutdangnhap.setOnClickListener{
            val email = emailInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()

            if(email.isEmpty() || password.isEmpty()){
                Toast.makeText(this, "Vui lòng điền đầy đủ thông tin"+ password, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            userLogin(email, password)
        }
    }

    private fun userLogin(email: String, password: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = RetrofitInstance.api.login(LoginRequest(email, password))

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        val loginResponse = response.body()
                        val token = loginResponse?.accessToken // Lấy token từ phản hồi API

                        if (token != null) {
                            saveToken(token) // Lưu token vào SharedPreferences
                        }

                        Toast.makeText(this@SignIn, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@SignIn, MainPage::class.java))
                    } else {
                        val errorBody = response.errorBody()?.string()
                        Toast.makeText(this@SignIn, "Lỗi: $errorBody", Toast.LENGTH_SHORT).show()

                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@SignIn, "Lỗi: ${e.message}", Toast.LENGTH_SHORT).show()
                    println("Lỗi API: $e")
                }
            }
        }
    }

    private fun saveToken(token: String) {
        val sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("access_token", token)
        editor.apply()
    }


}