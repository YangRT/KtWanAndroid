package com.example.wanandroid.data.network.api


/**
 * @program: WanAndroid
 *
 * @description: 积分明细 数据
 *
 * @author: YangRT
 *
 * @create: 2020-02-20 15:25
 **/

data class PointInfo(

    val data: PointData,
    val errorCode: Int,
    val errorMsg: String
)

data class PointData(
    val curPage: Int,
    val datas: List<PointDetail>,
    val offset: Int,
    val over: Boolean,
    val pageCount: Int,
    val size: Int,
    val total: Int
)

data class PointDetail(
    val coinCount: Int,
    val date: Long,
    val desc: String,
    val id: Int,
    val reason: String,
    val type: Int,
    val userId: Int,
    val userName: String
)