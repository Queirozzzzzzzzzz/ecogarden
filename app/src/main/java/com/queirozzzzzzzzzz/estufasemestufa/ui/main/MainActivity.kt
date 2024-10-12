package com.queirozzzzzzzzzz.estufasemestufa.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.queirozzzzzzzzzz.estufasemestufa.data.Preferences
import com.queirozzzzzzzzzz.estufasemestufa.databinding.ActivityMainBinding
import com.queirozzzzzzzzzz.estufasemestufa.ui.auth.LoginActivity
import com.queirozzzzzzzzzz.estufasemestufa.ui.home.HomeActivity
import com.queirozzzzzzzzzz.estufasemestufa.utils.NetworkUtils
import com.queirozzzzzzzzzz.estufasemestufa.viewmodel.AccountViewModel
import com.queirozzzzzzzzzz.estufasemestufa.viewmodel.ThemeViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val themeViewModel: ThemeViewModel by lazy { ThemeViewModel() }
    private val accountViewModel: AccountViewModel by lazy { ViewModelProvider(this)[AccountViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Preferences.setup(applicationContext)
        themeViewModel.setTheme(Preferences.getDarkTheme())
        animateLogo()
        loadApp()
    }

    private fun animateLogo() {
        binding.logo.animate().apply {
            duration = 1000
            rotationYBy(360f)
            withEndAction { binding.logo.postDelayed({ animateLogo() }, 5000) }
        }
    }

    private fun loadApp() {
        lifecycleScope.launch {
            val intent = if (Preferences.getAuthCookie().isNullOrEmpty() ||isLoginExpired()) {
                Preferences.setAuthCookie("")
                Intent(this@MainActivity, LoginActivity::class.java)
            } else {
                Intent(this@MainActivity, HomeActivity::class.java)
            }
            startActivity(intent)
            finish()
        }
    }

    private suspend fun isLoginExpired(): Boolean = withContext(Dispatchers.IO) {
        if(NetworkUtils.hasInternet(this@MainActivity)) {
            !accountViewModel.checkLogin()
        } else {
            false
        }

    }
}