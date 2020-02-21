package com.example.wanandroid.ui.mine.share

import com.example.wanandroid.base.BaseArticleModel
import com.example.wanandroid.base.BaseViewModel
import com.example.wanandroid.base.PageStatus
import com.example.wanandroid.repository.ShareRepository


/**
 * @program: WanAndroid
 *
 * @description: 分享文章列表 ui
 *
 * @author: YangRT
 *
 * @create: 2020-02-21 17:41
 **/

class ShareViewModel:BaseViewModel<BaseArticleModel,ShareRepository>() {

    init {
        repository = ShareRepository()
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