package com.example.wanandroid.ui.mine.todo.finish

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableArrayList
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
 * @create: 2020-02-21 20:08
 **/

class FinishFragment:BaseFragment<TodoEvent,TodoRepository,FinishViewModel,FragmentTodoBinding>() {

    private lateinit var adapter:TodoAdapter

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

    override fun viewModel(): FinishViewModel {
        if (viewModel == null){
            viewModel = ViewModelProviders.of(this).get(FinishViewModel::class.java)
        }
        return viewModel as FinishViewModel
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
        return "finish"
    }

    override fun init() {
        adapter = TodoAdapter(ArrayList())
        binding.articleRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.statusLayout.setOnRefreshListener {
            viewModel().refresh()
        }
        adapter.loadMoreModule?.setOnLoadMoreListener {
            viewModel().loadNextPage()
        }
        adapter.loadMoreModule?.isEnableLoadMoreIfNotFullPage = false
        adapter.addChildClickViewIds(R.id.todo_item_delete)
        adapter.setOnItemClickListener { adapter, view, position ->
            val intent = Intent(activity, TodoDetailActivity::class.java)
            startActivity(intent)
        }
        adapter.setOnItemChildClickListener { adapter, view, position ->

        }
        binding.articleRecyclerView.adapter = adapter
        binding.articleRecyclerView.addItemDecoration( DividerItemDecoration(
            context, DividerItemDecoration.VERTICAL)
        )
        viewModel().getCacheData()
    }

}