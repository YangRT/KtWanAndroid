package com.example.wanandroid.ui.square

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ObservableArrayList
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wanandroid.R
import com.example.wanandroid.base.BaseArticleAdapter
import com.example.wanandroid.base.BaseArticleModel
import com.example.wanandroid.base.BaseFragment
import com.example.wanandroid.databinding.FragmentListBinding
import com.example.wanandroid.databinding.FragmentProjectBinding
import com.example.wanandroid.repository.ProjectRepository
import com.example.wanandroid.repository.SqureRepository
import com.example.wanandroid.ui.project.ProjectViewModel


/**
 * @program: WanAndroid
 *
 * @description: 广场 ui
 *
 * @author: YangRT
 *
 * @create: 2020-02-18 22:12
 **/

class SquareFragment: BaseFragment<BaseArticleModel,SqureRepository, SquareViewModel, FragmentListBinding>(){
    private lateinit var adapter: BaseArticleAdapter
    private  var list: MutableList<BaseArticleModel> = ArrayList()

    override fun getLayoutId(): Int {
        return R.layout.fragment_list
    }

    override fun viewModel(): SquareViewModel {
        if(viewModel == null){
            viewModel = ViewModelProviders.of(this).get(SquareViewModel::class.java)
        }
        return viewModel as SquareViewModel;
    }

    override fun dataInsert(data: ObservableArrayList<BaseArticleModel>) {
        adapter.setNewData(data)
    }

    override fun refreshCancel() {
        if (binding.mainPageRefreshLayout.isRefreshing){
            binding.mainPageRefreshLayout.isRefreshing = false
        }
    }

    override fun isRefreshing(): Boolean {
        return binding.mainPageRefreshLayout.isRefreshing
    }

    override fun fragmentTag(): String {
        return "square"
    }

    override fun init() {
        binding.articleRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.mainPageRefreshLayout.setOnRefreshListener {
            viewModel().loadNextPage()
        }
        adapter = BaseArticleAdapter(list)
        adapter.loadMoreModule?.setOnLoadMoreListener {
            viewModel().loadNextPage()
        }
        adapter.loadMoreModule?.isEnableLoadMoreIfNotFullPage = false
        binding.articleRecyclerView.adapter = adapter
        binding.articleRecyclerView.addItemDecoration( DividerItemDecoration(getContext(),
            DividerItemDecoration.VERTICAL)
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