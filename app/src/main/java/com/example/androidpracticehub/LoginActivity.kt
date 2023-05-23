/**
 * This project aims to practice the following concepts:
 * View elements (Buttons, TextViews, etc..), Animation,
 * SharedPreferences, Intents, Activities, Toasts, and Kotlin fundamentals (visibility modifiers, navigation, and more).
 * The following links are resources I learned from to accomplish this project:
 * 1. sharedPreferences ->      https://www.codebrainer.com/blog/sharedpreferences
 * 2. Splash screen ->          https://developer.android.com/develop/ui/views/launch/splash-screen
 * 3. Splash animation ->       https://www.geeksforgeeks.org/how-to-create-an-animated-splash-screen-in-android/
 * 4. Kotlin course series ->   https://www.kodeco.com/android/paths/learn
 * 5. android:LaunchMode ->     https://medium.com/mobile-app-development-publication/android-activity-launchmode-made-simple-df7f0ec5e037
 * */

package com.example.androidpracticehub

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var emailTextField: EditText
    private lateinit var passwordTextField: EditText
    private lateinit var loginButton: Button
    private lateinit var signupButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val cachingLayer: CachingLayer = CachingLayerImpl(context = this)

        emailTextField = findViewById(R.id.emailTextField)
        passwordTextField = findViewById(R.id.passwordTextField)
        loginButton = findViewById(R.id.btnSignIn)
        signupButton = findViewById(R.id.btnSignUp)

        val isLoggedIn = cachingLayer.getLoggedIn()

        // validate if user is logged in --> redirect to welcomeActivity
        Log.d("isLoggedIn", isLoggedIn.toString())
        if (isLoggedIn) {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }


        loginButton.setOnClickListener {
            val userEmail = emailTextField.text.toString().trim()
            val userPassword = passwordTextField.text.toString().trim()

            // check if: 1.email is valid 2. registered or not
            if (isCredentialsCorrect(emailInput = userEmail, passwordInput = userPassword)) {
                // change Login boolean value --> true
                cachingLayer.setLoggedIn(value = true)

                Toast.makeText(this, R.string.login_successfully, Toast.LENGTH_LONG).show()

                val intent = Intent(this, HomeActivity::class.java)
                intent.putExtra(HomeActivity.USERNAME_KEY, userEmail)
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
        val cachingLayer:CachingLayer = CachingLayerImpl(context = this)
        val emailSaved = cachingLayer.getEmail()
        val passwordSaved = cachingLayer.getPassword()
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