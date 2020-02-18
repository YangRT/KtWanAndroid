package com.example.wanandroid.ui.mainPage

import android.util.Log
import com.example.wanandroid.base.BaseArticleModel
import com.example.wanandroid.base.BaseViewModel
import com.example.wanandroid.base.PageStatus
import com.example.wanandroid.repository.MainPageRepository


/**
 * @program: WanAndroid
 *
 * @description: 主页 vm
 *
 * @author: YangRT
 *
 * @create: 2020-02-18 15:22
 **/

class MainPageViewModel: BaseViewModel<BaseArticleModel, MainPageRepository>() {

    init {
        repository = MainPageRepository()

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