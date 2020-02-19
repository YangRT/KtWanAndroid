package com.example.wanandroid.repository

import com.example.wanandroid.base.BaseMvvmRepository
import com.example.wanandroid.base.BaseResult
import com.example.wanandroid.data.model.MineData
import com.example.wanandroid.data.model.MineInfo
import com.example.wanandroid.data.network.WanNetwork


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
        val data = WanNetwork.getInstance().getMine()
        val result = BaseResult<List<MineData>>()
        if(data.errorCode == 0){
            val list = ArrayList<MineData>()
            list.add(data.data)
            result.data = list
            result.isEmpty = false
            result.isPaging = false
            result.isFromCache = false
            result.isFirst = true
        }else{
            result.isEmpty = true
            result.isPaging = false
            result.isFromCache = false
            result.isFirst = true
            result.msg = data.errorMsg
        }
        return result
    }

    override suspend fun refresh(): BaseResult<List<MineData>> {
        return load()
    }
}