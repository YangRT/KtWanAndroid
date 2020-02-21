package com.example.wanandroid.ui.mine.point

import android.util.Log
import com.example.wanandroid.base.BaseViewModel
import com.example.wanandroid.base.PageStatus
import com.example.wanandroid.data.network.api.PointDetail
import com.example.wanandroid.repository.PointRepository


/**
 * @program: WanAndroid
 *
 * @description: 积分明细 vm
 *
 * @author: YangRT
 *
 * @create: 2020-02-21 14:47
 **/

class PointViewModel:BaseViewModel<PointDetail,PointRepository>() {

    init {
        repository = PointRepository()
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