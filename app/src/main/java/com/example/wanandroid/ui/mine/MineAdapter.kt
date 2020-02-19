package com.example.wanandroid.ui.mine

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.wanandroid.R


/**
 * @program: WanAndroid
 *
 * @description: 选项列表适配器
 *
 * @author: YangRT
 *
 * @create: 2020-02-19 14:59
 **/

class MineAdapter(layoutId:Int,data:MutableList<MineItemInfo>): BaseQuickAdapter<MineItemInfo, BaseViewHolder>(layoutId,data) {

    override fun convert(helper: BaseViewHolder, item: MineItemInfo?) {
        item?.let {
            helper.setText(R.id.mine_item_title,it.title)
            helper.setImageResource(R.id.mine_item_icon,it.image)
        }
    }
}