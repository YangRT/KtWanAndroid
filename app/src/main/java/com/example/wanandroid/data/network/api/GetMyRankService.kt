package com.example.wanandroid.data.network.api

import com.example.wanandroid.data.model.MyRankDetailInfo
import com.example.wanandroid.data.model.MyRankInfo
import retrofit2.Call
import retrofit2.http.GET

interface GetMyRankService {
    @GET("/lg/coin/userinfo/json")
    fun getMyRankInfo():Call<MyRankInfo>


    @GET("/lg/coin/list/1/json")
    fun getMyRankDetailInfo():Call<MyRankDetailInfo>
}