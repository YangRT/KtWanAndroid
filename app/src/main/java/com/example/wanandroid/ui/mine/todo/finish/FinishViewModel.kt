package com.example.wanandroid.ui.mine.todo.finish

import android.util.Log
import android.widget.Toast
import com.example.wanandroid.MyApplication
import com.example.wanandroid.base.BaseMvvmRepository
import com.example.wanandroid.base.BaseViewModel
import com.example.wanandroid.base.PageStatus
import com.example.wanandroid.data.model.TodoEvent
import com.example.wanandroid.repository.TodoRepository


/**
 * @program: WanAndroid
 *
 * @description: todo完成事件 vm
 *
 * @author: YangRT
 *
 * @create: 2020-02-21 18:36
 **/

class FinishViewModel:BaseViewModel<TodoEvent,TodoRepository>() {

    init {
        repository = TodoRepository(1,"finish")
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