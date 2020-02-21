package com.example.wanandroid.repository

import com.example.wanandroid.base.BaseMvvmRepository
import com.example.wanandroid.base.BaseResult
import com.example.wanandroid.data.model.NavigationData
import com.example.wanandroid.data.network.WanNetwork
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


/**
 * @program: WanAndroid
 *
 * @description: 导航 repository
 *
 * @author: YangRT
 *
 * @create: 2020-02-19 22:33
 **/

class NavigationRepository:BaseMvvmRepository<List<NavigationData>>(false,"navigation",null) {

    override suspend fun load(): BaseResult<List<NavigationData>> {
        val info = WanNetwork.getInstance().getNavigationInfo()
        val result:BaseResult<List<NavigationData>> = BaseResult()
        if(info.errorCode == 0){
            result.data = info.data
            result.isEmpty = info.data.isEmpty()
        }else{
            result.isEmpty = true
        }
        result.isFromCache = false
        result.isPaging = false
        result.isFirst = true
        if(result.isFirst){
            result.data?.let {
                saveDataToPreference(it)
            }
        }
        return result
    }

    override suspend fun refresh(): BaseResult<List<NavigationData>> {
        return load()
    }

    override fun getTClass(): Type? {
        return object : TypeToken<List<NavigationData>>(){}.type
    }
}