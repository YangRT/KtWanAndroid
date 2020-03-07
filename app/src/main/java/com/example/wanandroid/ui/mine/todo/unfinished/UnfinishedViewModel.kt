package com.example.wanandroid.ui.mine.todo.unfinished

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.wanandroid.MyApplication
import com.example.wanandroid.base.BaseViewModel
import com.example.wanandroid.base.PageStatus
import com.example.wanandroid.data.model.TodoEvent
import com.example.wanandroid.repository.TodoRepository
import com.example.wanandroid.ui.mine.todo.ChangeRecord


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

    var deleteResponse = MutableLiveData<ChangeRecord>()
    var competeResponse = MutableLiveData<ChangeRecord>()


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

    fun deleteEvent(id:Int,position:Int){
        launch({
            val result = repository.deleteEvent(id)
            val record = ChangeRecord()
            record.position = position
            record.result = result
            deleteResponse.postValue(record)
        },{
            val record = ChangeRecord()
            record.position = position
            record.result = false
            deleteResponse.postValue(record)
        })

    }

    fun competeEvent(id:Int,position: Int){
        launch({
            val result = repository.competeEvent(id)
            val record = ChangeRecord()
            record.position = position
            record.result = result
            competeResponse.postValue(record)
        },{
            val record = ChangeRecord()
            record.position = position
            record.result = false
            competeResponse.postValue(record)
        })
    }
}