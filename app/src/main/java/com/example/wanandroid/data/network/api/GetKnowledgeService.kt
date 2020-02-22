package com.example.wanandroid.data.network.api

import com.example.wanandroid.data.model.ArticleInfo
import com.example.wanandroid.data.model.KnowledgeInfo
import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Path
import retrofit2.http.Query


interface GetKnowledgeService {

    @GET("/tree/json")
    fun getKnowledgeInfo():Call<KnowledgeInfo>

    @GET("/article/list/{path}/json")
    fun getKnowledgeArticle(@Path("path")path:Int,@Query("cid")cid:Int):Call<ArticleInfo>
}