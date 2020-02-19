package com.example.wanandroid.data.model


/**
 * @program: WanAndroid
 *
 * @description: 排行榜 数据
 *
 * @author: YangRT
 *
 * @create: 2020-02-19 17:27
 **/

data class RankInfo(
    val data: RankData,
    val errorCode: Int,
    val errorMsg: String
)

data class RankData(
    val curPage: Int,
    val datas: List<RankItem>,
    val offset: Int,
    val over: Boolean,
    val pageCount: Int,
    val size: Int,
    val total: Int
)

data class RankItem(
    val coinCount: Int,
    val level: Int,
    val rank: Int,
    val userId: Int,
    val username: String
)