package com.example.wanandroid.ui.mine.knowledge

import com.example.wanandroid.base.BaseViewModel
import com.example.wanandroid.data.model.KnowledgeData
import com.example.wanandroid.repository.KnowledgeRepository


/**
 * @program: WanAndroid
 *
 * @description: 知识体系 vm
 *
 * @author: YangRT
 *
 * @create: 2020-02-19 21:28
 **/

class KnowledgeViewModel:BaseViewModel<KnowledgeData,KnowledgeRepository>() {

    init {
        repository = KnowledgeRepository()
    }


}