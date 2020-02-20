package com.example.wanandroid.data.network.api

import com.example.wanandroid.data.model.ArticleInfo
import com.example.wanandroid.data.model.ProjectClassicInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


/**
 * @program: WanAndroid
 *
 * @description: 获取项目数据
 *
 * @author: YangRT
 *
 * @create: 2020-02-17 11:20
 **/

interface GetProjectService {

    @GET("article/listproject/{path}/json")
    fun getProjectInfo(@Path("path")page:Int): Call<ArticleInfo>

    @GET("/project/tree/json")
    fun getProjectClassic(): Call<ProjectClassicInfo>

}