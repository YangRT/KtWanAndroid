package com.example.wanandroid.data.model


/**
 * @program: WanAndroid
 *
 * @description: 获取知识体系数据
 *
 * @author: YangRT
 *
 * @create: 2020-02-19 21:15
 **/

data class KnowledgeInfo(
    val `data`: List<KnowledgeData>,
    val errorCode: Int,
    val errorMsg: String
)

data class KnowledgeData(
    val children: List<KnowledgeChildren>,
    val courseId: Int,
    val id: Int,
    val name: String,
    val order: Int,
    val parentChapterId: Int,
    val userControlSetTop: Boolean,
    val visible: Int
)

data class KnowledgeChildren(
    val children: List<Any>,
    val courseId: Int,
    val id: Int,
    val name: String,
    val order: Int,
    val parentChapterId: Int,
    val userControlSetTop: Boolean,
    val visible: Int
)