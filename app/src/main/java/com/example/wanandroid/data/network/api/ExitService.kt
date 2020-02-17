package com.example.wanandroid.data.network.api

import com.example.wanandroid.data.model.ResponseInfo
import retrofit2.Call
import retrofit2.http.GET


/**
 * @program: WanAndroid
 *
 * @description: 退出登录
 *
 * @author: YangRT
 *
 * @create: 2020-02-17 11:11
 **/

interface ExitService {

    @GET("user/logout/json")
    fun getExitInfo():Call<ResponseInfo>
}