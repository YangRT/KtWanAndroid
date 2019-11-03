package com.example.wanandroid.data

import com.example.wanandroid.data.network.WanNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginRegisterRepository private constructor(private val wanNetwork: WanNetwork) {

    suspend fun login(username:String,password:String)= withContext(Dispatchers.IO){
        val info = wanNetwork.login(username, password)
        info
    }

    suspend fun register(username:String,password:String,repassword:String) = withContext(Dispatchers.IO){
        val info = wanNetwork.register(username, password, repassword)
        info
    }

    companion object {
        private lateinit var instance: LoginRegisterRepository

        fun getInstance(wanNetwork: WanNetwork): LoginRegisterRepository {
            if(!Companion::instance.isInitialized){
                synchronized(LoginRegisterRepository::class.java){
                    instance =
                        LoginRegisterRepository(wanNetwork)
                }
            }
            return instance
        }
    }
}