package com.example.wanandroid.ui.mainPage.banner

import com.example.wanandroid.base.BaseViewModel
import com.example.wanandroid.repository.BannerRepository


/**
 * @program: WanAndroid
 *
 * @description: banner vm
 *
 * @author: YangRT
 *
 * @create: 2020-02-19 11:47
 **/

class BannerViewModel:BaseViewModel<BannerModel,BannerRepository>() {

    init {
        repository = BannerRepository()
    }

}