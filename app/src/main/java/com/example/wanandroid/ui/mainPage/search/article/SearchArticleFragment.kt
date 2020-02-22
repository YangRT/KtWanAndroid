package com.example.wanandroid.ui.mainPage.search.article

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wanandroid.R
import com.example.wanandroid.base.BaseArticleAdapter
import com.example.wanandroid.base.BaseArticleModel
import com.example.wanandroid.base.BaseListFragment
import com.example.wanandroid.repository.SearchArticleRepository


/**
 * @program: WanAndroid
 *
 * @description: 搜索文章列表 ui
 *
 * @author: YangRT
 *
 * @create: 2020-02-22 11:37
 **/

class SearchArticleFragment(private val key:String):BaseListFragment<BaseArticleModel,SearchArticleRepository,SearchArticleViewModel>() {

    override fun viewModel(): SearchArticleViewModel {
        if(viewModel == null){
            viewModel = SAViewModelFactory(key).create(SearchArticleViewModel::class.java)
        }
        return viewModel as SearchArticleViewModel
    }

    override fun dataInsert(data: ObservableArrayList<BaseArticleModel>) {
        adapter.setNewData(data)
    }

    override fun fragmentTag(): String {
        return key
    }

    override fun init() {
        adapter = BaseArticleAdapter(ArrayList())
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
        adapter.loadMoreModule?.setOnLoadMoreListener {
            viewModel().loadNextPage()
        }
        adapter.setOnItemClickListener { adapter, view, position ->

        }
        adapter.loadMoreModule?.isEnableLoadMoreIfNotFullPage = false
        binding.articleRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.mainPageRefreshLayout.setOnRefreshListener {
            viewModel().refresh()
        }
        binding.articleRecyclerView.adapter = adapter
        binding.articleRecyclerView.addItemDecoration( DividerItemDecoration(getContext(),
            DividerItemDecoration.VERTICAL)
        )
        viewModel().getCacheData()
    }
}