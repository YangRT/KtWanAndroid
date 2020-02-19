package com.example.wanandroid.ui.mine.rank

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.wanandroid.R
import com.example.wanandroid.data.model.RankItem
import kotlinx.android.synthetic.main.rank_item.view.*


/**
 * @program: WanAndroid
 *
 * @description: 排行榜 适配器
 *
 * @author: YangRT
 *
 * @create: 2020-02-19 17:44
 **/

class RankAdapter(layoutId:Int,data:MutableList<RankItem>):BaseQuickAdapter<RankItem,BaseViewHolder>(layoutId,data),LoadMoreModule {
    override fun convert(helper: BaseViewHolder, item: RankItem?) {
        item?.let {
            helper.setText(R.id.rank_num,it.rank)
            helper.setText(R.id.rank_username,it.username)
            helper.setText(R.id.rank_point,it.coinCount)
        }
    }
}