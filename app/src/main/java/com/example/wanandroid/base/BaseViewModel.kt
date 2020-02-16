package com.example.wanandroid.base

import android.util.Log
import android.widget.Toast
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wanandroid.MyApplication
import kotlinx.coroutines.launch


/**
 * @program: WanAndroid
 *
 * @description: mvvm架构vm层基类
 *
 * @author: YangRT
 *
 * @create: 2020-02-15 11:32
 **/

class BaseViewModel<D,M : BaseMvvmRepository<List<D>>>:ViewModel(), LifecycleObserver {

    var data = MutableLiveData<ObservableArrayList<D>>()

    var refresh = MutableLiveData<Boolean>()

    lateinit var repository:M

    var status = MutableLiveData<PageStatus>()

    var isRequesting = false

    var isFirst = true

    init {
        data.value = ObservableArrayList<D>()
        status.value = PageStatus.LOADING
    }


    fun getCacheData(){
        launch({
            var result = repository.getCacheData()
            dealWithResult(result)
            if (repository.isNeedToUpdate()){
                requestData()
            }
        },{
            Log.e("BaseViewModel",it.message)
        })
    }

    private fun requestData(){
        launch({

            var result = repository.requestData()
            isFirst = false
            dealWithResult(result)
        },{
            Log.e("BaseViewModel",it.message)
            isFirst = false
            Toast.makeText(MyApplication.context,"网络错误！",Toast.LENGTH_SHORT).show()
        })
    }

    fun refresh(){
        if (status.value != PageStatus.LOADING){
            refresh.value = true
            launch({
                var result = repository.getCacheData()
                dealWithResult(result)
            },{
                refresh.value = false
                Log.e("BaseViewModel",it.message)
                status.postValue(PageStatus.REFRESH_ERROR)
            })
        }

    }

    private fun dealWithResult(result: BaseResult<List<D>>){
       if(result.isEmpty){
           if(!result.isFromCache && data.value?.size == 0){
               status.postValue(PageStatus.EMPTY)
           }else if (!result.isFromCache && result.isFirst){
               status.postValue(PageStatus.EMPTY)
           }else if(!result.isFromCache && result.isPaging){
               status.postValue(PageStatus.NO_MORE_DATA)
           }
       } else{
               if(result.isFirst){
                    data.value?.clear()
               }
               result.data?.let { data.value?.addAll(it) }
               data.postValue(data.value)
               status.postValue(PageStatus.SHOW_CONTENT)

       }
    }

    private fun launch(block: suspend () -> Unit, error: suspend (Throwable) -> Unit) = viewModelScope.launch {
        try {
            block()
        } catch (e: Throwable) {
            error(e)
        }
    }

    }


