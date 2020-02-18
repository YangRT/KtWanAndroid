package com.example.wanandroid.ui.project

import android.util.Log
import com.example.wanandroid.base.BaseArticleModel
import com.example.wanandroid.base.BaseViewModel
import com.example.wanandroid.base.PageStatus
import com.example.wanandroid.repository.MainPageRepository
import com.example.wanandroid.repository.ProjectRepository


/**
 * @program: WanAndroid
 *
 * @description: project vm
 *
 * @author: YangRT
 *
 * @create: 2020-02-18 22:25
 **/

class ProjectViewModel:BaseViewModel<BaseArticleModel,ProjectRepository>() {

    init {
        repository = ProjectRepository()

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