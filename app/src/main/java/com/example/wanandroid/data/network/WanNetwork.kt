package com.example.wanandroid.data.network

import com.example.wanandroid.data.network.api.LoginService
import com.example.wanandroid.data.network.api.RegisterService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class WanNetwork {

    private val loginService = ServiceCreator.createLogin(LoginService::class.java)
    private val registerService: RegisterService = ServiceCreator.create(RegisterService::class.java)


    suspend fun login(username:String,password:String) = loginService.getLoginInfo(username,password).await()
    suspend fun register(username:String,password:String,repassword:String) = registerService.getRegisterInfo(username,password,repassword).await()


    private suspend fun <T> Call<T>.await(): T {
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T> {
                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }

                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    if (body != null) continuation.resume(body)
                    else continuation.resumeWithException(RuntimeException("response body is null"))
                }
            })
        }
    }

    companion object {
        private var network: WanNetwork? = null
        fun getInstance(): WanNetwork {
            if (network == null) {
                synchronized(WanNetwork::class.java) {
                    if (network == null) {
                        network = WanNetwork()
                    }
                }
            }
            return network!!
        }

    }
}