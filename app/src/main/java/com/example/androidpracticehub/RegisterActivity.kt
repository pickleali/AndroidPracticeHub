package com.example.androidpracticehub

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

private fun isValidEmail(emailInput: String): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()
}

class RegisterActivity : AppCompatActivity() {
    private lateinit var firstName: EditText
    private lateinit var lastName: EditText
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var registerButton: Button
    private lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        firstName = findViewById(R.id.firstName)
        lastName = findViewById(R.id.lastName)
        email = findViewById(R.id.emailSignUp)
        password = findViewById(R.id.passwordSignUp)
        registerButton = findViewById(R.id.btnSignUp)
        loginButton = findViewById(R.id.btnSignIn)

        val cachingLayer:CachingLayer = CachingLayerImpl(context = this)

        registerButton.setOnClickListener {
            val userEmail = email.text.toString().trim()

            if (isValidEmail(emailInput = userEmail) && password.text.toString().isNotEmpty()) {
                cachingLayer.setName(firstName.text.toString())
                cachingLayer.setEmail(email.text.toString())
                cachingLayer.setPassword(password.text.toString())
                cachingLayer.setLoggedIn(true)
                // All inputs are valid - show Toast Message and redirect to Home(welcome)screen
                Toast.makeText(this, R.string.registered_successfully, Toast.LENGTH_LONG).show()
                Intent(this, HomeActivity::class.java).also {
                    startActivity(it)
                }
            } else {
                // Email input is invalid - error Toast message
                Toast.makeText(this, R.string.invalid_email_password, Toast.LENGTH_LONG).show()
            }
        }
        loginButton.setOnClickListener {
            Intent(this, LoginActivity::class.java).also {
                startActivity(it)
            }
        }

    }
}