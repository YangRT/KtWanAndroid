package com.example.wanandroid.ui.mine.todo

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wanandroid.repository.TodoDetailRepository
import kotlinx.coroutines.launch


/**
 * @program: WanAndroid
 *
 * @description: todo详情 vm
 *
 * @author: YangRT
 *
 * @create: 2020-03-07 16:58
 **/

class TodoDetailViewModel:ViewModel(), LifecycleObserver {

    private val repository:TodoDetailRepository = TodoDetailRepository()
    val updateResponse = MutableLiveData<Boolean>()
    val addResponse = MutableLiveData<Boolean>()

    fun addEvent(event: Event){
        launch({
            val result = repository.addEvent(event.title,event.content,event.type,event.date)
            addResponse.postValue(result)

        },{
            addResponse.postValue(false)
        })
    }

    fun updateEvent(id:Int,event: Event){
        launch({
            val result = repository.updateEvent(id,event.title,event.content,event.type,event.date)
            updateResponse.postValue(result)

        },{
            updateResponse.postValue(false)
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