package com.example.wanandroid.repository

import com.example.wanandroid.base.BaseMvvmRepository
import com.example.wanandroid.base.BaseResult
import com.example.wanandroid.data.model.HotWord
import com.example.wanandroid.data.network.WanNetwork
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


/**
 * @program: WanAndroid
 *
 * @description: 搜索热词 repository
 *
 * @author: YangRT
 *
 * @create: 2020-02-22 11:14
 **/

class HotWordRepository:BaseMvvmRepository<List<HotWord>>(false,"hotWord",null) {

    override suspend fun load(): BaseResult<List<HotWord>> {
        val info = WanNetwork.getInstance().getHotWord()
        val result:BaseResult<List<HotWord>> = BaseResult()
        if (info.errorCode == 0){
            result.data = info.data
            result.isEmpty = info.data.isEmpty()
        }else{
            result.isEmpty = true
            result.msg = info.errorMsg
        }
        result.isPaging = false
        result.isFirst = true
        result.isFromCache = false
        return result
    }

    override suspend fun refresh(): BaseResult<List<HotWord>> {
        return load()
    }

    override fun getTClass(): Type? {
        return object : TypeToken<List<HotWord>>(){}.type
    }
}