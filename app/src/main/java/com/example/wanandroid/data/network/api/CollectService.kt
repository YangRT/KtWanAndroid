package com.example.wanandroid.data.network.api

import retrofit2.Call
import com.example.wanandroid.data.model.CollectArticleInfo
import com.example.wanandroid.data.model.ResponseInfo
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface CollectService {
    @GET("/lg/collect/list/0/json")
    fun getCollectInfo():Call<CollectArticleInfo>

    @POST("/lg/collect/{id}/json")
    fun addCollectArticle(@Path("id")id:Int):Call<ResponseInfo>
}