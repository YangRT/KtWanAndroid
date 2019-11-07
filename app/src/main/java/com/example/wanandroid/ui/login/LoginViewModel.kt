package com.example.wanandroid.ui.login

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wanandroid.MyApplication
import com.example.wanandroid.data.LoginRegisterRepository
import com.example.wanandroid.data.model.ResponseInfo
import kotlinx.coroutines.launch

class LoginViewModel(private val repository:LoginRegisterRepository) :ViewModel(){

    var loginInfo = MutableLiveData<ResponseInfo>()

    fun login(username:String, password:String){
        launch({
            loginInfo.postValue(repository.login(username,password))
        },
            { Toast.makeText(MyApplication.context,it.message, Toast.LENGTH_LONG).show()})
    }


    private fun launch(block: suspend () -> Unit, error: suspend (Throwable) -> Unit) = viewModelScope.launch {
        try {
            block()
        } catch (e: Throwable) {
            error(e)
        }
    }
}