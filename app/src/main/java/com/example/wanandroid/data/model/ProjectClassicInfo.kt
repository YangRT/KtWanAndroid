package com.example.wanandroid.data.model


/**
 * @program: WanAndroid
 *
 * @description: 项目分类 tab
 *
 * @author: YangRT
 *
 * @create: 2020-02-20 11:03
 **/

data class ProjectClassicInfo(

    val data: List<ProjectClassic>,
    val errorCode: Int,
    val errorMsg: String
)

data class ProjectClassic(
    val children: List<Any>,
    val courseId: Int,
    val id: Int,
    val name: String,
    val order: Int,
    val parentChapterId: Int,
    val userControlSetTop: Boolean,
    val visible: Int
)