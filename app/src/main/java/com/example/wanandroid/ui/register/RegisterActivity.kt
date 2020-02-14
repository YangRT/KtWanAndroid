package com.example.wanandroid.ui.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.wanandroid.R
import com.example.wanandroid.repository.LoginRegisterRepository
import com.example.wanandroid.data.network.WanNetwork
import com.example.wanandroid.ui.login.LoginViewModelFactory
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    lateinit var registerViewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        registerViewModel = ViewModelProviders.of(this, LoginViewModelFactory(
            LoginRegisterRepository.getInstance(
                WanNetwork.getInstance()))).get(RegisterViewModel::class.java)
        init()
    }

    private fun init(){
        //监听注册反馈信息
        registerViewModel.registerInfo.observe(this, Observer {
            val code = it.errorCode
            if(code == 0){
                Toast.makeText(this,"注册成功", Toast.LENGTH_SHORT).show()
                finish()
        }else{
            Log.d("loginActivity","fail")
            Toast.makeText(this,it.errorMsg, Toast.LENGTH_SHORT).show()
        }
        })
        //设置注册按钮
        bt_register.setOnClickListener {
            registerViewModel.register(edit_username.text.toString(),edit_password.text.toString(),edit_re_password.text.toString())
        }

    }
}
