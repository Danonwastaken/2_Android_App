package com.example.drivenextapp

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

import com.example.drivenextapp.databinding.ActivitySignup2Binding
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class RegisterActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivitySignup2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignup2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val userLastName = binding.etLastName1
        val userName = binding.etName1
        val userSurname = binding.etSurname1
        val userBdate = binding.etDDMMYYYY
        val lastNameHint = binding.tvHintLastName
        val nameHint = binding.tvHintName
        val surnameHint = binding.tvHintSurname
        val button = binding.mbNext2
        val buttonPrev = binding.ibPrev
        val calendar = binding.ibCalendar
        val radioGroup = binding.rgGender


        surnameHint.text = getString(R.string.ommited)

        fun String.isAlpha(): Boolean {
            val regex = Regex("^[a-zA-Zа-яА-Я]+$")
            return this.matches(regex)
        }
        calendar.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText(getString(R.string.data_pick))
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build()
            datePicker.show(supportFragmentManager, "datePicker")
            datePicker.addOnPositiveButtonClickListener {
                val simpleDateFormat = SimpleDateFormat("dd/mm/yyyy", Locale.getDefault())
                userBdate.text = simpleDateFormat.format(Date(it).time)
            }
        }

        buttonPrev.setOnClickListener {
            //startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }


        button.setOnClickListener {
            val selectedOption : Int = radioGroup.checkedRadioButtonId
            val radioButton = findViewById<RadioButton>(selectedOption)
            val gender = when (selectedOption) {
                binding.rbMale.id -> "Мужской"   // Предполагается, что у вас есть radiobutton с id rbMale
                binding.rbFemale.id -> "Женский" // Предполагается, что у вас есть radiobutton с id rbFemale
                else -> ""
            }
            val lastName = userLastName.text.toString().trim()
            val namee = userName.text.toString().trim()
            val surname = userSurname.text.toString().trim()
            val bDate = userBdate.text.toString()
            val mail = intent.getStringExtra("email")
            val pass = intent.getStringExtra("password")


            val intent = Intent(this, RegisterActivity3::class.java).apply {
                putExtra("lastname", lastName)
                putExtra("name", namee)
                putExtra("surname", surname)
                putExtra("email", mail)
                putExtra("password", pass)
                putExtra("gender", gender)

            }
            when {
                (lastName.isAlpha() && namee.isAlpha() && !TextUtils.isEmpty(lastName) && !TextUtils.isEmpty(namee) && !TextUtils.isEmpty(bDate))
                        || (lastName.isAlpha() && namee.isAlpha() && surname.isAlpha() && !TextUtils.isEmpty(lastName) && !TextUtils.isEmpty(namee)
                        && !TextUtils.isEmpty(surname) && !TextUtils.isEmpty(bDate))-> {
                    startActivity(intent)
                }

                !lastName.isAlpha() -> {
                    lastNameHint.visibility = VISIBLE
                    lastNameHint.text = getString(R.string.lastname_alpha)
                    CoroutineScope(Dispatchers.Main).launch {
                        delay(5000)
                        lastNameHint.visibility = INVISIBLE
                    }
                }
                !namee.isAlpha() -> {
                    nameHint.visibility = VISIBLE
                    nameHint.text = getString(R.string.name_alpha)
                    CoroutineScope(Dispatchers.Main).launch {
                        delay(5000)
                        nameHint.visibility = INVISIBLE
                    }
                }
                !TextUtils.isEmpty(surname) && !surname.isAlpha() -> {
                    surnameHint.visibility = VISIBLE
                    surnameHint.text = getString(R.string.surname_alpha)
                    CoroutineScope(Dispatchers.Main).launch {
                        delay(5000)
                        surnameHint.visibility = INVISIBLE
                    }
                }
                TextUtils.isEmpty(lastName) || TextUtils.isEmpty(namee) || TextUtils.isEmpty(surname) -> {
                    Toast.makeText(this, getString(R.string.fill_fields), Toast.LENGTH_LONG).show()
                }
            }
        }
    }

}