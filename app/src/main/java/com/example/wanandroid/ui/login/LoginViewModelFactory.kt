package com.example.wanandroid.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.wanandroid.repository.LoginRegisterRepository

class LoginViewModelFactory(private val loginRegisterRepository: LoginRegisterRepository):
    ViewModelProvider.NewInstanceFactory(){
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginViewModel(loginRegisterRepository) as T
    }
}