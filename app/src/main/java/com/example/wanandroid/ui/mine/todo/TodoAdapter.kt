package com.example.wanandroid.ui.mine.todo

import android.view.View
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.wanandroid.R
import com.example.wanandroid.data.model.TodoEvent


/**
 * @program: WanAndroid
 *
 * @description: todo列表适配器
 *
 * @author: YangRT
 *
 * @create: 2020-02-21 20:10
 **/

class TodoAdapter(data:MutableList<TodoEvent>):BaseQuickAdapter<TodoEvent,BaseViewHolder>(R.layout.todo_item,data) {

    override fun convert(helper: BaseViewHolder, item: TodoEvent?) {
        item?.let {
            helper.setText(R.id.todo_item_title,it.title)
            helper.setText(R.id.todo_item_time,"预计完成时间:${it.dateStr}")
            when(it.type){
                Type.LIFE -> helper.setText(R.id.todo_item_type,"生活")
                Type.WORK -> helper.setText(R.id.todo_item_type,"工作")
                Type.LEARN -> helper.setText(R.id.todo_item_type,"学习")
                Type.OTHER -> helper.setText(R.id.todo_item_type,"其他")
            }
            if (it.status == 1){
                helper.setText(R.id.todo_item_finish,"")
                helper.getView<TextView>(R.id.todo_item_finish).visibility = View.GONE
                helper.setText(R.id.todo_item_finish_time,"完成时间:"+it.completeDateStr)
            }else{
                helper.setText(R.id.todo_item_finish_time,"")
            }
            it
        }

    }
}