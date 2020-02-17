package com.example.wanandroid.customview.search

import android.content.Context
import android.util.AttributeSet
import android.widget.ListView
import androidx.core.widget.ListViewCompat


/**
 * @program: WanAndroid
 *
 * @description: 搜索历史记录
 *
 * @author: YangRT
 *
 * @create: 2020-02-16 16:06
 **/

class SearchHistoryView(context: Context, attributeSet: AttributeSet? = null, defStyleAttr:Int = 0):
    ListView(context,attributeSet,defStyleAttr) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var expandSpec = MeasureSpec.makeMeasureSpec(Int.MAX_VALUE.shr(2),MeasureSpec.AT_MOST)
        super.onMeasure(widthMeasureSpec, expandSpec)
    }
}