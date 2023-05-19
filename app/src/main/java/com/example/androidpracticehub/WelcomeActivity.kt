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
        val sharedPref = getSharedPreferences(RegisterActivity.Constants.SHARED_PREFS_NAME, 0)
        welcomeMessage.text = getString(
            R.string.welcome_message,
            sharedPref.getString(RegisterActivity.Constants.PREFS_FIRST_NAME_KEY, null)
        )
    }

    companion object {
        const val USERNAME_KEY = "userInput"
    }
}