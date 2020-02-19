package com.example.wanandroid.repository

import com.example.wanandroid.base.BaseArticleModel
import com.example.wanandroid.base.BaseMvvmRepository
import com.example.wanandroid.base.BaseResult
import com.example.wanandroid.data.model.RankInfo
import com.example.wanandroid.data.model.RankItem
import com.example.wanandroid.data.network.WanNetwork
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


/**
 * @program: WanAndroid
 *
 * @description: 排行榜 repository
 *
 * @author: YangRT
 *
 * @create: 2020-02-19 17:30
 **/

class RankRepository:BaseMvvmRepository<List<RankItem>>(true,"rank",null) {

    init {
        pageNum = 1
    }

    override suspend fun load(): BaseResult<List<RankItem>> {
        val datas = WanNetwork.getInstance().getRank(pageNum)
        var result:BaseResult<List<RankItem>> = BaseResult()
        if(datas.errorCode == 0){
            pageNum = if(isRefreshing){ 2 }else{ pageNum+1}
            val list = datas.data.datas
            result.isEmpty = list.size == 0
            result.isFirst = pageNum == 1
            result.isFromCache = false
            result.data = list
            result.isPaging = true
        }else{
            result.isEmpty = true
            result.isFirst = pageNum==1
            result.msg = datas.errorMsg
            result.isFromCache = false
            result.isPaging = true
        }
        if(result.isFirst){
            result.data?.let {
                saveDataToPreference(it)
            }
        }
        return result
    }

    override suspend fun refresh(): BaseResult<List<RankItem>> {
        isRefreshing = true
        pageNum = 1
        return load()
    }

    suspend fun loadNextPage():BaseResult<List<RankItem>>{
        isRefreshing = false
        return load()
    }

    override fun getTClass(): Type? {
        return object : TypeToken<List<RankItem>>(){}.type
    }
}