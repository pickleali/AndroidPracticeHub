package com.example.androidpracticehub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText


class MainActivity : AppCompatActivity() {
    private lateinit var submitButton: Button
    private lateinit var firstNameTextField: EditText
    private lateinit var lastNameTextField: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        submitButton = findViewById(R.id.btnSubmit)
//        firstNameTextField = findViewById(R.id.firstNameTextField)
        lastNameTextField = findViewById(R.id.lastNameTextField)

        submitButton.setOnClickListener {
            val userInput = lastNameTextField.text.toString()
            val intent = Intent(this, WelcomeActivity::class.java)
//            intent.putExtra(WelcomeActivity.USERNAME_KEY, userInput)
            startActivity(intent)
        }
    }
}