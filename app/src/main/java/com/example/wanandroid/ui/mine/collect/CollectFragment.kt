package com.example.wanandroid.ui.mine.collect

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wanandroid.base.BaseArticleAdapter
import com.example.wanandroid.base.BaseArticleModel
import com.example.wanandroid.base.BaseListFragment
import com.example.wanandroid.repository.CollectRepository


/**
 * @program: WanAndroid
 *
 * @description: 收藏文章 ui
 *
 * @author: YangRT
 *
 * @create: 2020-02-21 17:38
 **/

class CollectFragment:BaseListFragment<BaseArticleModel,CollectRepository,CollectViewModel>() {

    override fun viewModel(): CollectViewModel {
        if (viewModel == null){
            viewModel = ViewModelProviders.of(this).get(CollectViewModel::class.java)
        }
        return viewModel as CollectViewModel
    }

    override fun dataInsert(data: ObservableArrayList<BaseArticleModel>) {
        adapter.setNewData(data)
    }

    override fun fragmentTag(): String {
        return "collect"
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
        adapter.setOnItemClickListener { adapter, view, position ->

        }
        adapter.loadMoreModule?.isEnableLoadMoreIfNotFullPage = false
        binding.articleRecyclerView.adapter = adapter
        binding.articleRecyclerView.addItemDecoration( DividerItemDecoration(
            context, DividerItemDecoration.VERTICAL)
        )
        viewModel().getCacheData()
    }
}