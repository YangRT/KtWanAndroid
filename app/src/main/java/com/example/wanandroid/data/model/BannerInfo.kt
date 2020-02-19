package com.example.wanandroid.data.model


/**
 * @program: WanAndroid
 *
 * @description: banner 数据
 *
 * @author: YangRT
 *
 * @create: 2020-02-19 11:35
 **/

data class BannerInfo(
    val `data`: List<BannerData>,
    val errorCode: Int,
    val errorMsg: String
)

data class BannerData(
    val desc: String,
    val id: Int,
    val imagePath: String,
    val isVisible: Int,
    val order: Int,
    val title: String,
    val type: Int,
    val url: String
)