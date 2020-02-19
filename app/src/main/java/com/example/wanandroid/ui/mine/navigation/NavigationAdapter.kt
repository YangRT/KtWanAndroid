package com.example.wanandroid.ui.mine.navigation

import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.wanandroid.R
import com.example.wanandroid.data.model.Navigation
import com.example.wanandroid.data.model.NavigationData
import com.example.wanandroid.data.model.NavigationInfo
import com.google.android.flexbox.FlexboxLayoutManager


/**
 * @program: WanAndroid
 *
 * @description: 导航 适配器
 *
 * @author: YangRT
 *
 * @create: 2020-02-19 22:53
 **/

class NavigationAdapter(layoutId:Int,data:MutableList<NavigationData>):BaseQuickAdapter<NavigationData,BaseViewHolder>(layoutId,data) {

    private var mListener:NavigationAdapterListener? = null

    interface NavigationAdapterListener{
        fun onItemClick(title:String,link:String,id:Int)
    }

    fun setNavigationAdapterListener(listener: NavigationAdapterListener){
        mListener = listener
    }
    override fun convert(helper: BaseViewHolder, item: NavigationData?) {
        item?.let {
            helper.setText(R.id.navigation_item_title,it.name);
            val recyclerView = helper.getView(R.id.navigation_item_recyler_view) as RecyclerView
            val list:MutableList<Navigation> = ArrayList()
            list.addAll(it.articles)
            val adapter = NavigationItemAdapter(R.layout.navigation_item_tv,list)
            adapter.setClickListener(object :NavigationItemAdapter.NavigationItemClickListener{
                override fun onCLick(title: String, link: String, id: Int) {
                    mListener?.onItemClick(title,link, id)
                }
            })
            recyclerView.setLayoutManager(FlexboxLayoutManager(context));
            recyclerView.setAdapter(adapter);
            helper
        }
        }

}