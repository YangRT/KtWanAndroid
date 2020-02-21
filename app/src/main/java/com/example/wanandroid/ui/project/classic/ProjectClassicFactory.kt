package com.example.wanandroid.ui.project.classic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


/**
 * @program: WanAndroid
 *
 * @description: 项目分类 item viewmodel 创建者
 *
 * @author: YangRT
 *
 * @create: 2020-02-21 15:58
 **/

class ProjectClassicFactory(private val cid:Int,private val key:String):ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProjectArticleViewModel(cid,key) as T
    }
}