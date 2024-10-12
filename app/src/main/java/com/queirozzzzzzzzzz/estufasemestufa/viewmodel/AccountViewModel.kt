package com.queirozzzzzzzzzz.estufasemestufa.viewmodel

import androidx.lifecycle.ViewModel
import com.queirozzzzzzzzzz.estufasemestufa.models.Account

class AccountViewModel: ViewModel() {
    private val account =  Account()

    suspend fun login(cpf: String, password: String): Boolean {
        if (cpf.isEmpty() || password.isEmpty()) return false

        return account.login(cpf, password)
    }

    suspend fun signup(cpf: String, password: String): Boolean {
        if (cpf.isEmpty() || password.isEmpty()) return false

        return account.signup(cpf, password)
    }

    suspend fun checkLogin(): Boolean {
        return account.checkLogin()
    }

}