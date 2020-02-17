package com.example.wanandroid.data.network.api

import com.example.wanandroid.data.model.ShareInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


/**
 * @program: WanAndroid
 *
 * @description: 获取分享数据
 *
 * @author: YangRT
 *
 * @create: 2020-02-17 11:24
 **/

interface GetShareService {
    //页码从 1 开始
    @GET("user/lg/private_articles/{path}/json")
    fun getShareInfo(@Path("path")path:Int):Call<ShareInfo>
}