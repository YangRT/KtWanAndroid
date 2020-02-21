package com.example.wanandroid.ui.mine.knowledgeitem

import android.os.Bundle
import android.view.View
import androidx.databinding.ObservableArrayList
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wanandroid.base.BaseArticleAdapter
import com.example.wanandroid.base.BaseArticleModel
import com.example.wanandroid.base.BaseListFragment
import com.example.wanandroid.repository.KIRepository


/**
 * @program: WanAndroid
 *
 * @description: 知识体系 item ui
 *
 * @author: YangRT
 *
 * @create: 2020-02-21 15:19
 **/

class KIFragment(val cid:Int,val key:String):BaseListFragment<BaseArticleModel,KIRepository,KIViewModel>() {

    override fun viewModel(): KIViewModel {
        if (viewModel == null){
            viewModel = KIViewModelFactory(cid,key).create(KIViewModel::class.java)
        }
        return viewModel as KIViewModel
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