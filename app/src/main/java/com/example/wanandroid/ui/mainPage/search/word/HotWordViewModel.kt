package com.example.wanandroid.ui.mainPage.search.word

import com.example.wanandroid.base.BaseViewModel
import com.example.wanandroid.data.model.HotWord
import com.example.wanandroid.repository.HotWordRepository


/**
 * @program: WanAndroid
 *
 * @description: 搜索热词 vm
 *
 * @author: YangRT
 *
 * @create: 2020-02-22 11:18
 **/

class HotWordViewModel:BaseViewModel<HotWord,HotWordRepository>() {

    init {
        repository = HotWordRepository()
    }

}