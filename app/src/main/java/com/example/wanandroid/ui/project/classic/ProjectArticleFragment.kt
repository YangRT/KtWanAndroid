package com.example.wanandroid.ui.project.classic

import androidx.databinding.ObservableArrayList
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wanandroid.MyApplication.Companion.context
import com.example.wanandroid.base.BaseArticleAdapter
import com.example.wanandroid.base.BaseArticleModel
import com.example.wanandroid.base.BaseLazyFragment
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

class ProjectArticleFragment(val cid:Int,val key:String):BaseLazyFragment<BaseArticleModel,ProjectArticleRepository,ProjectArticleViewModel>(){

    override fun viewModel(): ProjectArticleViewModel {
        if(viewModel == null){
           viewModel = ProjectClassicFactory(cid,key).create(ProjectArticleViewModel::class.java)
        }
        return viewModel as ProjectArticleViewModel
    }

    override fun dataInsert(data: ObservableArrayList<BaseArticleModel>) {
        if (isResumed){
            adapter.setNewData(data)
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