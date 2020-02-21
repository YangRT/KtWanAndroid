package com.example.wanandroid.ui.mine.gzh

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


/**
 * @program: WanAndroid
 *
 * @description: 公众号 viewModel 建造者
 *
 * @author: YangRT
 *
 * @create: 2020-02-21 16:55
 **/

class GzhViewModelFactory(val cid:Int,val key:String):ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return GzhViewModel(cid,key) as T
    }
}