package com.example.wanandroid.data.network.api

import com.example.wanandroid.data.model.ArticleInfo
import com.example.wanandroid.data.model.NavigationInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


/**
 * @program: WanAndroid
 *
 * @description: 获取 navigation 数据
 *
 * @author: YangRT
 *
 * @create: 2020-02-19 22:30
 **/

interface GetNavigationService {
    @GET("navi/json")
    fun getNavigationInfo(): Call<NavigationInfo>
}