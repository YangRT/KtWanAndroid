package com.example.wanandroid.data.network.api

import retrofit2.Call
import com.example.wanandroid.data.model.ArticleInfo
import com.example.wanandroid.data.model.ShareInfo
import retrofit2.http.GET
import retrofit2.http.Path


/**
 * @program: WanAndroid
 *
 * @description: 获取 我的文章
 *
 * @author: YangRT
 *
 * @create: 2020-02-21 17:07
 **/

interface GetMyArticleService {

    @GET("lg/collect/list/{path}/json")
    fun getCollectArticle(@Path("path")page:Int):Call<ArticleInfo>

    @GET("user/lg/private_articles/{path}/json")
    fun getShareArticle(@Path("path")page:Int):Call<ShareInfo>

}