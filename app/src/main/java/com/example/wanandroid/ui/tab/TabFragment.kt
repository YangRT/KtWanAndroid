package com.example.wanandroid.ui.tab

import android.util.Log
import androidx.databinding.ObservableArrayList
import androidx.fragment.app.Fragment
import com.example.wanandroid.R
import com.example.wanandroid.base.BaseFragment
import com.example.wanandroid.base.PageStatus
import com.example.wanandroid.databinding.FragmentTabBinding
import com.example.wanandroid.repository.TabRepository
import com.example.wanandroid.ui.mine.gzh.GzhFragment
import com.example.wanandroid.ui.mine.knowledgeitem.KIFragment
import com.example.wanandroid.ui.project.classic.ProjectArticleFragment


/**
 * @program: WanAndroid
 *
 * @description: tab ui
 *
 * @author: YangRT
 *
 * @create: 2020-02-20 11:09
 **/

class TabFragment(private var type: String):BaseFragment<TabTitleInfo,TabRepository,TabViewModel,FragmentTabBinding>() {

    private lateinit var adapter: TabViewPagerAdapter
    private var fragmentList:ArrayList<Fragment> = ArrayList()
    private var titleList:ArrayList<String> = ArrayList()
    private var idList:ArrayList<Int> = ArrayList()

    override fun getLayoutId(): Int {
        return R.layout.fragment_tab
    }

    override fun viewModel(): TabViewModel {
        if (viewModel == null){
            viewModel =  TabViewModelFactory(type).create(TabViewModel::class.java)
        }
        return viewModel as TabViewModel
    }

    override fun dataInsert(data: ObservableArrayList<TabTitleInfo>) {
        if (type  == "公众号文章"){
            fragmentList.clear()
            titleList.clear()
            createGzhFragment(data)
            binding.tabViewPager.adapter = adapter
            adapter.notifyDataSetChanged()
            binding.tabLayout.setupWithViewPager(binding.tabViewPager)
        }else if (type == "项目分类"){
            fragmentList.clear()
            titleList.clear()
            createProjectArticleFragment(data)
            binding.tabViewPager.adapter = adapter
            adapter.notifyDataSetChanged()
            binding.tabLayout.setupWithViewPager(binding.tabViewPager)
        }

    }

    private fun createGzhFragment(data: ArrayList<TabTitleInfo>){
        for(item in data.iterator()){
            val fragment = GzhFragment(item.id,item.title)
            fragmentList.add(fragment)
            titleList.add(item.title)
        }
    }

    private fun createProjectArticleFragment(data:ArrayList<TabTitleInfo>){
        for(item in data.iterator()){
            val fragment = ProjectArticleFragment(item.id,item.title)
            fragmentList.add(fragment)
            titleList.add(item.title)
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
        adapter = TabViewPagerAdapter(childFragmentManager,fragmentList,titleList)
        if (type == "公众号文章" || type == "项目分类"){
            viewModel().getCacheData()
        }else{
            fragmentList.clear()
            titleList.clear()
            idList.clear()
            titleList.addAll(arguments!!.getStringArrayList("tabTitle")!!)
            idList.addAll(arguments!!.getIntegerArrayList("tabId")!!)
            createKIFragment()
        }
    }

    private fun createKIFragment(){
        Log.e("createKI","createKIFragment:${titleList.size}")
        for(i in 0 until  titleList.size){
            val fragment =  KIFragment(idList[i], titleList[i])
            fragmentList.add(fragment)
        }
        viewModel().status.postValue(PageStatus.SHOW_CONTENT)
        binding.tabViewPager.adapter = adapter
        adapter.notifyDataSetChanged()
        binding.tabLayout.setupWithViewPager(binding.tabViewPager)
    }
}