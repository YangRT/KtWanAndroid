package com.example.wanandroid.base

import android.content.Context
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder


/**
 * @program: WanAndroid
 *
 * @description: 文章列表通用适配器
 *
 * @author: YangRT
 *
 * @create: 2020-02-16 11:41
 **/

class BaseArticleAdapter(data:MutableList<BaseArticleModel>): BaseMultiItemQuickAdapter<BaseArticleModel, BaseViewHolder> (data){


   init {

   }


    override fun convert(helper: BaseViewHolder, item: BaseArticleModel?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}