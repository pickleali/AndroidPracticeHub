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

        val sharedPreferences =
            getSharedPreferences(Constants.SHARED_PREFS_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        registerButton.setOnClickListener {
            val userEmail = email.text.toString().trim()

            if (isValidEmail(emailInput = userEmail)) {
                editor.apply {
                    putString(Constants.PREFS_FIRST_NAME_KEY, firstName.text.toString())
                    putString(Constants.PREFS_LAST_NAME_KEY, lastName.text.toString())
                    putString(Constants.PREFS_EMAIL_KEY, email.text.toString())
                    putString(Constants.PREFS_PASSWORD_KEY, password.text.toString())
                    putBoolean(Constants.PREFS_LOGIN_KEY, true)
                    apply()
                }
                // All inputs are valid - show Toast Message and redirect to Home(welcome)screen
                Toast.makeText(this, R.string.registered_successfully, Toast.LENGTH_LONG).show()
                Intent(this, WelcomeActivity::class.java).also {
                    startActivity(it)
                }
            } else {
                // Email input is invalid - error Toast message
                Toast.makeText(this, R.string.invalid_email, Toast.LENGTH_LONG).show()
            }
        }
        loginButton.setOnClickListener {
            Intent(this, MainActivity::class.java).also {
                startActivity(it)
            }
        }

    }

    object Constants {
        const val SHARED_PREFS_NAME = "prefs"
        const val PREFS_FIRST_NAME_KEY = "firstName"
        const val PREFS_LAST_NAME_KEY = "lastName"
        const val PREFS_EMAIL_KEY = "email"
        const val PREFS_PASSWORD_KEY = "password"
        const val PREFS_LOGIN_KEY = "login"
    }
}