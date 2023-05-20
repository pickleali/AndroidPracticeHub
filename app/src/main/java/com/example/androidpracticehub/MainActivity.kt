/**
 * This project aims to practice the following concepts:
 * View elements (Buttons, TextViews, etc..), Animation,
 * SharedPreferences, Intents, Activities, Toasts, and Kotlin fundamentals (visibility modifiers, navigation, and more).
 * The following links are resources I learned from to accomplish this project:
 * 1. https://www.codebrainer.com/blog/sharedpreferences
 * 2. https://developer.android.com/develop/ui/views/launch/splash-screen
 * 3. https://www.geeksforgeeks.org/how-to-create-an-animated-splash-screen-in-android/
 * 4. https://www.kodeco.com/android/paths/learn
 * */

package com.example.androidpracticehub

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

private fun isValidEmail(email: String): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

class MainActivity : AppCompatActivity() {
    private lateinit var emailTextField: EditText
    private lateinit var passwordTextField: EditText
    private lateinit var loginButton: Button
    private lateinit var signupButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        emailTextField = findViewById(R.id.emailTextField)
        passwordTextField = findViewById(R.id.passwordTextField)

        loginButton = findViewById(R.id.btnSignIn)
        signupButton = findViewById(R.id.btnSignUp)

        val sharedPreferences = getSharedPreferences(RegisterActivity.Constants.SHARED_PREFS_NAME, Context.MODE_PRIVATE)
        val isLoggedIn = sharedPreferences.getBoolean(RegisterActivity.Constants.PREFS_LOGIN_KEY, false)
        // validate if user is logged in --> redirect to welcomeActivity
        Log.d("isLoggedIn", isLoggedIn.toString())
        if (isLoggedIn) {
            val intent = Intent(this, WelcomeActivity::class.java)
            startActivity(intent)
        }


        loginButton.setOnClickListener {
            val userEmail = emailTextField.text.toString().trim()
            val userPassword = passwordTextField.text.toString().trim()

            // check if: 1.email is valid 2. registered or not
            if (isCredentialsCorrect(emailInput = userEmail, passwordInput = userPassword) && isValidEmail(userEmail)) {
                // change Login boolean value --> true
                sharedPreferences.edit().apply {
                    putBoolean(RegisterActivity.Constants.PREFS_LOGIN_KEY, true)
                    apply()
                }

                Toast.makeText(this, R.string.login_successfully, Toast.LENGTH_LONG).show()

                val intent = Intent(this, WelcomeActivity::class.java)
                intent.putExtra(WelcomeActivity.USERNAME_KEY, userEmail)
                startActivity(intent)
            } else {
                // NO ACCOUNT WITH THE FOLLOWING EMAIL -- TOAST MESSAGE
                Toast.makeText(this, R.string.email_password_incorrect, Toast.LENGTH_LONG).show()
            }
        }

        signupButton.setOnClickListener {
            Intent(this, RegisterActivity::class.java).also {
                startActivity(it)
            }
        }

    }

    private fun isCredentialsCorrect(emailInput: String, passwordInput: String): Boolean {
        val sharedPreferences = getSharedPreferences(RegisterActivity.Constants.SHARED_PREFS_NAME, Context.MODE_PRIVATE)
        val emailSaved = sharedPreferences.getString(RegisterActivity.Constants.PREFS_EMAIL_KEY, "")
        val passwordSaved =
            sharedPreferences.getString(RegisterActivity.Constants.PREFS_PASSWORD_KEY, "")
        if ((emailSaved == emailInput) && (passwordSaved == passwordInput)) {
            Log.d(
                "credentials",
                "email: $emailSaved -- emailInput: $emailInput" +
                        "\npassword: $passwordSaved -- passwordInput: $passwordInput"
            )
            return true
        }
        return false
    }
}