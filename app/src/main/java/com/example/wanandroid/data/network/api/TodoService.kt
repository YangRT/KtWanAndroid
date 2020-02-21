package com.example.wanandroid.data.network.api

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


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
    fun getTodoService(@Path("path")page:Int,@Query("status")status:Int,@Query("type")type:Int?)

}