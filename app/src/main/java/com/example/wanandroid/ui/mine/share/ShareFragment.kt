package com.example.wanandroid.ui.mine.share

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wanandroid.base.BaseArticleAdapter
import com.example.wanandroid.base.BaseArticleModel
import com.example.wanandroid.base.BaseListFragment
import com.example.wanandroid.repository.ShareRepository


/**
 * @program: WanAndroid
 *
 * @description: 分享文章列表 ui
 *
 * @author: YangRT
 *
 * @create: 2020-02-21 17:42
 **/

class ShareFragment:BaseListFragment<BaseArticleModel,ShareRepository,ShareViewModel>() {

    override fun viewModel(): ShareViewModel {
        if(viewModel == null){
            viewModel = ViewModelProviders.of(this).get(ShareViewModel::class.java)
        }
        return viewModel as ShareViewModel
    }

    override fun dataInsert(data: ObservableArrayList<BaseArticleModel>) {
        adapter.setNewData(data)
    }

    override fun fragmentTag(): String {
        return "share"
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