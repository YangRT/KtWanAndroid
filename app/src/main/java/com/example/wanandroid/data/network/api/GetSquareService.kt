package com.example.wanandroid.data.network.api

import com.example.wanandroid.data.model.ArticleInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


/**
 * @program: WanAndroid
 *
 * @description: 获取广场数据
 *
 * @author: YangRT
 *
 * @create: 2020-02-17 11:16
 **/

interface GetSquareService {
    @GET("/user_article/list/{path}/json")
    fun getSquareInfo(@Path("path")page:Int): Call<ArticleInfo>
}