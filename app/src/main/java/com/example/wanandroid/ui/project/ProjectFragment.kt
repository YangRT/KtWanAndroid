package com.example.wanandroid.ui.project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ObservableArrayList
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wanandroid.MyApplication.Companion.context
import com.example.wanandroid.R
import com.example.wanandroid.base.BaseArticleAdapter
import com.example.wanandroid.base.BaseArticleModel
import com.example.wanandroid.base.BaseFragment
import com.example.wanandroid.base.BaseListFragment
import com.example.wanandroid.databinding.FragmentListBinding
import com.example.wanandroid.databinding.FragmentProjectBinding
import com.example.wanandroid.repository.MainPageRepository
import com.example.wanandroid.repository.ProjectRepository
import com.example.wanandroid.ui.mainPage.MainPageViewModel
import java.security.AccessController.getContext


/**
 * @program: WanAndroid
 *
 * @description: 项目 ui
 *
 * @author: YangRT
 *
 * @create: 2020-02-18 22:13
 **/

class ProjectFragment: BaseListFragment<BaseArticleModel, ProjectRepository, ProjectViewModel>(){

    private  var list: MutableList<BaseArticleModel> = ArrayList()

    override fun viewModel(): ProjectViewModel {
        if(viewModel == null){
            viewModel = ViewModelProviders.of(this).get(ProjectViewModel::class.java)
        }
        return viewModel as ProjectViewModel
    }

    override fun dataInsert(data: ObservableArrayList<BaseArticleModel>) {
        adapter.setNewData(data)
    }


    override fun fragmentTag(): String {
        return "Project"
    }

    override fun init() {
        binding.articleRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.mainPageRefreshLayout.setOnRefreshListener {
            viewModel().refresh()
        }
        adapter = BaseArticleAdapter(list)
        adapter.loadMoreModule?.setOnLoadMoreListener {
            viewModel().loadNextPage()
        }
        adapter.loadMoreModule?.isEnableLoadMoreIfNotFullPage = false
        binding.articleRecyclerView.adapter = adapter
        binding.articleRecyclerView.addItemDecoration( DividerItemDecoration(getContext(),
            DividerItemDecoration.VERTICAL))
        viewModel().getCacheData()
    }

}