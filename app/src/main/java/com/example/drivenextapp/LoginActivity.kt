package com.example.drivenextapp

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.drivenextapp.databinding.ActivityLoginBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val scope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = AppDataBase.getDb(this)
        val userMail = binding.etMailL
        val userPassword = binding.etPassword11
        val buttonLogin = binding.mbSignInL
        val buttonRegister = binding.mbRegL
        val intent = Intent(this, NavigationActivity::class.java)
        buttonRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        buttonLogin.setOnClickListener {
            val email = userMail.text.toString().trim()
            val password = userPassword.text.toString().trim()
            when {
                ((TextUtils.isEmpty(email) || TextUtils.isEmpty(password))) ->
                {
                    Toast.makeText(this, getString(R.string.fill_fields), Toast.LENGTH_LONG).show()
                }
                else -> {
                    scope.launch {
                        val userExists = withContext(Dispatchers.IO) {
                            db.getDao().checkUserExists(email, password)
                        }
                        if (userExists != null) {
                            intent.putExtra("gender", userExists.gender)
                            intent.putExtra("email", userExists.email)
                            intent.putExtra("name", userExists.name)
                            intent.putExtra("lastname", userExists.lastname)
                            intent.putExtra("avatar", userExists.avatarUri)

                            startActivity(intent)
                            finish()
                        }
                        else {
                            Toast.makeText(this@LoginActivity, "Пользователь не найден", Toast.LENGTH_LONG).show()
                        }
                    }
                }

            }

        }
    }
}