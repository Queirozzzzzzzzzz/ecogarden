package com.queirozzzzzzzzzz.estufasemestufa.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.queirozzzzzzzzzz.estufasemestufa.data.Preferences
import com.queirozzzzzzzzzz.estufasemestufa.databinding.ActivityMainBinding
import com.queirozzzzzzzzzz.estufasemestufa.ui.home.HomeActivity
import com.queirozzzzzzzzzz.estufasemestufa.viewmodel.ThemeViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var themeViewModel: ThemeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root

        themeViewModel = ThemeViewModel()

        Preferences.setup(applicationContext)
        themeViewModel.setTheme(Preferences.getDarkTheme())
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
                }, 5000)
            }
        }
    }

    private fun loadApp() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }
}
