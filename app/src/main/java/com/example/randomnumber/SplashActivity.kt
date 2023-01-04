package com.example.randomnumber

import android.content.Intent
import android.graphics.drawable.Drawable
import android.graphics.drawable.Icon
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.randomnumber.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var ivIcon = binding.ivIcon
        ivIcon.setImageDrawable(ContextCompat.getDrawable(this, R.mipmap.ic_launcher))
        Initializer.init(application)
        Intent(this, MainActivity::class.java).also {
            startActivity(it)
        }


    }
}