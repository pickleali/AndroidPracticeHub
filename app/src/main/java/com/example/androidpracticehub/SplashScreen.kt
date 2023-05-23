package com.example.androidpracticehub

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        // Animation background Image
        val backgroundImage: ImageView = findViewById(R.id.SplashScreenImage)

        // Animation effect
        val slideAnimation = AnimationUtils.loadAnimation(this, R.anim.splash_screen)
        backgroundImage.startAnimation(slideAnimation)

        // Handler with postDelayed (2sec) to navigate to Login screen after delay time
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }, 2000) // 2000 is the delayed time in milliseconds.
    }
}