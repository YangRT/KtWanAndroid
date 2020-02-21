package com.example.wanandroid.ui.mine.point

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wanandroid.R
import com.example.wanandroid.base.BaseFragment
import com.example.wanandroid.data.network.api.PointDetail
import com.example.wanandroid.databinding.FragmentListBinding
import com.example.wanandroid.repository.PointRepository


/**
 * @program: WanAndroid
 *
 * @description: 积分明细 ui
 *
 * @author: YangRT
 *
 * @create: 2020-02-21 14:53
 **/

class PointFragment:BaseFragment<PointDetail,PointRepository,PointViewModel,FragmentListBinding>() {

    private lateinit var adapter : PointAdapter
    private lateinit var headView:FrameLayout
    private var count:  String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        count = arguments?.getString("count")
        super.onViewCreated(view, savedInstanceState)
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_list
    }

    override fun viewModel(): PointViewModel {
        if(viewModel == null){
            viewModel = ViewModelProviders.of(this).get(PointViewModel::class.java)
        }
        return viewModel as PointViewModel
    }

    override fun dataInsert(data: ObservableArrayList<PointDetail>) {
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
        return "point"
    }

    override fun init() {
        adapter = PointAdapter(R.layout.point_item,ArrayList())
        adapter.loadMoreModule?.setOnLoadMoreListener {
            viewModel().loadNextPage()
        }
        adapter.loadMoreModule?.isEnableLoadMoreIfNotFullPage = false
        headView = LayoutInflater.from(context!!).inflate(R.layout.point_headview,null) as FrameLayout
        val text = headView.findViewById<TextView>(R.id.point_total)
        count?.let {
            text.text = it
        }
        adapter.addHeaderView(headView)
        binding.mainPageRefreshLayout.setOnRefreshListener {
            viewModel().refresh()
        }
        binding.articleRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.articleRecyclerView.addItemDecoration(DividerItemDecoration(
            context, DividerItemDecoration.VERTICAL))
        binding.articleRecyclerView.adapter = adapter
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