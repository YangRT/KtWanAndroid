package com.example.wanandroid.repository

import android.util.Log
import com.example.wanandroid.base.BaseArticleModel
import com.example.wanandroid.base.BaseMvvmRepository
import com.example.wanandroid.base.BaseResult
import com.example.wanandroid.data.network.WanNetwork
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


/**
 * @program: WanAndroid
 *
 * @description: 主页 repository
 *
 * @author: YangRT
 *
 * @create: 2020-02-18 14:51
 **/

class MainPageRepository:BaseMvvmRepository<List<BaseArticleModel>>(true,"mainpage",null) {

    override suspend fun load(): BaseResult<List<BaseArticleModel>> {
        Log.e("BaseRepository","load")
        val datas = WanNetwork.getInstance().getArticle(pageNum)
        var result:BaseResult<List<BaseArticleModel>> = BaseResult()
        if(datas.errorCode == 0){
            pageNum = if(isRefreshing){ 1 }else{ pageNum+1}
            val list = datas.data.datas
            var resultList = ArrayList<BaseArticleModel>()
            for (item in list.iterator()){
                var baseArticle: BaseArticleModel
                if(!item.envelopePic.equals("")){
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
                if(!item.author.equals("")){
                    baseArticle.author = item.author
                }else{
                    baseArticle.author = item.shareUser
                }
                resultList.add(baseArticle)
            }
            result.isEmpty = resultList.size == 0
            result.isFirst = pageNum == 1
            result.isFromCache = false
            result.data = resultList
            result.isPaging = true
        }else{
            result.isEmpty = true
            result.isFirst = pageNum==0
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

    override suspend fun refresh(): BaseResult<List<BaseArticleModel>> {
        isRefreshing = true
        pageNum = 0
        return load()
    }

    suspend fun loadNextPage():BaseResult<List<BaseArticleModel>>{
        isRefreshing = false
        return load()
    }

    override fun getTClass(): Type? {
        return object :TypeToken<List<BaseArticleModel>>(){}.type
    }
}