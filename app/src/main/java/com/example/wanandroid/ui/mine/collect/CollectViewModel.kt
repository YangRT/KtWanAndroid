package com.example.wanandroid.ui.mine.collect

import com.example.wanandroid.base.BaseArticleModel
import com.example.wanandroid.base.BaseViewModel
import com.example.wanandroid.base.PageStatus
import com.example.wanandroid.repository.CollectRepository


/**
 * @program: WanAndroid
 *
 * @description: 收藏文章 vm
 *
 * @author: YangRT
 *
 * @create: 2020-02-21 17:36
 **/

class CollectViewModel:BaseViewModel<BaseArticleModel,CollectRepository>() {

    init {
        repository = CollectRepository()
    }

    fun loadNextPage(){
        launch({
            if(!isFirst){
                val result = repository.loadNextPage()
                dealWithResult(result)
            }
        },{
            status.postValue(PageStatus.LOAD_MORE_FAILED)
        })
    }
}