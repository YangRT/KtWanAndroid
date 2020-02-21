package com.example.wanandroid.ui.mine.rank

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wanandroid.R
import com.example.wanandroid.base.BaseFragment
import com.example.wanandroid.data.model.RankItem
import com.example.wanandroid.databinding.FragmentListBinding
import com.example.wanandroid.repository.RankRepository


/**
 * @program: WanAndroid
 *
 * @description: 排行榜 ui
 *
 * @author: YangRT
 *
 * @create: 2020-02-19 17:38
 **/

class RankFragment:BaseFragment<RankItem,RankRepository,RankViewModel,FragmentListBinding>() {

    private lateinit var adapter: RankAdapter
    private var list:MutableList<RankItem> = ArrayList()

    override fun getLayoutId(): Int {
        return R.layout.fragment_list
    }

    override fun viewModel(): RankViewModel {
        if(viewModel == null){
            viewModel = ViewModelProviders.of(this).get(RankViewModel::class.java)
        }
        return viewModel as RankViewModel
    }

    override fun dataInsert(data: ObservableArrayList<RankItem>) {
        adapter.setNewData(data)
    }

    override fun refreshCancel() {
        if(binding.mainPageRefreshLayout.isRefreshing){
            binding.mainPageRefreshLayout.isRefreshing = false
        }
    }

    override fun isRefreshing(): Boolean {
        return binding.mainPageRefreshLayout.isRefreshing
    }

    override fun fragmentTag(): String {
        return "rank"
    }

    override fun init() {
        binding.articleRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.mainPageRefreshLayout.setOnRefreshListener {
            viewModel().refresh()
        }
        adapter = RankAdapter(R.layout.rank_item,list)
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

    override fun loadMoreEmpty() {
        if(adapter.loadMoreModule?.isLoading!!){
            adapter.loadMoreModule?.loadMoreEnd()
        }
    }

    override fun loadMoreFailed() {
        if(adapter.loadMoreModule?.isLoading!!){
            adapter.loadMoreModule?.loadMoreFail()
        }
    }

    override fun loadMoreFinished() {
        if(adapter.loadMoreModule?.isLoading!!){
            adapter.loadMoreModule?.loadMoreComplete()
        }
    }
}