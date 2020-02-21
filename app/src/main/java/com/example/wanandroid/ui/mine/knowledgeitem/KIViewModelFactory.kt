package com.example.wanandroid.ui.mine.knowledgeitem

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


/**
 * @program: WanAndroid
 *
 * @description: viewmodel 创建者
 *
 * @author: YangRT
 *
 * @create: 2020-02-21 15:21
 **/

class KIViewModelFactory(private val id:Int,private val key:String):ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return KIViewModel(id,key) as T
    }
}