package com.example.wanandroid.ui.tab

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


/**
 * @program: WanAndroid
 *
 * @description: tab vm 建造者
 *
 * @author: YangRT
 *
 * @create: 2020-02-20 11:35
 **/

class TabViewModelFactory(private var key: String): ViewModelProvider.Factory {



    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TabViewModel(key) as T
    }


}