package com.queirozzzzzzzzzz.estufasemestufa.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.queirozzzzzzzzzz.estufasemestufa.databinding.ActivityHomeBinding
import com.queirozzzzzzzzzz.estufasemestufa.repository.EnvironmentRepository
import com.queirozzzzzzzzzz.estufasemestufa.ui.environments.EnvironmentsActivity
import com.queirozzzzzzzzzz.estufasemestufa.ui.newEnvironment.fragments.NewEnvironmentActivity
import com.queirozzzzzzzzzz.estufasemestufa.viewmodel.ThemeViewModel
import kotlinx.coroutines.launch

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var themeViewModel: ThemeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root

        viewModel = HomeViewModel(EnvironmentRepository(application))
        themeViewModel = ThemeViewModel()

        animateLogo()
        setButtons()

        setContentView(view)
    }

    private fun switchTheme() {
        themeViewModel.switchTheme()
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

    private fun setButtons() {
        binding.btnCreateEnvironment.setOnClickListener {
            val intent = Intent(this, NewEnvironmentActivity::class.java)
            startActivity(intent)
        }

        lifecycleScope.launch {
            val hasEnvironments = viewModel.getEnvironments()

            binding.btnAccessEnvironments.isEnabled = hasEnvironments.isNotEmpty()
        }

        binding.btnAccessEnvironments.setOnClickListener {
            val intent = Intent(this, EnvironmentsActivity::class.java)
            startActivity(intent)
        }

        binding.theme.setOnClickListener {
            switchTheme()
        }
    }
}
