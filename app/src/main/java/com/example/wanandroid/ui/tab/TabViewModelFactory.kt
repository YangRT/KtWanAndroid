package com.example.wanandroid.ui.tab

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

class TabViewModelFactory(private var key: String): ViewModelProvider.NewInstanceFactory() {

    fun createViewModel():TabViewModel{
        return TabViewModel(key)
    }

}