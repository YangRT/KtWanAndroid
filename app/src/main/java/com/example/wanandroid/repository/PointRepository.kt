package com.example.wanandroid.repository

import com.example.wanandroid.base.BaseMvvmRepository
import com.example.wanandroid.base.BaseResult
import com.example.wanandroid.data.network.WanNetwork
import com.example.wanandroid.data.network.api.PointDetail
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


/**
 * @program: WanAndroid
 *
 * @description: 积分详情 repository
 *
 * @author: YangRT
 *
 * @create: 2020-02-21 11:45
 **/

class PointRepository:BaseMvvmRepository<List<PointDetail>>(true,"point",null) {

    init {
        pageNum = 1
    }

    override suspend fun load(): BaseResult<List<PointDetail>> {
        val info = WanNetwork.getInstance().getPointDetail(pageNum)
        val result = BaseResult<List<PointDetail>>()
        if(info.errorCode == 0){
            pageNum = if(isRefreshing){ 2 }else{pageNum + 1}
            result.data = info.data.datas
            result.isEmpty = info.data.datas.isEmpty()
            result.isFirst = pageNum == 2
        }else{
            result.isEmpty = true
            result.msg = info.errorMsg
            result.isFirst = pageNum == 1
        }
        result.isPaging = true
        result.isFromCache = true
        if(result.isFirst){
            result.data?.let {
                saveDataToPreference(it)
            }
        }
        return result
    }

    override suspend fun refresh(): BaseResult<List<PointDetail>> {
        isRefreshing = true
        pageNum = 1
        return load()
    }

    suspend fun loadNextPage():BaseResult<List<PointDetail>>{
        isRefreshing = false
        return load()
    }

    override fun getTClass(): Type? {
        return object : TypeToken<List<PointDetail>>(){}.type
    }
}