package com.example.wanandroid.ui.login

import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.wanandroid.SplashActivity
import com.example.wanandroid.R
import com.example.wanandroid.repository.LoginRegisterRepository
import com.example.wanandroid.data.network.WanNetwork
import com.example.wanandroid.ui.register.RegisterActivity
import com.example.wanandroid.util.saveUserInfo
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var loginViewModel:LoginViewModel
    private var username:String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()
        if (Build.VERSION.SDK_INT >= 21) {
            val decorView = window.decorView
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            window.statusBarColor = Color.TRANSPARENT
        }
        loginViewModel = ViewModelProviders.of(this,LoginViewModelFactory(
            LoginRegisterRepository.getInstance(
            WanNetwork.getInstance()))).get(LoginViewModel::class.java)
        init()
    }

    private fun init(){
        //监听登录返回信息
        loginViewModel.loginInfo.observe(this, Observer {
            bt_login.isClickable = true
            val errorCode = it.errorCode
            if(errorCode == 0){
                //保存登录信息
                username?.let { i ->
                    saveUserInfo(i)
                }
                val intent = Intent(this, SplashActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                finish()
            }else{
                Log.d("loginActivity","fail")
                Toast.makeText(this,it.errorMsg,Toast.LENGTH_SHORT).show()
            }
        })
        //设置登录按钮点击事件
        bt_login.setOnClickListener {
            loginViewModel.login(edit_username.text.toString(),edit_password.text.toString())
            it.isClickable = false
            username = edit_username.text.toString()
        }
        //设置注册文字点击事件
        tv_to_register.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}
