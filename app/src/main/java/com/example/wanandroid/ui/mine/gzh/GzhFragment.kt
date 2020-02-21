package com.example.wanandroid.ui.mine.gzh

import androidx.databinding.ObservableArrayList
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wanandroid.base.BaseArticleAdapter
import com.example.wanandroid.base.BaseArticleModel
import com.example.wanandroid.base.BaseListFragment
import com.example.wanandroid.repository.GzhRepository


/**
 * @program: WanAndroid
 *
 * @description: 公众号文章 ui
 *
 * @author: YangRT
 *
 * @create: 2020-02-21 16:53
 **/

class GzhFragment(val cid:Int,val key:String):BaseListFragment<BaseArticleModel,GzhRepository,GzhViewModel>() {

    override fun viewModel(): GzhViewModel {
        if (viewModel == null){
            viewModel = GzhViewModelFactory(cid, key).create(GzhViewModel::class.java)
        }
        return viewModel as GzhViewModel
    }

    override fun dataInsert(data: ObservableArrayList<BaseArticleModel>) {
        adapter.setNewData(data)
    }

    override fun fragmentTag(): String {
        return key
    }

    override fun init() {
        adapter = BaseArticleAdapter(ArrayList())
        binding.articleRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.mainPageRefreshLayout.setOnRefreshListener {
            viewModel().refresh()
        }
        adapter.loadMoreModule?.setOnLoadMoreListener {
            viewModel().loadNextPage()
        }
        adapter.loadMoreModule?.isEnableLoadMoreIfNotFullPage = false
        binding.articleRecyclerView.adapter = adapter
        binding.articleRecyclerView.addItemDecoration( DividerItemDecoration(
            context, DividerItemDecoration.VERTICAL)
        )
        viewModel().getCacheData()
    }
}