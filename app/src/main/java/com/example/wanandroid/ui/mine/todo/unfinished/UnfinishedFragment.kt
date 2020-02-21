package com.example.wanandroid.ui.mine.todo.unfinished

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.alguojian.mylibrary.StatusLayout
import com.example.wanandroid.R
import com.example.wanandroid.base.BaseFragment
import com.example.wanandroid.base.BaseStatusAdapter
import com.example.wanandroid.data.model.TodoEvent
import com.example.wanandroid.databinding.FragmentTodoBinding
import com.example.wanandroid.repository.TodoRepository
import com.example.wanandroid.ui.mine.todo.TodoAdapter
import com.example.wanandroid.ui.mine.todo.TodoDetailActivity


/**
 * @program: WanAndroid
 *
 * @description: todo列表 ui
 *
 * @author: YangRT
 *
 * @create: 2020-02-21 19:57
 **/

class UnfinishedFragment:BaseFragment<TodoEvent,TodoRepository,UnfinishedViewModel,FragmentTodoBinding>() {

    private lateinit var adapter: TodoAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,getLayoutId(),container,false)
        retainInstance = true
        val statusLayout = StatusLayout.setNewAdapter(BaseStatusAdapter())
        statusHelper = statusLayout.attachView(binding.statusLayout)
            .onRetryClick {
                viewModel().refresh()
            }
        return binding.root
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_todo
    }

    override fun viewModel(): UnfinishedViewModel {
        if (viewModel == null){
            viewModel = ViewModelProviders.of(this).get(UnfinishedViewModel::class.java)
        }
        return viewModel as UnfinishedViewModel
    }

    override fun dataInsert(data: ObservableArrayList<TodoEvent>) {
        adapter.setNewData(data)
    }

    override fun refreshCancel() {
        if (binding.statusLayout.isRefreshing){
            binding.statusLayout.isRefreshing = false
        }
    }

    override fun isRefreshing(): Boolean {
        return binding.statusLayout.isRefreshing
    }

    override fun fragmentTag(): String {
        return "unfinished"
    }

    override fun init() {
        binding.articleRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.statusLayout.setOnRefreshListener {
            viewModel().refresh()
        }
        adapter.loadMoreModule?.setOnLoadMoreListener {
            viewModel().loadNextPage()
        }
        adapter.addChildClickViewIds(R.id.todo_item_delete)
        adapter.addChildClickViewIds(R.id.todo_item_finish)
        adapter.setOnItemClickListener { adapter, view, position ->
            val intent = Intent(activity,TodoDetailActivity::class.java)
            startActivity(intent)
        }
        adapter.setOnItemChildClickListener { adapter, view, position ->
            when(view.id){
                R.id.todo_item_finish ->{

                }
                R.id.todo_item_delete ->{

                }
            }
        }
        adapter.loadMoreModule?.isEnableLoadMoreIfNotFullPage = false
        binding.articleRecyclerView.adapter = adapter
        binding.articleRecyclerView.addItemDecoration( DividerItemDecoration(
            context, DividerItemDecoration.VERTICAL)
        )
        viewModel().getCacheData()
    }
}