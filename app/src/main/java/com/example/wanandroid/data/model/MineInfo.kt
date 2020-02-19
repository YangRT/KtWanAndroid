package com.example.wanandroid.data.model


/**
 * @program: WanAndroid
 *
 * @description: 我的 数据
 *
 * @author: YangRT
 *
 * @create: 2020-02-19 16:12
 **/

data class MineInfo(
    val data:  MineData,
    val errorCode: Int,
    val errorMsg: String
)

data class  MineData(
    val coinCount: Int,
    val level: Int,
    val rank: Int,
    val userId: Int,
    val username: String
)