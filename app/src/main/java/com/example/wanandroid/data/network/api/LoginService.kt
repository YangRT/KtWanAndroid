package com.example.wanandroid.data.network.api

import com.example.wanandroid.data.model.ResponseInfo
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginService {
    @FormUrlEncoded
    @POST("user/login")
    fun getLoginInfo(@Field("username")username:String, @Field("password")password:String): Call<ResponseInfo>
}