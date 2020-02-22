package com.example.wanandroid.data.model


/**
 * @program: WanAndroid
 *
 * @description: 搜索热词 数据
 *
 * @author: YangRT
 *
 * @create: 2020-02-22 11:10
 **/

data class HotWordInfo(
    val data: List<HotWord>,
    val errorCode: Int,
    val errorMsg: String
)

data class HotWord(
    val id: Int,
    val link: String,
    val name: String,
    val order: Int,
    val visible: Int
)