package com.example.wanandroid.ui.project.classic

import androidx.databinding.ObservableArrayList
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wanandroid.base.BaseArticleAdapter
import com.example.wanandroid.base.BaseArticleModel
import com.example.wanandroid.base.BaseListFragment
import com.example.wanandroid.repository.ProjectArticleRepository


/**
 * @program: WanAndroid
 *
 * @description: 项目分类 文章 ui
 *
 * @author: YangRT
 *
 * @create: 2020-02-21 15:57
 **/

class ProjectArticleFragment(val cid:Int,val key:String):BaseListFragment<BaseArticleModel,ProjectArticleRepository,ProjectArticleViewModel>(){

    override fun viewModel(): ProjectArticleViewModel {
        if(viewModel == null){
           viewModel = ProjectClassicFactory(cid,key).create(ProjectArticleViewModel::class.java)
        }
        return viewModel as ProjectArticleViewModel
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