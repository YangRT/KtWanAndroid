package com.example.wanandroid.ui.mine.todo

import java.io.Serializable


/**
 * @program: WanAndroid
 *
 * @description: 事件bean
 *
 * @author: YangRT
 *
 * @create: 2020-03-07 10:26
 **/

class Event: Serializable {

    lateinit var title:String
    lateinit var content:String
    lateinit var date:String
    var type = -1
}