package com.example.wanandroid.ui.register

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wanandroid.MyApplication
import com.example.wanandroid.repository.LoginRegisterRepository
import com.example.wanandroid.data.model.ResponseInfo
import kotlinx.coroutines.launch

class RegisterViewModel(private val repository: LoginRegisterRepository):ViewModel() {
    var registerInfo = MutableLiveData<ResponseInfo>()

    fun register(username:String, password:String,repassword:String){
        launch({
            registerInfo.postValue(repository.register(username,password,repassword))
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