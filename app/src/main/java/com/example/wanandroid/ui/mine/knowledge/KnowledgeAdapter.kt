package com.example.wanandroid.ui.mine.knowledge

import android.view.MotionEvent
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.wanandroid.R
import com.example.wanandroid.data.model.KnowledgeChildren
import com.example.wanandroid.data.model.KnowledgeData
import com.google.android.flexbox.FlexboxLayoutManager


/**
 * @program: WanAndroid
 *
 * @description: 知识体系 适配器
 *
 * @author: YangRT
 *
 * @create: 2020-02-19 21:36
 **/

class KnowledgeAdapter(layoutId:Int,data:MutableList<KnowledgeData>):BaseQuickAdapter<KnowledgeData,BaseViewHolder>(layoutId,data) {

    override fun convert(helper: BaseViewHolder, item: KnowledgeData?) {
        item?.let {
            helper.setText(R.id.knowledge_item_title,it.name);
            val recyclerView = helper.getView(R.id.knowledge_item_recycler) as RecyclerView
            val datas:MutableList<KnowledgeChildren> = ArrayList()
            datas.addAll(it.children)
            val adapter = KnowledgeItemAdapter(
                R.layout.knowledge_flow_item,
                datas
            )
            recyclerView.layoutManager = FlexboxLayoutManager(context)
            recyclerView.adapter = adapter
            recyclerView.setOnTouchListener { v, event ->
                if(event.getAction() == MotionEvent.ACTION_UP){
                    helper.itemView.callOnClick();
                }
                return@setOnTouchListener false
            }
        }

    }
}