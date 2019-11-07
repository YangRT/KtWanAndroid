package com.example.wanandroid.ui.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.wanandroid.data.LoginRegisterRepository

class RegisterViewModelFactory (private val loginRegisterRepository: LoginRegisterRepository):
    ViewModelProvider.NewInstanceFactory(){
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RegisterViewModel(loginRegisterRepository) as T
    }
}