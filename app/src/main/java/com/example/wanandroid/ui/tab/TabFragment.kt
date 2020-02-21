package com.example.wanandroid.ui.tab

import android.os.Bundle
import android.view.View
import androidx.databinding.ObservableArrayList
import androidx.fragment.app.Fragment
import com.example.wanandroid.R
import com.example.wanandroid.base.BaseFragment
import com.example.wanandroid.databinding.FragmentTabBinding
import com.example.wanandroid.repository.TabRepository


/**
 * @program: WanAndroid
 *
 * @description: tab ui
 *
 * @author: YangRT
 *
 * @create: 2020-02-20 11:09
 **/

class TabFragment:BaseFragment<TabTitleInfo,TabRepository,TabViewModel,FragmentTabBinding>() {

    private var type:String? = null
    private var titleList :ArrayList<String> = ArrayList()
    private var idList:ArrayList<Int> = ArrayList()
    private var fragmentList:ArrayList<Fragment> = ArrayList()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.let {
            type = it.getString("type")
        }
        super.onViewCreated(view, savedInstanceState)
    }
    override fun getLayoutId(): Int {
        return R.layout.fragment_tab
    }

    override fun viewModel(): TabViewModel {
        if (viewModel == null){
            viewModel = type?.let { TabViewModelFactory(it).create(TabViewModel::class.java) }
        }
        return viewModel as TabViewModel
    }

    override fun dataInsert(data: ObservableArrayList<TabTitleInfo>) {
        fragmentList.clear()
        titleList.clear()
        idList.clear()
        for(item in data.iterator()){
            titleList.add(item.title)
            idList.add(item.id)
        }
    }

    override fun refreshCancel() {

    }

    override fun isRefreshing(): Boolean {
        return false
    }

    override fun fragmentTag(): String {
        return "tab"
    }

    override fun init() {


        if (type == "公众号文章" || type == "项目分类"){
            viewModel().getCacheData()
        }
    }
}