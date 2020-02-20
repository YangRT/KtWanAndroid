package com.example.wanandroid.repository

import com.example.wanandroid.base.BaseArticleModel
import com.example.wanandroid.base.BaseMvvmRepository
import com.example.wanandroid.base.BaseResult
import com.example.wanandroid.data.network.WanNetwork
import com.example.wanandroid.ui.tab.TabTitleInfo
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


/**
 * @program: WanAndroid
 *
 * @description: tab repository
 *
 * @author: YangRT
 *
 * @create: 2020-02-20 10:45
 **/

class TabRepository(key:String):BaseMvvmRepository<List<TabTitleInfo>>(false,key,null) {

    override suspend fun load(): BaseResult<List<TabTitleInfo>> {
        var result:BaseResult<List<TabTitleInfo>> = BaseResult()
        if(mCachedPreferenceKey == "gzh"){
            val data = WanNetwork.getInstance().getGzhList()
            if (data.errorCode == 0){
                val list = data.data
                val resultList:ArrayList<TabTitleInfo> = ArrayList()
                for (item in list.iterator()){
                    val tabTitleInfo = TabTitleInfo()
                    tabTitleInfo.id = item.id
                    tabTitleInfo.title = item.name
                    resultList.add(tabTitleInfo)
                }
                result.isEmpty = resultList.isEmpty()
                result.data = resultList
            }else{
                result.msg = data.errorMsg
                result.isEmpty = true
            }
        }else{
            val data = WanNetwork.getInstance().getProjectClassic()
            if(data.errorCode == 0){
                val list = data.data
                val resultList:ArrayList<TabTitleInfo> = ArrayList()
                for(item in list.iterator()){
                    val tabTitleInfo = TabTitleInfo()
                    tabTitleInfo.id = item.id
                    tabTitleInfo.title = item.name
                    resultList.add(tabTitleInfo)
                }
                result.isEmpty = resultList.isEmpty()
                result.data = resultList
            }else{
                result.msg = data.errorMsg
                result.isEmpty = true
            }

        }
        result.isFirst = true
        result.isFromCache = false
        result.isPaging = false
        return result
    }

    override suspend fun refresh(): BaseResult<List<TabTitleInfo>> {
        return load()
    }

    override fun getTClass(): Type? {
        return object : TypeToken<List<TabTitleInfo>>(){}.type
    }
}