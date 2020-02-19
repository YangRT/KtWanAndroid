package com.example.wanandroid.customview

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView


/**
 * @program: WanAndroid
 *
 * @description: RecyclerView item间隔
 *
 * @author: YangRT
 *
 * @create: 2020-02-19 21:49
 **/

class SpaceItemDecoration(space:Int):RecyclerView.ItemDecoration() {

    private var space = space

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.top = space
    }

}