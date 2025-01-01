package com.example.freefom

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class OpeningFreeFrom : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_opening_free_from)

        Handler(Looper.getMainLooper()).postDelayed({
            // Navigate to MainActivity
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
            finish() // Close SplashActivity
        }, 3000)


    }
}