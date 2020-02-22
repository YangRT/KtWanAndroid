package com.example.wanandroid.ui.square.share

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wanandroid.MyApplication
import com.example.wanandroid.data.network.WanNetwork
import kotlinx.coroutines.launch


/**
 * @program: WanAndroid
 *
 * @description: 分享文章 vm
 *
 * @author: YangRT
 *
 * @create: 2020-02-22 16:28
 **/

class ShareViewModel:ViewModel() {

    var shareResponse = MutableLiveData<Int>()

    init {
        shareResponse.value = 0
    }

    fun shareArticle(title:String,link:String){
        launch({
            val info = WanNetwork.getInstance().shareArticle(title, link)
            if (info.errorCode == 0){
                shareResponse.postValue(1)
                Toast.makeText(MyApplication.context,"分享成功！",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(MyApplication.context,"分享失败！"+info.errorMsg,Toast.LENGTH_SHORT).show()
            }
        },{
            Toast.makeText(MyApplication.context,"网络错误！", Toast.LENGTH_SHORT).show()
        })

    }

    private fun launch(block: suspend () -> Unit, error: suspend (Throwable) -> Unit) = viewModelScope.launch {
        try {
            block()
        } catch (e: Throwable) {
            error(e)
        }
    }
}