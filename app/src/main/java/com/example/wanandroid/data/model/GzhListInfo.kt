package com.example.wanandroid.data.model


/**
 * @program: WanAndroid
 *
 * @description: 公众号列表数据
 *
 * @author: YangRT
 *
 * @create: 2020-02-17 11:46
 **/

data class GzhListInfo(
    val data: List<GzhListData>,
    val errorCode: Int,
    val errorMsg: String
)

data class GzhListData(
    val children: List<Any>,
    val courseId: Int,
    val id: Int,
    val name: String,
    val order: Int,
    val parentChapterId: Int,
    val userControlSetTop: Boolean,
    val visible: Int
)