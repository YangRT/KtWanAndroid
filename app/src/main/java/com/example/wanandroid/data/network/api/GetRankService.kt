package com.example.wanandroid.data.network.api
import com.example.wanandroid.data.model.RankInfo
import retrofit2.Call
import retrofit2.http.GET

interface GetRankService {
    @GET("/coin/rank/1/json")
    fun getRankInfo():Call<RankInfo>
}