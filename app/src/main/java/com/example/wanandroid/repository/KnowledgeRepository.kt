package com.example.wanandroid.repository

import android.util.Log
import com.example.wanandroid.base.BaseMvvmRepository
import com.example.wanandroid.base.BaseResult
import com.example.wanandroid.data.model.KnowledgeChildren
import com.example.wanandroid.data.model.KnowledgeData
import com.example.wanandroid.data.model.RankItem
import com.example.wanandroid.data.network.WanNetwork
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


/**
 * @program: WanAndroid
 *
 * @description: 知识体系 repository
 *
 * @author: YangRT
 *
 * @create: 2020-02-19 21:22
 **/

class KnowledgeRepository:BaseMvvmRepository<List<KnowledgeData>>(false,"knowledge",null) {

    override suspend fun load(): BaseResult<List<KnowledgeData>> {
        Log.e("Knowledge","load")
        val data = WanNetwork.getInstance().getKnowledgeInfo()
        val result = BaseResult<List<KnowledgeData>>()
        if (data.errorCode == 0){
            Log.e("Knowledge","success")
            result.isFirst = true
            result.isFromCache = false
            result.isPaging = false
            result.isEmpty = data.data.isEmpty()
            result.data = data.data
        }else{
            result.isFirst = true
            result.isFromCache = false
            result.isPaging = false
            result.isEmpty = true
            result.msg = data.errorMsg
        }
        return result
    }

    override suspend fun refresh(): BaseResult<List<KnowledgeData>> {
        return load()
    }

    override fun getTClass(): Type? {
        return object : TypeToken<List<KnowledgeData>>(){}.type
    }

}