package com.example.wanandroid.ui.mine

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.wanandroid.R


/**
 * @program: WanAndroid
 *
 * @description: "我的" ui
 *
 * @author: YangRT
 *
 * @create: 2020-02-16 15:06
 **/

class MineFragment :Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_mine,container,false)
    }
}