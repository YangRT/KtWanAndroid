package com.example.wanandroid.repository

import com.example.wanandroid.base.BaseArticleModel
import com.example.wanandroid.base.BaseMvvmRepository
import com.example.wanandroid.base.BaseResult
import com.example.wanandroid.data.model.ResponseInfo
import com.example.wanandroid.data.network.WanNetwork
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


/**
 * @program: WanAndroid
 *
 * @description: 分享文章 repository
 *
 * @author: YangRT
 *
 * @create: 2020-02-21 17:19
 **/

class ShareRepository:BaseMvvmRepository<List<BaseArticleModel>>(true,"share",null) {

    init {
        pageNum = 1
    }

    override suspend fun load(): BaseResult<List<BaseArticleModel>> {
        val info = WanNetwork.getInstance().getShareArticle(pageNum)
        val result = BaseResult<List<BaseArticleModel>>()
        if (info.errorCode == 0){
            pageNum = if(isRefreshing){ 2 }else{ pageNum+1}
            val list = info.data.shareArticles.datas
            val resultList = ArrayList<BaseArticleModel>()
            for(item in list.iterator()){
                var baseArticle: BaseArticleModel
                if(item.envelopePic != ""){
                    baseArticle = BaseArticleModel(BaseArticleModel.PROJECT)
                    baseArticle.description = item.desc
                    baseArticle.imagePath = item.envelopePic
                }else{
                    baseArticle = BaseArticleModel(BaseArticleModel.NORMAL)
                    baseArticle.classic = item.superChapterName+"/"+item.chapterName
                }
                baseArticle.link = item.link
                baseArticle.id = item.id
                baseArticle.time = item.niceDate
                baseArticle.title = item.title
                baseArticle.isCollect = item.collect
                if(item.author != ""){
                    baseArticle.author = item.author
                }else{
                    baseArticle.author = item.shareUser
                }
                resultList.add(baseArticle)
            }
            result.isEmpty = resultList.size == 0
            result.isFirst = pageNum == 2
            result.data = resultList
        }else{
            result.isEmpty = true
            result.msg = info.errorMsg
            result.isFirst = pageNum == 1
        }
        result.isPaging = true
        result.isFromCache = false
        if(result.isFirst){
            result.data?.let {
                saveDataToPreference(it)
            }
        }
        return result
    }

    override suspend fun refresh(): BaseResult<List<BaseArticleModel>> {
        isRefreshing = true
        pageNum = 1
        return load()
    }

    suspend fun loadNextPage():BaseResult<List<BaseArticleModel>>{
        isRefreshing = false
        return load()
    }

    override fun getTClass(): Type? {
        return object : TypeToken<List<BaseArticleModel>>(){}.type
    }

    suspend fun addCollect(id:Int): ResponseInfo {
        return WanNetwork.getInstance().addCollect(id)
    }

    suspend fun unCollect(id:Int): ResponseInfo {
        return WanNetwork.getInstance().unCollect(id)
    }
}