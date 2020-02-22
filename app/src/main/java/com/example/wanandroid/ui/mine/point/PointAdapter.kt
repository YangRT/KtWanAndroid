package com.example.wanandroid.ui.mine.point

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.wanandroid.R
import com.example.wanandroid.base.BaseViewModel
import com.example.wanandroid.data.network.api.PointDetail


/**
 * @program: WanAndroid
 *
 * @description: 积分明细 适配器
 *
 * @author: YangRT
 *
 * @create: 2020-02-21 14:49
 **/

class PointAdapter(layoutId:Int,data:MutableList<PointDetail>):BaseQuickAdapter<PointDetail,BaseViewHolder>(layoutId,data),LoadMoreModule {

    override fun convert(helper: BaseViewHolder, item: PointDetail?) {
        item?.let {
            helper.setText(R.id.point_reason,it.reason)
            helper.setText(R.id.point_desc,it.desc)
            helper.setText(R.id.point_count,"${it.coinCount}")
        }
    }
}