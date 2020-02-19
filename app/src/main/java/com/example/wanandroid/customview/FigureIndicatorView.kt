package com.example.wanandroid.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import com.zhpan.bannerview.indicator.BaseIndicatorView


/**
 * @program: WanAndroid
 *
 * @description: 自定义指示器
 *
 * @author: YangRT
 *
 * @create: 2020-02-19 14:19
 **/

class FigureIndicatorView constructor(context: Context, attributeSet: AttributeSet? = null, defStyleAttr:Int = 0):BaseIndicatorView(context,attributeSet,defStyleAttr) {

    private  var radius = 20

    private var textColor = Color.WHITE;

    private var textSize= 20f



    init {
        mPaint = Paint()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(2 * radius, 2 * radius)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        mPaint.color = textColor
        mPaint.textSize = textSize
        val num =  currentPosition + 1
        val text =  "$num/$pageSize"
        var textWidth =  mPaint.measureText(text)
        var fontMetricsInt = mPaint.fontMetricsInt
        var baseline = ((measuredHeight - fontMetricsInt.bottom + fontMetricsInt.top) / 2 - fontMetricsInt.top)*1.0f
        canvas?.drawText(text, (getWidth() - textWidth) / 2, baseline, mPaint);
    }
}