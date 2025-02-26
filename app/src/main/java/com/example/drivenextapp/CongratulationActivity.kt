package com.example.drivenextapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.drivenextapp.databinding.ActivityCongratulationBinding

class CongratulationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCongratulationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCongratulationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val button = binding.mbNextC
        val intent = Intent(this, MainActivity::class.java)
        button.setOnClickListener {
            startActivity(intent)
        }
    }
}