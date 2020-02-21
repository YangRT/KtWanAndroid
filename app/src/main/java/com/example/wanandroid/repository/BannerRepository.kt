package com.example.wanandroid.repository

import com.example.wanandroid.base.BaseMvvmRepository
import com.example.wanandroid.base.BaseResult
import com.example.wanandroid.data.network.WanNetwork
import com.example.wanandroid.ui.mainPage.banner.BannerModel
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


/**
 * @program: WanAndroid
 *
 * @description: Banner repository
 *
 * @author: YangRT
 *
 * @create: 2020-02-19 11:32
 **/

class BannerRepository:BaseMvvmRepository<List<BannerModel>>(false,"banner",null){

    override suspend fun load(): BaseResult<List<BannerModel>> {
        val info = WanNetwork.getInstance().getBanner()
        val result:BaseResult<List<BannerModel>> = BaseResult()
        if(info.errorCode == 0){
            val list = info.data
            val resultList = ArrayList<BannerModel>()
            for (item in list.iterator()){
                val bannerModel = BannerModel()
                bannerModel.title = item.title
                bannerModel.imagePath = item.imagePath
                bannerModel.link = item.url
                bannerModel.id = item.id
                resultList.add(bannerModel)
            }
            result.isEmpty = resultList.size == 0
            result.isFirst = true
            result.isFromCache = false
            result.data = resultList
            result.isPaging = false
        }else{
                result.isEmpty = true
                result.isFirst = pageNum==0
                result.msg = info.errorMsg
                result.isFromCache = false
                result.isPaging = false
        }
        if(result.isFirst){
            result.data?.let {
                saveDataToPreference(it)
            }
        }
        return result
    }

    override suspend fun refresh(): BaseResult<List<BannerModel>> {
        return load()
    }

    override fun getTClass(): Type? {
        return object : TypeToken<List<BannerModel>>(){}.type
    }

}