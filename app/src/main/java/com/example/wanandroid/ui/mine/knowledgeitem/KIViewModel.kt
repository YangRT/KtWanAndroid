package com.example.wanandroid.ui.mine.knowledgeitem

import com.example.wanandroid.base.BaseArticleModel
import com.example.wanandroid.base.BaseViewModel
import com.example.wanandroid.base.PageStatus
import com.example.wanandroid.repository.KIRepository

/**
 * @program: WanAndroid
 *
 * @description: 知识体系 item vm
 *
 * @author: YangRT
 *
 * @create: 2020-02-21 15:16
 **/

class KIViewModel(id:Int,key:String):BaseViewModel<BaseArticleModel,KIRepository>() {

    init {
        repository = KIRepository(id,key)
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