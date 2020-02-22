package com.example.wanandroid.ui.home

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
 * @description: home vm
 *
 * @author: YangRT
 *
 * @create: 2020-02-22 16:45
 **/

class HomeViewModel:ViewModel() {

    var exitResponse = MutableLiveData<Int>()

    init {
        exitResponse.value = 0
    }

    fun exit(){
        launch({
            val responseInfo = WanNetwork.getInstance().exit()
            if (responseInfo.errorCode == 0){
                exitResponse.postValue(1)
                Toast.makeText(MyApplication.context,"退出成功！", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(MyApplication.context,"退出失败！"+responseInfo.errorMsg, Toast.LENGTH_SHORT).show()
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