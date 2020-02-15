package com.example.wanandroid.base

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

class BaseViewModel<D,M : BaseMvvmRepository<List<D>>>:ViewModel() {

    var data = MutableLiveData<ObservableArrayList<D>>()

    var refresh = MutableLiveData<Boolean>()

    lateinit var repository:M

    var status = MutableLiveData<PageStatus>()

    init {
        data.value = ObservableArrayList<D>()
        status.value = PageStatus.LOADING
    }


    fun getCacheData(){
        launch({
            var result = repository.getCacheData()
            if (repository.isNeedToUpdate()){
                requestData()
            }
        },{

        })

    }

    fun requestData(){
        launch({
            var result = repository.requestData()
        },{

        })
    }

    fun refresh(){
        refresh.value = true
        launch({
            var result = repository.getCacheData()
            refresh.value = false
        },{
            refresh.value = false
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