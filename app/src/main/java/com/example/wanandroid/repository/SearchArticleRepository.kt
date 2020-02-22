package com.example.wanandroid.repository

import com.airbnb.lottie.L
import com.example.wanandroid.base.BaseArticleModel
import com.example.wanandroid.base.BaseMvvmRepository
import com.example.wanandroid.base.BaseResult
import com.example.wanandroid.data.network.WanNetwork
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


/**
 * @program: WanAndroid
 *
 * @description: 搜索文章 repository
 *
 * @author: YangRT
 *
 * @create: 2020-02-22 11:30
 **/

class SearchArticleRepository(private val k:String):BaseMvvmRepository<List<BaseArticleModel>>(true,k,null) {

    override suspend fun load(): BaseResult<List<BaseArticleModel>> {
        val info = WanNetwork.getInstance().getSearchArticle(pageNum,k)
        val result:BaseResult<List<BaseArticleModel>> = BaseResult()
        if(info.errorCode == 0){
            pageNum = if(isRefreshing){ 1 }else{ pageNum+1}
            val list = info.data.datas
            val resultList = ArrayList<BaseArticleModel>()
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
                var title = item.title.replace("<em class='highlight'>","")
                title = title.replace("</em>","")
                title = title.replace("&amp;","")
                title = title.replace("&mdash;","")
                baseArticle.title = title
                baseArticle.isCollect = item.collect
                if(!item.author.equals("")){
                    baseArticle.author = item.author
                }else{
                    baseArticle.author = item.shareUser
                }
                resultList.add(baseArticle)
            }
            result.data = resultList
            result.isEmpty = resultList.isEmpty()
            result.isFirst = pageNum == 1
        }else{
            result.isEmpty = true
            result.isFirst = pageNum== 0
            result.msg = info.errorMsg
        }
        result.isFromCache = false
        result.isPaging = true
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
        return object : TypeToken<List<BaseArticleModel>>(){}.type
    }
}