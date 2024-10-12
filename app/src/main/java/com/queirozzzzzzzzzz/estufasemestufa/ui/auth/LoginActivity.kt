package com.queirozzzzzzzzzz.estufasemestufa.ui.auth

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.queirozzzzzzzzzz.estufasemestufa.data.Preferences
import com.queirozzzzzzzzzz.estufasemestufa.databinding.ActivityLoginBinding
import com.queirozzzzzzzzzz.estufasemestufa.ui.home.HomeActivity
import com.queirozzzzzzzzzz.estufasemestufa.viewmodel.AccountViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.job
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var accountViewModel: AccountViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        accountViewModel = ViewModelProvider(this)[AccountViewModel::class.java]

        checkLogin()
        setInputs()
    }

    fun loadSignup(view: View) {
        startActivity(Intent(this, SignupActivity::class.java))
    }

    private fun checkLogin() {
        if (Preferences.getAuthCookie() != "null" && !Preferences.getAuthCookie().isNullOrEmpty()) {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
    }

    private fun setInputs() {
        binding.btnLogin.setOnClickListener {
            val email = binding.email.text.toString()
            val password = binding.password.text.toString()

            CoroutineScope(Dispatchers.IO).launch {
                val dialogJob = launch {
                    try {
                        showLoadingDialog()
                        coroutineContext.job.join()
                    } finally {
                        hideLoadingDialog()
                    }
                }

                val res = accountViewModel.login(email, password)

                dialogJob.cancel()
                runOnUiThread {
                    if (res) {
                        startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                        finish()
                    } else {
                        binding.error.text = Preferences.getLoginError()
                    }
                }
            }
        }
    }

    private fun showLoadingDialog() {
        runOnUiThread {
            binding.loadingBg.visibility = View.VISIBLE
            binding.loading.visibility = View.VISIBLE
        }
    }

    private fun hideLoadingDialog() {
        runOnUiThread {
            binding.loadingBg.visibility = View.GONE
            binding.loading.visibility = View.GONE
        }
    }
}