package com.queirozzzzzzzzzz.estufasemestufa

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.queirozzzzzzzzzz.estufasemestufa.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root

        animateLogo()
        setThemeManager()
        setButtons()

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

    private fun isDarkTheme() =
        AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES

    private fun setThemeManager() {
        binding.theme.setOnClickListener {
            AppCompatDelegate.setDefaultNightMode(
                if (isDarkTheme()) AppCompatDelegate.MODE_NIGHT_NO else AppCompatDelegate.MODE_NIGHT_YES
            )
        }
    }

    private fun setButtons() {
        binding.btnCreateEnvironment.setOnClickListener {
            val intent = Intent(this, NewEnvironmentActivity::class.java)
            startActivity(intent)
        }

        binding.btnAccessEnvironments.setOnClickListener {
            val intent = Intent(this, EnvironmentsActivity::class.java)
            startActivity(intent)
        }
    }
}