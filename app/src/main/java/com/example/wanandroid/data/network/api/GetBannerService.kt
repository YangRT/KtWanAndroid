package com.example.wanandroid.data.network.api

import com.example.wanandroid.data.model.BannerInfo
import retrofit2.Call
import retrofit2.http.GET

interface GetBannerService {
    @GET("/banner/json")
    fun getBannerInfo():Call<BannerInfo>
}