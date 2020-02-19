package com.example.wanandroid.ui.mine.knowledge

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.wanandroid.R
import com.example.wanandroid.data.model.KnowledgeChildren


/**
 * @program: WanAndroid
 *
 * @description: 知识体系 item 适配器
 *
 * @author: YangRT
 *
 * @create: 2020-02-19 21:42
 **/

class KnowledgeItemAdapter(layoutId:Int, data:MutableList<KnowledgeChildren>):BaseQuickAdapter<KnowledgeChildren,BaseViewHolder>(layoutId,data) {

    override fun convert(helper: BaseViewHolder, item: KnowledgeChildren?) {
        item?.let {
            helper.setText(R.id.tv_tag,it.name)
        }
    }
}