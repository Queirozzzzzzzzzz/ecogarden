package com.queirozzzzzzzzzz.estufasemestufa.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.lifecycleScope
import com.queirozzzzzzzzzz.estufasemestufa.databinding.ActivityHomeBinding
import com.queirozzzzzzzzzz.estufasemestufa.models.tables.Environment
import com.queirozzzzzzzzzz.estufasemestufa.repository.EnvironmentRepository
import com.queirozzzzzzzzzz.estufasemestufa.ui.environments.EnvironmentsActivity
import com.queirozzzzzzzzzz.estufasemestufa.ui.newEnvironment.fragments.NewEnvironmentActivity
import kotlinx.coroutines.launch

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root

        animateLogo()
        setThemeManager()
        setButtons()
        testDatabase()

        setContentView(view)
    }

    private fun testDatabase() {
        viewModel = HomeViewModel(EnvironmentRepository(application))
        lifecycleScope.launch {
            val testEnv = Environment(0, "test", true, "", emptyList())
            viewModel.insertEnvironment(testEnv)
            val envs = viewModel.getEnvironments()
            println(envs[0])
    }
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
                if (isDarkTheme()) AppCompatDelegate.MODE_NIGHT_NO else AppCompatDelegate.MODE_NIGHT_YES,
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
