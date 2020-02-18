package com.example.wanandroid.customview

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import java.util.jar.Attributes


/**
 * @program: WanAndroid
 *
 * @description: 利用贝塞尔曲线画弧线
 *
 * @author: YangRT
 *
 * @create: 2020-02-14 15:49
 **/

class ArcView @JvmOverloads constructor(context: Context,attributeSet: AttributeSet? = null,defStyleAttr:Int = 0): View(context,attributeSet,defStyleAttr){

    private var mPaint:Paint = Paint()
    private var mContext = context
    private var centerX:Int = 0
    private var centerY:Int = 0
    private var path = Path()

    //起点
    private var start :PointF = PointF(0f, 0f)
    //终点
    private var end :PointF = PointF(0f, 0f)
    //控制点
    private var control :PointF = PointF(0f, 0f)

    init {
        mPaint.color = Color.parseColor("#03A9F4")
        mPaint.style = Paint.Style.FILL
        mPaint.isAntiAlias = true
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        centerX = measuredWidth / 2
        centerY = measuredHeight
        start.x =0f
        start.y = measuredHeight*1.0f/4*3;
        end.x = measuredWidth*1.0f
        end.y = start.y
        control.x = centerX * 1.0f
        control.y = centerY * 1.0f


    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        path.reset()
        //画出贝塞尔曲线
        path.moveTo(start.x,start.y)
        path.quadTo(control.x,control.y,end.x,end.y)
        path.lineTo(end.x, 0f)
        path.lineTo(0f, 0f)
        path.close()

        canvas?.drawPath(path, mPaint)

    }

}