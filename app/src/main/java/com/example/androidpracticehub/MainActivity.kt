package com.example.androidpracticehub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText


class MainActivity : AppCompatActivity() {
    lateinit var submitButton: Button
    lateinit var nameTextField: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        submitButton = findViewById(R.id.btnSubmit)
        nameTextField = findViewById(R.id.firstNameTextField)

        submitButton.setOnClickListener {
            val userInput = nameTextField.text.toString()
            val intent = Intent(this, WelcomeActivity::class.java)
            intent.putExtra(WelcomeActivity.USERNAME_KEY, userInput)
            startActivity(intent)
        }
    }
}