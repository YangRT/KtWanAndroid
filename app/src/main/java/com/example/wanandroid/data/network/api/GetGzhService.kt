package com.example.wanandroid.data.network.api

import com.example.wanandroid.data.model.ArticleInfo
import com.example.wanandroid.data.model.GzhListInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GetGzhService {

    @GET("/wxarticle/chapters/json")
    fun getGzhListInfo(): Call<GzhListInfo>

    @GET("/wxarticle/list/{id}/{page}/json")
    fun getGzhArticlesById(@Path("id")id:Int,@Path("page")page:Int):Call<ArticleInfo>

    @GET("/wxarticle/list/{id}/{page}/json?k={key}")
    fun getArticlesByKey(@Path("id")id:Int,@Path("page")page:Int,@Path("key")key:String):Call<ArticleInfo>
}