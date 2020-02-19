package com.example.wanandroid.data.network.api
import com.example.wanandroid.data.model.RankInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GetRankService {
    @GET("/coin/rank/{path}/json")
    fun getRankInfo(@Path("path")path:Int):Call<RankInfo>
}