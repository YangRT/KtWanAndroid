package com.example.wanandroid.ui.mainPage.search.article

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.wanandroid.MyApplication
import com.example.wanandroid.base.BaseArticleModel
import com.example.wanandroid.base.BaseViewModel
import com.example.wanandroid.base.CollectResponse
import com.example.wanandroid.base.PageStatus
import com.example.wanandroid.repository.SearchArticleRepository


/**
 * @program: WanAndroid
 *
 * @description: 搜索文章列表 vm
 *
 * @author: YangRT
 *
 * @create: 2020-02-22 11:35
 **/

class SearchArticleViewModel(key:String):BaseViewModel<BaseArticleModel,SearchArticleRepository>() {

    var collectResponse = MutableLiveData<CollectResponse>()

    init {
        collectResponse.value = CollectResponse()
        repository = SearchArticleRepository(key)
    }

    fun loadNextPage(){
        launch({
            if(!isFirst){
                var result = repository.loadNextPage()
                dealWithResult(result)
            }
        },{
            status.postValue(PageStatus.LOAD_MORE_FAILED)
        })
    }


    fun addCollect(id:Int,position:Int){
        launch({
            val response = repository.addCollect(id)
            if (response.errorCode == 0){
                val result = CollectResponse()
                result.position = position
                result.type = 0
                collectResponse.postValue(result)
                Toast.makeText(MyApplication.context,"收藏成功！",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(MyApplication.context,"收藏失败！",Toast.LENGTH_SHORT).show()
            }
        },{
            Toast.makeText(MyApplication.context,"网络错误，收藏失败！",Toast.LENGTH_SHORT).show()
        })
    }

    fun unCollect(id:Int,position: Int){
        launch({
            val response = repository.unCollect(id)
            if (response.errorCode == 0){
                val result = CollectResponse()
                result.position = position
                result.type = 1
                collectResponse.postValue(result)
                Toast.makeText(MyApplication.context,"取消收藏成功！",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(MyApplication.context,"取消收藏失败！",Toast.LENGTH_SHORT).show()
            }
        },{
            Toast.makeText(MyApplication.context,"网络错误，取消收藏失败！",Toast.LENGTH_SHORT).show()
        })
    }
}