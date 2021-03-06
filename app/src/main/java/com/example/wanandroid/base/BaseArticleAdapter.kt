package com.example.wanandroid.base

import android.content.Context
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.wanandroid.R


/**
 * @program: WanAndroid
 *
 * @description: 文章列表通用适配器
 *
 * @author: YangRT
 *
 * @create: 2020-02-16 11:41
 **/

class BaseArticleAdapter(data:MutableList<BaseArticleModel>): BaseMultiItemQuickAdapter<BaseArticleModel, BaseViewHolder> (data),LoadMoreModule{


   init {
       addItemType(BaseArticleModel.NORMAL, R.layout.article_item)
       addItemType(BaseArticleModel.PROJECT,R.layout.article_item_project)
   }


    override fun convert(helper: BaseViewHolder, item: BaseArticleModel?) {
        item?.let {
            if(helper.itemViewType == BaseArticleModel.PROJECT){
                Glide.with(context).load(it.imagePath).into(helper.itemView.findViewById(R.id.main_page_recyclerview_item_image))
                helper.setText(R.id.main_page_recyclerview_item_desc,it.description);
            }
            helper
                .setText(R.id.todo_item_title,it.title)
                .setText(R.id.todo_item_time,it.time)
                .setText(R.id.main_page_recyclerview_item_author,it.author)
                .setText(R.id.todo_item_type,it.classic)
            if(it.isCollect){
                helper.setImageResource(R.id.main_page_recyclerview_item_collect,R.drawable.like);
            }else{
                helper.setImageResource(R.id.main_page_recyclerview_item_collect,R.drawable.unlike);
            }
        }
    }
}