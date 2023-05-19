package com.example.androidpracticehub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

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

        loginButton = findViewById(R.id.btnLogin)
        signupButton = findViewById(R.id.btnSignUp)


        loginButton.setOnClickListener {
            val userEmail = emailTextField.text.toString().trim()
            if (isValidEmail(userEmail)) Toast.makeText(
                this,
                R.string.login_successfully,
                Toast.LENGTH_LONG
            ).show()
            else Toast.makeText(this, R.string.email_password_incorrect, Toast.LENGTH_LONG).show()

            val intent = Intent(this, WelcomeActivity::class.java)
            intent.putExtra(WelcomeActivity.USERNAME_KEY, userEmail)
            startActivity(intent)
        }

        signupButton.setOnClickListener {
            Intent(this, RegisterActivity::class.java).also {
                startActivity(it)
            }
        }

    }
}