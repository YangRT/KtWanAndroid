package com.example.wanandroid.ui.mine.gzh

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wanandroid.R
import com.example.wanandroid.base.BaseArticleAdapter
import com.example.wanandroid.base.BaseArticleModel
import com.example.wanandroid.base.BaseLazyFragment
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

class GzhFragment(val cid:Int,val key:String):BaseLazyFragment<BaseArticleModel,GzhRepository,GzhViewModel>() {

    override fun viewModel(): GzhViewModel {
        if (viewModel == null){
            viewModel = GzhViewModelFactory(cid, key).create(GzhViewModel::class.java)
        }
        return viewModel as GzhViewModel
    }

    override fun dataInsert(data: ObservableArrayList<BaseArticleModel>) {
        if (isResumed){
            adapter.replaceData(data)
        }
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
        adapter.setOnItemClickListener { adapter, view, position ->

        }
        viewModel().collectResponse.observe(this, Observer {
            if (it.type == 0){
                val article = adapter.data[it.position]
                article.isCollect = true
                adapter.setData(it.position,article)
            }else if (it.type == 1){
                val article = adapter.data[it.position]
                article.isCollect = false
                adapter.setData(it.position,article)
            }
        })
        adapter.addChildClickViewIds(R.id.main_page_recyclerview_item_collect)
        adapter.setOnItemChildClickListener { adapter, view, position ->
            val article = adapter.data[position] as BaseArticleModel
            if(article.isCollect){
                viewModel().unCollect(article.id,position)
            } else{
                viewModel().addCollect(article.id,position)
            }
        }
        adapter.loadMoreModule?.isEnableLoadMoreIfNotFullPage = false
        binding.articleRecyclerView.adapter = adapter
        binding.articleRecyclerView.addItemDecoration( DividerItemDecoration(
            context, DividerItemDecoration.VERTICAL)
        )
    }

    override fun onFragmentResume() {
        super.onFragmentResume()
        viewModel().getCacheData()
    }
}