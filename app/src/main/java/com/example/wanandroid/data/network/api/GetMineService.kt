package com.example.wanandroid.data.network.api

import com.example.wanandroid.data.model.ArticleInfo
import com.example.wanandroid.data.model.MineInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


/**
 * @program: WanAndroid
 *
 * @description: 获取 我的信息
 *
 * @author: YangRT
 *
 * @create: 2020-02-19 16:10
 **/

interface GetMineService {
    @GET("/lg/coin/userinfo/json")
    fun getMineInfo(): Call<MineInfo>
}