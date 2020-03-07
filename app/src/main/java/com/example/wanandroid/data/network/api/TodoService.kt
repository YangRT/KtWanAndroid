package com.example.wanandroid.data.network.api

import com.example.wanandroid.data.model.ResponseInfo
import com.example.wanandroid.data.model.TodoEventInfo
import retrofit2.Call
import retrofit2.http.*


/**
 * @program: WanAndroid
 *
 * @description: 获取todo数据
 *
 * @author: YangRT
 *
 * @create: 2020-02-21 17:44
 **/

interface TodoService {

    @GET("/lg/todo/v2/list/{path}/json")
    fun getTodoList(@Path("path")page:Int, @Query("status")status:Int, @Query("type")type:Int?):Call<TodoEventInfo>

    @POST("lg/todo/delete/{path}/json")
    fun getDeleteResponse(@Path("path")id:Int):Call<ResponseInfo>

    @FormUrlEncoded
    @POST("/lg/todo/done/{path}/json")
    fun getChangeStatusResponse(@Path("path")id:Int,@Field("status")status:Int):Call<ResponseInfo>

    @FormUrlEncoded
    @POST("lg/todo/add/json")
    fun getAddEventResponse(@Field("title")title:String,@Field("content")content:String,@Field("date")date:String,@Field("type")type:Int):Call<ResponseInfo>

    @FormUrlEncoded
    @POST("lg/todo/update/{path}/json")
    fun getUpdateEventResponse(@Path("path")id:Int,@Field("title")title:String,@Field("content")content:String,@Field("date")date:String,@Field("type")type:Int):Call<ResponseInfo>



}
