package com.queirozzzzzzzzzz.estufasemestufa.ui.auth

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.queirozzzzzzzzzz.estufasemestufa.data.Preferences
import com.queirozzzzzzzzzz.estufasemestufa.databinding.ActivitySignupBinding
import com.queirozzzzzzzzzz.estufasemestufa.ui.home.HomeActivity
import com.queirozzzzzzzzzz.estufasemestufa.viewmodel.AccountViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.job
import kotlinx.coroutines.launch

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private lateinit var viewModel: AccountViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[AccountViewModel::class.java]

        checkLogin()
        setInputs()
    }

    fun loadLogin(view: View) {
        startActivity(Intent(this, LoginActivity::class.java))
    }

    private fun checkLogin() {
        if (Preferences.getAuthCookie() != "null" && !Preferences.getAuthCookie().isNullOrEmpty()) {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
    }

    private fun setInputs() {
        binding.btnSignup.setOnClickListener {
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

                val res = viewModel.signup(email, password)

                dialogJob.cancel()
                runOnUiThread {
                    if (res) {
                        startActivity(Intent(this@SignupActivity, HomeActivity::class.java))
                        finish()
                    } else {
                        binding.error.text = Preferences.getSignupError()
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