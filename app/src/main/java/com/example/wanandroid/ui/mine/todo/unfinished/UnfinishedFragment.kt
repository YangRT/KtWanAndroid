package com.example.wanandroid.ui.mine.todo.unfinished

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.Observer
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
import com.example.wanandroid.ui.mine.todo.*


/**
 * @program: WanAndroid
 *
 * @description: todo列表 ui
 *
 * @author: YangRT
 *
 * @create: 2020-02-21 19:57
 **/

class UnfinishedFragment:BaseFragment<TodoEvent,TodoRepository,UnfinishedViewModel,FragmentTodoBinding>(),
    AdapterView.OnItemSelectedListener,View.OnClickListener{

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
        adapter.replaceData(data)
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
        adapter = TodoAdapter(ArrayList())
        binding.articleRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.statusLayout.setOnRefreshListener {
            viewModel().refresh()
        }
        viewModel().competeResponse.observe(this, Observer {
            if (it.position != -1){
                competeResponse(it)
            }
        })
        viewModel().deleteResponse.observe(this, Observer {
            if (it.position != -1){
                deleteResponse(it)
            }
        })
        adapter.loadMoreModule?.setOnLoadMoreListener {
            viewModel().loadNextPage()
        }
        adapter.addChildClickViewIds(R.id.todo_item_delete)
        adapter.addChildClickViewIds(R.id.todo_item_finish)
        adapter.setOnItemClickListener { adapter, view, position ->
            val data = adapter.data[position] as TodoEvent
            val intent = Intent(activity, TodoDetailActivity::class.java)
            val e = Event()
            e.title = data.title
            e.content = data.content
            e.date = data.dateStr
            e.type = data.type
            intent.putExtra("detail",e)
            intent.putExtra("status","unFinished")
            startActivity(intent)
        }
        adapter.setOnItemChildClickListener { adapter, view, position ->
            val data = adapter.data[position] as TodoEvent
            when(view.id){
                R.id.todo_item_finish ->{
                    viewModel().competeEvent(data.id,position)
                }
                R.id.todo_item_delete ->{
                    viewModel().deleteEvent(data.id,position)
                }
            }
        }
        adapter.loadMoreModule?.isEnableLoadMoreIfNotFullPage = false
        binding.articleRecyclerView.adapter = adapter
        binding.articleRecyclerView.addItemDecoration( DividerItemDecoration(
            context, DividerItemDecoration.VERTICAL)
        )

        binding.listSpinner.onItemSelectedListener = this
        binding.todoToolbarAdd.setOnClickListener(this)
        binding.todoToolbarBack.setOnClickListener(this)

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        Log.e("unfinished","$position")
        when (position){
            0 -> viewModel().changeType(0)
            Type.LIFE -> viewModel().changeType(Type.LIFE)
            Type.WORK -> viewModel().changeType(Type.WORK)
            Type.LEARN -> viewModel().changeType(Type.LEARN)
            Type.OTHER -> viewModel().changeType(Type.OTHER)
        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.todo_toolbar_back -> activity?.finish()
            R.id.todo_toolbar_add -> {
                val intent = Intent(activity,TodoDetailActivity::class.java)
                intent.putExtra("status","add")
                startActivity(intent)
            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    private fun deleteResponse(changeRecord: ChangeRecord){
        if (changeRecord.result){
            adapter.remove(changeRecord.position)
            Toast.makeText(context,"删除成功！", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(context,"删除失败！", Toast.LENGTH_SHORT).show()
        }
    }

    private fun competeResponse(changeRecord: ChangeRecord){
        if (changeRecord.result){
            adapter.remove(changeRecord.position)
            Toast.makeText(context,"修改成功！",Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(context,"修改失败！",Toast.LENGTH_SHORT).show()
        }
    }
}