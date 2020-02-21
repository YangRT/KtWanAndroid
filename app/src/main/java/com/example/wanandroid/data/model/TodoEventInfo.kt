package com.example.wanandroid.data.model


/**
 * @program: WanAndroid
 *
 * @description: todo事件数据
 *
 * @author: YangRT
 *
 * @create: 2020-02-21 18:10
 **/

data class TodoEventInfo(
    val data: TodoEventData,
    val errorCode: Int,
    val errorMsg: String
)

data class TodoEventData(
    val curPage: Int,
    val datas: List<TodoEvent>,
    val offset: Int,
    val over: Boolean,
    val pageCount: Int,
    val size: Int,
    val total: Int
)

data class TodoEvent(
    val completeDate: Long,
    val completeDateStr: String,
    val content: String,
    val date: Long,
    val dateStr: String,
    val id: Int,
    val priority: Int,
    val status: Int,
    val title: String,
    val type: Int,
    val userId: Int
)