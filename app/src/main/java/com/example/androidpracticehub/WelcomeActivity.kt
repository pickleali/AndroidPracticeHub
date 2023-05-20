package com.example.androidpracticehub

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class WelcomeActivity : AppCompatActivity() {

    private lateinit var nameWelcomeText: TextView
    private lateinit var emailWelcomeText: TextView
    private lateinit var logoutButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_window)
        nameWelcomeText = findViewById(R.id.nameWelcomeText)
        emailWelcomeText = findViewById(R.id.emailWelcomeText)
        val sharedPref = getSharedPreferences(RegisterActivity.Constants.SHARED_PREFS_NAME, MODE_PRIVATE)
        nameWelcomeText.text = getString(
            R.string.welcome_message,
            sharedPref.getString(RegisterActivity.Constants.PREFS_FIRST_NAME_KEY, null)
        )
        emailWelcomeText.text = sharedPref.getString(RegisterActivity.Constants.PREFS_EMAIL_KEY, null)

        logoutButton = findViewById(R.id.logoutBtn)
        logoutButton.setOnClickListener {

            // change Login value --> false
            sharedPref.edit().apply {
                putBoolean(RegisterActivity.Constants.PREFS_LOGIN_KEY, false)
                apply()
            }
            // redirect to login screen
            Intent(this, MainActivity::class.java).also {
                startActivity(it)
            }

        }
    }



    companion object {
        const val USERNAME_KEY = "userInput"
    }
}