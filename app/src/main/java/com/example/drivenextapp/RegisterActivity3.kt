package com.example.drivenextapp

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.view.View.INVISIBLE
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.LinkAnnotation
import com.example.drivenextapp.databinding.ActivitySignup3Binding
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class RegisterActivity3 : AppCompatActivity() {
    private lateinit var binding : ActivitySignup3Binding


    private var avatarUri: Uri? = null
    private var passportUri: Uri? = null
    private var licenseUri: Uri? = null

    private lateinit var photoChooseLicense: TextView
    private lateinit var photoChoosePassport: TextView
    private val pickAvatarLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val imageUri: Uri? = result.data?.data
            if (imageUri != null) {
                avatarUri = imageUri
                val inputStream = contentResolver.openInputStream(imageUri)
                val originalBitmap = BitmapFactory.decodeStream(inputStream)
                inputStream?.close()

                // Ресайзим Bitmap
                val compressedBitmap = Bitmap.createScaledBitmap(originalBitmap, 256, 256, true)

                binding.ivAvatar.setImageBitmap(compressedBitmap)
            }
        }
    }

    private val pickPassportLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val imageUri: Uri? = result.data?.data
            if (imageUri != null) {
                passportUri = imageUri
                photoChoosePassport = binding.tvUploadPhotoNear
                photoChoosePassport.text = getString(R.string.photo_picked)
            }
        }
    }

    private val pickLicenseLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val imageUri: Uri? = result.data?.data
            if (imageUri != null) {
                licenseUri = imageUri
                photoChooseLicense = binding.tvUploadPhotoNear2
                photoChooseLicense.text = getString(R.string.photo_picked)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignup3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val userNumber = binding.etNumber1
        val userDateGet = binding.tvDDMMYYYY
        val calendar = binding.ibCalendar
        val button = binding.mbNext3
        val ibAvatar = binding.ibPickAvatar
        val ibPassport = binding.ibUploadPassport
        val ibNumber = binding.ibUploadNumber
        val buttonPrev = binding.ibPrev
        val photoChoosePassport = binding.tvUploadPhotoNear

        ibAvatar.setOnClickListener {
            openGallery(pickAvatarLauncher)
            ibAvatar.visibility = INVISIBLE
        }

        ibPassport.setOnClickListener {
            openGallery(pickPassportLauncher)
        }

        ibNumber.setOnClickListener {
            openGallery(pickLicenseLauncher)
        }


        calendar.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText(getString(R.string.data_pick))
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build()
            datePicker.show(supportFragmentManager, "datePicker")
            datePicker.addOnPositiveButtonClickListener {
                val simpleDateFormat = SimpleDateFormat("dd/mm/yyyy", Locale.getDefault())
                userDateGet.text = simpleDateFormat.format(Date(it).time)
            }
        }

        buttonPrev.setOnClickListener {
            //startActivity(Intent(this, RegisterActivity2::class.java))
            finish()
        }

        button.setOnClickListener {
            val intent = Intent(this, CongratulationActivity::class.java)
            val numberr = userNumber.text.toString().trim()
            val dateGet = userDateGet.text.toString()

            when {

                !TextUtils.isEmpty(numberr) && !TextUtils.isEmpty(dateGet) && licenseUri !=null && passportUri != null -> {
                    startActivity(intent)
                }

                TextUtils.isEmpty(numberr) && TextUtils.isEmpty(dateGet) -> {
                    Toast.makeText(this, getString(R.string.fill_fields), Toast.LENGTH_LONG).show()
                }
                licenseUri == null -> {
                    photoChooseLicense.text = getString(R.string.photo_unpicked)
                }
                passportUri == null -> {
                    photoChoosePassport.text = getString(R.string.photo_unpicked)
                }
            }


        }
    }
    private fun openGallery(launcher: androidx.activity.result.ActivityResultLauncher<Intent>) {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        launcher.launch(intent)
    }

}
