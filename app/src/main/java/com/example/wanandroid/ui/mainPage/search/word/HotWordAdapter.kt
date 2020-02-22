package com.example.wanandroid.ui.mainPage.search.word

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.wanandroid.R
import com.example.wanandroid.data.model.HotWord


/**
 * @program: WanAndroid
 *
 * @description: 搜索热词 适配器
 *
 * @author: YangRT
 *
 * @create: 2020-02-22 11:21
 **/

class HotWordAdapter(data:MutableList<HotWord>):BaseQuickAdapter<HotWord,BaseViewHolder>(R.layout.navigation_item_tv,data) {

    override fun convert(helper: BaseViewHolder, item: HotWord?) {
        item?.let {
            helper.setText(R.id.navigation_item_tv,it.name)
        }
    }
}