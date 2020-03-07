package com.example.wanandroid.ui.mine.navigation

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wanandroid.R
import com.example.wanandroid.base.BaseFragment
import com.example.wanandroid.customview.SpaceItemDecoration
import com.example.wanandroid.data.model.NavigationData
import com.example.wanandroid.databinding.FragmentNavigationBinding
import com.example.wanandroid.repository.NavigationRepository


/**
 * @program: WanAndroid
 *
 * @description: 导航 ui
 *
 * @author: YangRT
 *
 * @create: 2020-02-19 22:50
 **/

class NavigationFragment:BaseFragment<NavigationData,NavigationRepository,NavigationViewModel,FragmentNavigationBinding>() {

    private lateinit var adapter: NavigationAdapter

    override fun getLayoutId(): Int {
        return R.layout.fragment_navigation
    }

    override fun viewModel(): NavigationViewModel {
        if(viewModel == null){
            viewModel = ViewModelProviders.of(this).get(NavigationViewModel::class.java)
        }
        return viewModel as NavigationViewModel
    }

    override fun dataInsert(data: ObservableArrayList<NavigationData>) {
        adapter.replaceData(data)
    }

    override fun refreshCancel() {

    }

    override fun isRefreshing(): Boolean {
        return false
    }

    override fun fragmentTag(): String {
        return "navigation"
    }

    override fun init() {
        binding.navigationRecyclerview.layoutManager = LinearLayoutManager(context)
        binding.navigationRecyclerview.addItemDecoration(SpaceItemDecoration(10))
        adapter = NavigationAdapter(R.layout.navigation_item,ArrayList<NavigationData>())
        binding.navigationRecyclerview.adapter = adapter
        adapter.addChildClickViewIds(R.id.navigation_item_recyler_view)
        adapter.setNavigationAdapterListener(object :NavigationAdapter.NavigationAdapterListener{
            override fun onItemClick(title: String, link: String, id: Int) {

            }
        })
        viewModel().getCacheData()
    }
}