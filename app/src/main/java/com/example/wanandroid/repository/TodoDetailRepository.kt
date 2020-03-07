package com.example.wanandroid.repository

import com.example.wanandroid.data.network.WanNetwork


/**
 * @program: WanAndroid
 *
 * @description: todo详情 repository
 *
 * @author: YangRT
 *
 * @create: 2020-03-07 16:54
 **/

class TodoDetailRepository {

    suspend fun addEvent(title:String,content:String,type:Int,date:String):Boolean{
        val responseInfo = WanNetwork.getInstance().getAddTodoResponse(title, content, type, date)
        return responseInfo.errorCode==0
    }

    suspend fun updateEvent(id:Int,title:String,content:String,type:Int,date:String):Boolean{
        val responseInfo = WanNetwork.getInstance().getUpdateTodoResponse(id, title, content, type, date)
        return responseInfo.errorCode==0
    }
}