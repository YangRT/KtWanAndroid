package com.example.wanandroid.ui.square

import com.example.wanandroid.base.BaseArticleModel
import com.example.wanandroid.base.BaseViewModel
import com.example.wanandroid.base.PageStatus
import com.example.wanandroid.repository.SquareRepository


/**
 * @program: WanAndroid
 *
 * @description: 广场 vm
 *
 * @author: YangRT
 *
 * @create: 2020-02-18 22:46
 **/

class SquareViewModel:BaseViewModel<BaseArticleModel,SquareRepository>() {

    init {
        repository = SquareRepository()

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