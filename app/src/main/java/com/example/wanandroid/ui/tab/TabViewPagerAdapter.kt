package com.example.wanandroid.ui.tab

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter


/**
 * @program: WanAndroid
 *
 * @description: tablayout与 viewPager 适配器
 *
 * @author: YangRT
 *
 * @create: 2020-02-21 22:23
 **/

class TabViewPagerAdapter(fm: FragmentManager,val item:List<Fragment>,private val titleList:List<String>): FragmentStatePagerAdapter(fm,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment {
        return item.get(position)
    }

    override fun getCount(): Int {
        return item.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titleList.get(position)
    }
}