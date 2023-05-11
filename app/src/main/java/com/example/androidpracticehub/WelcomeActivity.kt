package com.example.androidpracticehub

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class WelcomeActivity : AppCompatActivity() {

    lateinit var welcomeMessage: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_window)

        welcomeMessage = findViewById(R.id.welcomeTextView)
        val intent = intent
        val userInput = intent.getStringExtra(USERNAME_KEY)
        welcomeMessage.text = "Welcome, $userInput"

    }

    companion object {
        const val USERNAME_KEY = "userInput"
    }
}