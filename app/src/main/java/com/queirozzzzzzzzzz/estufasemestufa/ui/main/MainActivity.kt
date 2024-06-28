package com.queirozzzzzzzzzz.estufasemestufa.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.queirozzzzzzzzzz.estufasemestufa.databinding.ActivityMainBinding
import com.queirozzzzzzzzzz.estufasemestufa.ui.home.HomeActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root

        animateLogo()
        loadApp()

        setContentView(view)
    }

    private fun animateLogo() {
        binding.logo.animate().apply {
            duration = 1000
            rotationYBy(360f)
            withEndAction {
                binding.logo.postDelayed({
                    animateLogo()
                }, 500)
            }
        }
    }

    private fun loadApp() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }
}
