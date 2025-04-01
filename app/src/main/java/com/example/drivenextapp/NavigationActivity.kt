package com.example.drivenextapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController

import com.example.drivenextapp.databinding.ActivityNavigationBinding

class NavigationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNavigationBinding
    private lateinit var viewModel: UserDataViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[UserDataViewModel::class.java]
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView2) as NavHostFragment
        val controller = navHostFragment.navController

        val gender = intent.getStringExtra("gender")
        val email = intent.getStringExtra("email")
        val namee = intent.getStringExtra("name")
        val lastname = intent.getStringExtra("lastname")
        val avatarUri = intent.getStringExtra("avatarUri")


        viewModel.setUserData(gender, email, namee, lastname, avatarUri)



        val btnNavView = binding.bottomNavigationView2
        btnNavView.setupWithNavController(controller)

    }
}