package com.example.wanandroid.ui.mainPage.banner

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.wanandroid.R
import com.zhpan.bannerview.holder.ViewHolder


/**
 * @program: WanAndroid
 *
 * @description: 主页Banner适配器
 *
 * @author: YangRT
 *
 * @create: 2020-02-16 15:24
 **/

class BannerAdapter :ViewHolder<BannerModel>{
    override fun getLayoutId(): Int {
        return R.layout.banner_item
    }

    override fun onBind(itemView: View?, data: BannerModel?, position: Int, size: Int) {
        val imageView = itemView?.findViewById(R.id.banner_image) as ImageView
        val textView = itemView?.findViewById(R.id.banner_title) as TextView
        data?.let {
            Glide.with(itemView.context).load(it.imagePath).into(imageView)
            textView.text = it.title
        }

    }
}