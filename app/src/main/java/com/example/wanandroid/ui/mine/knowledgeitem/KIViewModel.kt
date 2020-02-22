package com.example.wanandroid.ui.mine.knowledgeitem

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.wanandroid.MyApplication
import com.example.wanandroid.base.BaseArticleModel
import com.example.wanandroid.base.BaseViewModel
import com.example.wanandroid.base.CollectResponse
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

    var collectResponse = MutableLiveData<CollectResponse>()

    init {
        repository = KIRepository(id,key)
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


    fun addCollect(id:Int,position:Int){
        launch({
            val response = repository.addCollect(id)
            if (response.errorCode == 0){
                val result = CollectResponse()
                result.position = position
                result.type = 0
                collectResponse.postValue(result)
                Toast.makeText(MyApplication.context,"收藏成功！", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(MyApplication.context,"收藏失败！", Toast.LENGTH_SHORT).show()
            }
        },{
            Toast.makeText(MyApplication.context,"网络错误，收藏失败！", Toast.LENGTH_SHORT).show()
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
                Toast.makeText(MyApplication.context,"取消收藏成功！", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(MyApplication.context,"取消收藏失败！", Toast.LENGTH_SHORT).show()
            }
        },{
            Toast.makeText(MyApplication.context,"网络错误，取消收藏失败！", Toast.LENGTH_SHORT).show()
        })
    }
}