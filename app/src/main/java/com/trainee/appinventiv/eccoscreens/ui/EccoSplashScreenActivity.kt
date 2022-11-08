package com.trainee.appinventiv.eccoscreens.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.trainee.appinventiv.eccoscreens.databinding.ActivityEccoSplashScreenBinding

@SuppressLint("CustomSplashScreen")
class EccoSplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEccoSplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEccoSplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed(Runnable {
            startActivity(Intent(this, MainActivity::class.java))
        },3000)
    }
}