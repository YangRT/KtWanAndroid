package com.example.wanandroid.ui.mainPage.search.article

import android.util.Log
import com.example.wanandroid.base.BaseArticleModel
import com.example.wanandroid.base.BaseViewModel
import com.example.wanandroid.base.PageStatus
import com.example.wanandroid.repository.SearchArticleRepository


/**
 * @program: WanAndroid
 *
 * @description: 搜索文章列表 vm
 *
 * @author: YangRT
 *
 * @create: 2020-02-22 11:35
 **/

class SearchArticleViewModel(key:String):BaseViewModel<BaseArticleModel,SearchArticleRepository>() {

    init {
        repository = SearchArticleRepository(key)
    }

    fun loadNextPage(){
        launch({
            if(!isFirst){
                var result = repository.loadNextPage()
                dealWithResult(result)
            }
        },{
            status.postValue(PageStatus.LOAD_MORE_FAILED)
        })
    }
}