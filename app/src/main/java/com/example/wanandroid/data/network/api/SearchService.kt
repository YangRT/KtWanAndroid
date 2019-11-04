package com.example.wanandroid.data.network.api

import com.example.wanandroid.data.model.ArticleInfo
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Path

interface SearchService {
    @FormUrlEncoded
    @POST("/article/query/{page}/json")
    fun search(@Path("page")Page:Int,@Field("k")k:String):Call<ArticleInfo>
}