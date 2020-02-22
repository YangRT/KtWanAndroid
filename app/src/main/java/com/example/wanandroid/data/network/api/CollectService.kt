package com.example.wanandroid.data.network.api

import retrofit2.Call
import com.example.wanandroid.data.model.CollectArticleInfo
import com.example.wanandroid.data.model.ResponseInfo
import retrofit2.http.*

interface CollectService {



    @POST("/lg/collect/{id}/json")
    fun addCollectArticle(@Path("id")id:Int):Call<ResponseInfo>

    @POST("lg/uncollect_originId/{id}/json")
    fun getUnCollectResponse(@Path("id")id:Int):Call<ResponseInfo>

    @FormUrlEncoded
    @POST("lg/uncollect/{id}/json")
    fun getUnCollectInMineResponse(@Path("id")id:Int,@Field("originId")originId:Int?):Call<ResponseInfo>
}