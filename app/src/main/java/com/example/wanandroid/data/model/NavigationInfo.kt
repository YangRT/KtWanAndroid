package com.example.wanandroid.data.model


/**
 * @program: WanAndroid
 *
 * @description: 导航 数据
 *
 * @author: YangRT
 *
 * @create: 2020-02-19 22:29
 **/

data class NavigationInfo(
    val `data`: List<NavigationData>,
    val errorCode: Int,
    val errorMsg: String
)

data class NavigationData(
    val articles: List<Navigation>,
    val cid: Int,
    val name: String
)

data class Navigation(
    val apkLink: String,
    val audit: Int,
    val author: String,
    val canEdit: Boolean,
    val chapterId: Int,
    val chapterName: String,
    val collect: Boolean,
    val courseId: Int,
    val desc: String,
    val descMd: String,
    val envelopePic: String,
    val fresh: Boolean,
    val id: Int,
    val link: String,
    val niceDate: String,
    val niceShareDate: String,
    val origin: String,
    val prefix: String,
    val projectLink: String,
    val publishTime: Long,
    val selfVisible: Int,
    val shareDate: Long,
    val shareUser: String,
    val superChapterId: Int,
    val superChapterName: String,
    val tags: List<Any>,
    val title: String,
    val type: Int,
    val userId: Int,
    val visible: Int,
    val zan: Int
)