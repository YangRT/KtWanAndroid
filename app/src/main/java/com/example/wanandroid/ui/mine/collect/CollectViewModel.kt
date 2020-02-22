package com.example.wanandroid.ui.mine.collect

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.wanandroid.MyApplication
import com.example.wanandroid.base.BaseArticleModel
import com.example.wanandroid.base.BaseViewModel
import com.example.wanandroid.base.CollectResponse
import com.example.wanandroid.base.PageStatus
import com.example.wanandroid.repository.CollectRepository


/**
 * @program: WanAndroid
 *
 * @description: 收藏文章 vm
 *
 * @author: YangRT
 *
 * @create: 2020-02-21 17:36
 **/

class CollectViewModel:BaseViewModel<BaseArticleModel,CollectRepository>() {

    var collectResponse = MutableLiveData<CollectResponse>()

    init {
        repository = CollectRepository()
        collectResponse.value = CollectResponse()
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




    fun unCollect(id:Int,originId:Int,position: Int){
        launch({
            val response = repository.unCollect(id,originId)
            if (response.errorCode == 0){
                val result = CollectResponse()
                result.position = position
                result.type = 1
                collectResponse.postValue(result)
                Toast.makeText(MyApplication.context,"取消收藏成功！", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(MyApplication.context,"取消收藏失败！", Toast.LENGTH_SHORT).show()
            }
        },{
            Toast.makeText(MyApplication.context,"网络错误，取消收藏失败！", Toast.LENGTH_SHORT).show()
        })
    }
}