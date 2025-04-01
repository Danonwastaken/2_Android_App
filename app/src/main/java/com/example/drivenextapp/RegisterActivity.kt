package com.example.drivenextapp

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.checkbox.MaterialCheckBox
import androidx.viewpager2.widget.ViewPager2
import com.example.drivenextapp.databinding.ActivitySignup1Binding
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RegisterActivity: AppCompatActivity() {

    private lateinit var binding: ActivitySignup1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySignup1Binding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val userMail = binding.etMail11
        val userPass = binding.etPassword11
        val userPassR = binding.etPasswordR11
        val userAgree = binding.mcAgree
        val button = binding.mbNext
        val buttonPrev = binding.ibPrev
        val mailHint = binding.tvHintMail1
        val passHint = binding.tvHintPassword1
        val passRHint = binding.tvHintRepPassword1




        fun String.isEmailValid(): Boolean {
            return !TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
        }

        buttonPrev.setOnClickListener {
            //startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        button.setOnClickListener {
            val mail = userMail.text.toString().trim()
            val pass = userPass.text.toString().trim()
            val passR = userPassR.text.toString().trim()
            val agree = userAgree.isChecked
            val intent = Intent(this, RegisterActivity2::class.java).apply {
                putExtra("email", mail)
                putExtra("password", pass)
            }

            when {
                (mail.isEmailValid()) && (pass == passR) && agree -> {
                    startActivity(intent)
                }
                TextUtils.isEmpty(mail) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(
                        passR) -> {
                    Toast.makeText(this, getString(R.string.fill_fields), Toast.LENGTH_LONG).show()
                }
                pass != passR -> {
                    passHint.visibility = VISIBLE
                    passRHint.visibility = VISIBLE
                    passHint.text = getString(R.string.password_missmatch)
                    passRHint.text = getString(R.string.password_missmatch)
                    CoroutineScope(Dispatchers.Main).launch {
                        delay(5000)
                        passHint.visibility = INVISIBLE
                        passRHint.visibility = INVISIBLE
                    }
                }

                !mail.isEmailValid() -> {
                    mailHint.visibility = VISIBLE
                    mailHint.text = getString(R.string.wrong_email)
                    CoroutineScope(Dispatchers.Main).launch {
                        delay(5000)
                        mailHint.visibility = INVISIBLE
                    }
                }
                !agree -> {
                    Toast.makeText(
                        this, getString(R.string.terms_dissagree),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

    }
}