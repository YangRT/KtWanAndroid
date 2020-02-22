package com.example.wanandroid.data.network.api

import com.example.wanandroid.data.model.ArticleInfo
import com.example.wanandroid.data.model.HotWordInfo
import retrofit2.Call
import retrofit2.http.*

interface SearchService {

    @GET("/hotkey/json")
    fun getHotWord():Call<HotWordInfo>

    @FormUrlEncoded
    @POST("/article/query/{page}/json")
    fun search(@Path("page")Page:Int,@Field("k")k:String):Call<ArticleInfo>
}