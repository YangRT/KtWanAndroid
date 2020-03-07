package com.example.wanandroid.ui.mine.todo.unfinished

import android.util.Log
import android.widget.Toast
import com.example.wanandroid.MyApplication
import com.example.wanandroid.base.BaseViewModel
import com.example.wanandroid.base.PageStatus
import com.example.wanandroid.data.model.TodoEvent
import com.example.wanandroid.repository.TodoRepository


/**
 * @program: WanAndroid
 *
 * @description: todo未完成事件 vm
 *
 * @author: YangRT
 *
 * @create: 2020-02-21 18:38
 **/

class UnfinishedViewModel:BaseViewModel<TodoEvent,TodoRepository>() {

    init {
        repository = TodoRepository(0,"unfinished")
        isFirst = false
    }

    fun loadNextPage(){
        launch({
            if(!isFirst){
                val result = repository.loadNextPage()
                dealWithResult(result)
            }
        },{
            Log.e("BaseViewModel",it.message)
            status.postValue(PageStatus.LOAD_MORE_FAILED)
        })
    }

    fun changeType(type:Int){
        launch({
            val result = repository.changeType(type)
            dealWithResult(result)
        },{
            Toast.makeText(MyApplication.context,"网络错误！", Toast.LENGTH_SHORT).show()
        })
    }
}