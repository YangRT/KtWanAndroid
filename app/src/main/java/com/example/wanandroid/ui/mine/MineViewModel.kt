package com.example.wanandroid.ui.mine

import com.example.wanandroid.base.BaseViewModel
import com.example.wanandroid.data.model.MineData
import com.example.wanandroid.repository.MineRepository


/**
 * @program: WanAndroid
 *
 * @description: 我的 vm
 *
 * @author: YangRT
 *
 * @create: 2020-02-19 16:19
 **/

class MineViewModel:BaseViewModel<MineData,MineRepository>() {

    init {
        repository = MineRepository()
    }
}