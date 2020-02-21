package com.example.wanandroid.ui.mine.knowledge

import android.content.Intent
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wanandroid.R
import com.example.wanandroid.base.BaseFragment
import com.example.wanandroid.base.BaseListActivity
import com.example.wanandroid.customview.SpaceItemDecoration
import com.example.wanandroid.data.model.KnowledgeData
import com.example.wanandroid.databinding.FragmentKnowledgeBinding
import com.example.wanandroid.databinding.FragmentListBinding
import com.example.wanandroid.repository.KnowledgeRepository


/**
 * @program: WanAndroid
 *
 * @description: 知识体系 ui
 *
 * @author: YangRT
 *
 * @create: 2020-02-19 21:29
 **/

class KnowledgeFragment:BaseFragment<KnowledgeData,KnowledgeRepository,KnowledgeViewModel,FragmentKnowledgeBinding>() {

    private lateinit var adapter: KnowledgeAdapter

    override fun getLayoutId(): Int {
        return R.layout.fragment_knowledge
    }

    override fun viewModel(): KnowledgeViewModel {
        if(viewModel == null){
            viewModel = ViewModelProviders.of(this).get(KnowledgeViewModel::class.java)
        }
        return viewModel as KnowledgeViewModel
    }

    override fun dataInsert(data: ObservableArrayList<KnowledgeData>) {
        adapter.setNewData(data)
    }

    override fun refreshCancel() {
    }

    override fun isRefreshing(): Boolean {
        return false
    }

    override fun fragmentTag(): String {
        return "knowledge"
    }

    override fun init() {
        binding.knowledgeRecyclerview.layoutManager = LinearLayoutManager(context)
        binding.knowledgeRecyclerview.addItemDecoration(SpaceItemDecoration(50))
        adapter = KnowledgeAdapter(R.layout.knowledge_tree_item,ArrayList<KnowledgeData>())
        binding.knowledgeRecyclerview.adapter = adapter
        adapter.setOnItemClickListener { adapter, view, position ->
            val intent = Intent(activity,BaseListActivity::class.java)
            val data = adapter.data as List<KnowledgeData>
            intent.putExtra("type",data[position].name)
            val titleList = ArrayList<String>()
            val idList = ArrayList<Int>()
            for (item in data[position].children.listIterator()){
                titleList.add(item.name)
                idList.add(item.id)
            }
            intent.putStringArrayListExtra("tabTitle",titleList)
            intent.putIntegerArrayListExtra("tabId",idList)
            startActivity(intent)
        }
        viewModel().getCacheData()
    }
}