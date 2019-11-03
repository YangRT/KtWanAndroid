package com.example.wanandroid.data.network.api

import com.example.wanandroid.data.model.ArticleInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GetArticlesService {
    @GET("/article/list/{page}/json")
    fun getArticles(@Path("page")page:Int):Call<ArticleInfo>
}