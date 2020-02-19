package com.example.wanandroid.ui.mine.navigation

import android.view.View
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.wanandroid.R
import com.example.wanandroid.data.model.Navigation
import kotlinx.android.synthetic.main.navigation_item.view.*


/**
 * @program: WanAndroid
 *
 * @description: 导航 item 适配器
 *
 * @author: YangRT
 *
 * @create: 2020-02-19 22:57
 **/

class NavigationItemAdapter(layoutId:Int, data:MutableList<Navigation>):BaseQuickAdapter<Navigation,BaseViewHolder>(layoutId,data) {

    private var mListener:NavigationItemClickListener? = null

    interface NavigationItemClickListener{
        fun onCLick(title:String,link:String,id:Int)
    }

    fun setClickListener(listener: NavigationItemClickListener){
        mListener = listener
    }

    override fun convert(helper: BaseViewHolder, item: Navigation?) {
        item?.let {
            helper.setText(R.id.navigation_item_tv,it.title)
            helper.getView<TextView>(R.id.navigation_item_tv).setOnClickListener {
                mListener?.onCLick(item.title,item.link,item.id)
            }

        }

    }
}