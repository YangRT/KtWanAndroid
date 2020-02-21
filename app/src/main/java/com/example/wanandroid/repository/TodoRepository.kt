package com.example.wanandroid.repository

import com.example.wanandroid.base.BaseMvvmRepository
import com.example.wanandroid.base.BaseResult
import com.example.wanandroid.data.model.TodoEvent
import com.example.wanandroid.data.model.TodoEventInfo
import com.example.wanandroid.data.network.WanNetwork
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


/**
 * @program: WanAndroid
 *
 * @description: todo数据 repository
 *
 * @author: YangRT
 *
 * @create: 2020-02-21 18:09
 **/

class TodoRepository(val status:Int,val type:String):BaseMvvmRepository<List<TodoEvent>>(true,type,null) {

    private var lastType = 0
    init {
        pageNum = 1
    }

    override suspend fun load(): BaseResult<List<TodoEvent>> {
        val info = WanNetwork.getInstance().getTodoList(pageNum,status,null)
        val result:BaseResult<List<TodoEvent>> = BaseResult()
        if (info.errorCode == 0){
            pageNum = if (isRefreshing){ 2 }else{ pageNum+1 }
            result.isFirst = pageNum==2
            result.isEmpty = info.data.datas.isEmpty()
            result.data = info.data.datas
        }else{
            result.isEmpty = true
            result.isFirst = pageNum==1
        }
        if(result.isFirst){
            result.data?.let {
                saveDataToPreference(it)
            }
        }
        result.isFromCache = false
        result.isPaging = true
        return result
    }

    override suspend fun refresh(): BaseResult<List<TodoEvent>> {
        isRefreshing = true
        pageNum = 1
        return loadType(lastType)
    }

    suspend fun loadNextPage():BaseResult<List<TodoEvent>>{
        isRefreshing = false
        return loadType(lastType)
    }

    private suspend fun loadType(type:Int):BaseResult<List<TodoEvent>>{
        val info:TodoEventInfo = if (type == 0){
            WanNetwork.getInstance().getTodoList(pageNum,status,null)
        }else{
            WanNetwork.getInstance().getTodoList(pageNum,status,type)
        }
        val result:BaseResult<List<TodoEvent>> = BaseResult()
        if (info.errorCode == 0){
            pageNum = if (isRefreshing){ 2 }else{ pageNum+1 }
            result.isFirst = pageNum==2
            result.isEmpty = info.data.datas.isEmpty()
            result.data = info.data.datas
        }else{
            result.isEmpty = true
            result.isFirst = pageNum==1
        }
        result.isFromCache = false
        result.isPaging = true
        return result
    }

    suspend fun changeType(type: Int):BaseResult<List<TodoEvent>>{
        var result:BaseResult<List<TodoEvent>> = BaseResult()
        if (lastType != type){
            lastType = type
            pageNum = 1
            result = loadType(type)
        }else{
            result.isFirst = true
            result.isEmpty = true
            result.isFromCache = true
            result.msg = "所选类型与当前类型相同！"
        }
        result.isPaging = true
        return result
    }

    override fun getTClass(): Type? {
        return object : TypeToken<List<TodoEvent>>(){}.type
    }
}