package com.example.wanandroid.repository

import com.example.wanandroid.base.BaseMvvmRepository
import com.example.wanandroid.base.BaseResult
import com.example.wanandroid.data.model.MineData
import com.example.wanandroid.data.network.WanNetwork
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


/**
 * @program: WanAndroid
 *
 * @description: 我的 repository
 *
 * @author: YangRT
 *
 * @create: 2020-02-19 16:09
 **/

class MineRepository:BaseMvvmRepository<List<MineData>>(false,"mine",null) {

    override suspend fun load(): BaseResult<List<MineData>> {
        val info = WanNetwork.getInstance().getMine()
        val result = BaseResult<List<MineData>>()
        if(info.errorCode == 0){
            val list = ArrayList<MineData>()
            list.add(info.data)
            result.data = list
            result.isEmpty = false
        }else{
            result.isEmpty = true
            result.msg = info.errorMsg
        }
        result.isPaging = false
        result.isFromCache = false
        result.isFirst = true
        if(result.isFirst){
            result.data?.let {
                saveDataToPreference(it)
            }
        }
        return result
    }

    override suspend fun refresh(): BaseResult<List<MineData>> {
        return load()
    }

    override fun getTClass(): Type? {
        return object : TypeToken<List<MineData>>(){}.type
    }
}