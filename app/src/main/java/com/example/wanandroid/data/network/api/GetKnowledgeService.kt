package com.example.wanandroid.data.network.api

import com.example.wanandroid.data.model.ArticleInfo
import retrofit2.http.GET
import com.example.wanandroid.data.model.KnowledgeInfo
import retrofit2.Call
import retrofit2.http.Path


interface GetKnowledgeService {
    @GET("/tree/json")
    fun getKnowledgeInfo():Call<KnowledgeInfo>

    @GET("/article/list/0/json?cid={cid}")
    fun getKnowledgeArticle(@Path("cid")cid:Int):Call<ArticleInfo>
}