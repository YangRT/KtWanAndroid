package com.example.wanandroid.ui.tab

import com.example.wanandroid.base.BaseViewModel
import com.example.wanandroid.repository.TabRepository


/**
 * @program: WanAndroid
 *
 * @description: tab vm
 *
 * @author: YangRT
 *
 * @create: 2020-02-20 11:07
 **/

class TabViewModel(key:String):BaseViewModel<TabTitleInfo,TabRepository>() {

    init {
        repository = TabRepository(key)
    }

}