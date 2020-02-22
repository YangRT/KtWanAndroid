package com.example.wanandroid.data.network.api

import com.example.wanandroid.data.model.ResponseInfo
import com.example.wanandroid.data.model.ShareInfo
import retrofit2.Call
import retrofit2.http.*


/**
 * @program: WanAndroid
 *
 * @description: 获取分享数据
 *
 * @author: YangRT
 *
 * @create: 2020-02-17 11:24
 **/

interface ShareService {

    @FormUrlEncoded
    @POST("lg/user_article/add/json")
    fun getShareArticleResponse(@Field("title")title:String, @Field("link")link:String):Call<ResponseInfo>
}