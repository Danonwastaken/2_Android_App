package com.example.drivenextapp

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.drivenextapp.databinding.ActivitySplashScreenBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket

@SuppressLint("CustomSplashScreen")
object NetworkUtil {
    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
        return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }

    suspend fun isInternetAvailable(): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val socket = Socket()
                socket.connect(InetSocketAddress("8.8.8.8", 53), 1500)
                socket.close()
                true
            } catch (e: IOException) {
                false
            }
        }
    }
}

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        button = binding.mbTryagainM
        button.setOnClickListener {
            button.isEnabled = false
            checkInternet()
        }

        //
        checkInternet()
    }

    private fun checkInternet() {
        val checkInter = binding.checkInternet

        if (NetworkUtil.isNetworkAvailable(this@SplashActivity)) {
            CoroutineScope(Dispatchers.Main).launch {
                if (NetworkUtil.isInternetAvailable()) {

                    checkInter.visibility = INVISIBLE
                    Handler(Looper.getMainLooper()).postDelayed({
                        startMainActivity()
                    }, 3000)
                } else {

                    checkInter.visibility = VISIBLE
                    button.isEnabled = true
                }
            }
        } else {

            checkInter.visibility = VISIBLE
            button.isEnabled = true
        }
    }

    private fun startMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}