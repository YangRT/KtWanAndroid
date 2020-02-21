package com.example.wanandroid.ui.project.classic

import com.example.wanandroid.base.BaseArticleModel
import com.example.wanandroid.base.BaseViewModel
import com.example.wanandroid.base.PageStatus
import com.example.wanandroid.repository.ProjectArticleRepository


/**
 * @program: WanAndroid
 *
 * @description: 项目分类 文章 vm
 *
 * @author: YangRT
 *
 * @create: 2020-02-21 15:53
 **/

class ProjectArticleViewModel(cid:Int, key:String):BaseViewModel<BaseArticleModel,ProjectArticleRepository>() {

    init {
        repository = ProjectArticleRepository(cid, key)
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