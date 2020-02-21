package com.example.wanandroid.ui.mine.gzh

import com.example.wanandroid.base.BaseArticleModel
import com.example.wanandroid.base.BaseViewModel
import com.example.wanandroid.base.PageStatus
import com.example.wanandroid.repository.GzhRepository


/**
 * @program: WanAndroid
 *
 * @description: 公众号文章 vm
 *
 * @author: YangRT
 *
 * @create: 2020-02-21 16:50
 **/

class GzhViewModel(cid:Int,key:String):BaseViewModel<BaseArticleModel,GzhRepository>() {

    init {
        repository = GzhRepository(cid, key)
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