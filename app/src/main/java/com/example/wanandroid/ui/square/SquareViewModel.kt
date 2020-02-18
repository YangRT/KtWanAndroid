package com.example.wanandroid.ui.square

import android.util.Log
import com.example.wanandroid.base.BaseArticleModel
import com.example.wanandroid.base.BaseViewModel
import com.example.wanandroid.base.PageStatus
import com.example.wanandroid.repository.ProjectRepository
import com.example.wanandroid.repository.SqureRepository


/**
 * @program: WanAndroid
 *
 * @description: 广场 vm
 *
 * @author: YangRT
 *
 * @create: 2020-02-18 22:46
 **/

class SquareViewModel:BaseViewModel<BaseArticleModel,SqureRepository>() {

    init {
        repository = SqureRepository()

    }

    fun loadNextPage(){
        launch({
            if(!isFirst){
                var result = repository.loadNextPage()
                dealWithResult(result)
            }
        },{
            Log.e("BaseViewModel",it.message)
            status.postValue(PageStatus.LOAD_MORE_FAILED)
        })
    }
}