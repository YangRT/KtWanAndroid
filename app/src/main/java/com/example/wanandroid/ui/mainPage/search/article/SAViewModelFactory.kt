package com.example.wanandroid.ui.mainPage.search.article

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


/**
 * @program: WanAndroid
 *
 * @description: 搜索文章 vm 创建者
 *
 * @author: YangRT
 *
 * @create: 2020-02-22 11:36
 **/

class SAViewModelFactory(private val key:String):ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SearchArticleViewModel(key) as T
    }
}