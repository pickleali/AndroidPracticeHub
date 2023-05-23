package com.example.androidpracticehub

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {

    private lateinit var nameWelcomeText: TextView
    private lateinit var emailWelcomeText: TextView
    private lateinit var logoutButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_window)
        nameWelcomeText = findViewById(R.id.nameWelcomeText)
        emailWelcomeText = findViewById(R.id.emailWelcomeText)
        val cachingLayer:CachingLayer = CachingLayerImpl(context = this)
        nameWelcomeText.text = getString(
            R.string.welcome_message,
            cachingLayer.getName()
        )
        emailWelcomeText.text = cachingLayer.getEmail()


        logoutButton = findViewById(R.id.logoutBtn)
        logoutButton.setOnClickListener {

            // change Login value --> false
            cachingLayer.setLoggedIn(false)
            // redirect to login screen
            Intent(this, LoginActivity::class.java).also {
                startActivity(it)
                finish() // destroy the current activity (HomeActivity) after calling the next activity (LoginActivity)
            }

        }
    }



    companion object {
        const val USERNAME_KEY = "userInput"
    }
}