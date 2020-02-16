package com.example.wanandroid.customview

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView


/**
 * @program: WanAndroid
 *
 * @description: 文字滚动
 *
 * @author: YangRT
 *
 * @create: 2020-02-16 14:54
 **/

class ScrollTextView(context: Context,attributeSet: AttributeSet? = null,defStyleAttr:Int = 0):TextView(context,attributeSet,defStyleAttr){

    override fun isFocused(): Boolean {
        return true
    }
}