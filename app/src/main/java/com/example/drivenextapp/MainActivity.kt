package com.example.drivenextapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.drivenextapp.databinding.ActivityGettingstartedBinding
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGettingstartedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGettingstartedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewPager2 = binding.viewPager2
        viewPager2.adapter = ViewPagerAdapter(viewPager2)



        val linkLogin = binding.mbSigninGS
        val linkRegister = binding.mbRegGS
        linkLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        linkRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}