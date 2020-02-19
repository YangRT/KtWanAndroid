package com.example.wanandroid.ui.mine.navigation

import com.example.wanandroid.base.BaseViewModel
import com.example.wanandroid.data.model.NavigationData
import com.example.wanandroid.repository.NavigationRepository


/**
 * @program: WanAndroid
 *
 * @description: 导航 vm
 *
 * @author: YangRT
 *
 * @create: 2020-02-19 22:49
 **/

class NavigationViewModel:BaseViewModel<NavigationData,NavigationRepository>() {

    init {
        repository = NavigationRepository()
    }
}