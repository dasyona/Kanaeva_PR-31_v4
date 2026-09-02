package com.example.kanaevadm_pr_31_04

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var loginEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var registerButton: Button
    private lateinit var sharedPrefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.loginactivity)

        loginEditText = findViewById(R.id.loginEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        registerButton = findViewById(R.id.registerButton)

        sharedPrefs = getSharedPreferences("MyPrefs", MODE_PRIVATE)

        registerButton.setOnClickListener {
            val login = loginEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (login.isEmpty() || password.isEmpty()) {
                // Показываем Toast или AlertDialog
                Toast.makeText(this, "Введите логин и пароль", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Проверяем, сохранены ли логин и пароль
            val savedLogin = sharedPrefs.getString("login", null)
            val savedPassword = sharedPrefs.getString("password", null)

            if (savedLogin == null && savedPassword == null) {
                // Первый вход – сохраняем
                sharedPrefs.edit().apply {
                    putString("login", login)
                    putString("password", password)
                    apply()
                }
                // Переход на второй экран
                startActivity(Intent(this, figureactivity::class.java))
            } else {
                // Проверяем соответствие с сохранёнными (ects / ects2023)
                if (login == savedLogin && password == savedPassword) {
                    startActivity(Intent(this, figureactivity::class.java))
                } else {
                    Toast.makeText(this, "Неверный логин или пароль", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}



