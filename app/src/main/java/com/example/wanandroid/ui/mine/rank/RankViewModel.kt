package com.example.wanandroid.ui.mine.rank

import android.util.Log
import com.example.wanandroid.base.BaseViewModel
import com.example.wanandroid.base.PageStatus
import com.example.wanandroid.data.model.RankItem
import com.example.wanandroid.repository.RankRepository


/**
 * @program: WanAndroid
 *
 * @description: 排行榜 vm
 *
 * @author: YangRT
 *
 * @create: 2020-02-19 17:36
 **/

class RankViewModel:BaseViewModel<RankItem,RankRepository>() {

    init {
        repository = RankRepository()
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