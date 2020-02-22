package com.example.wanandroid.ui.mainPage.search.word

import android.content.Intent
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.ViewModelProviders
import com.example.wanandroid.R
import com.example.wanandroid.base.BaseFragment
import com.example.wanandroid.base.BaseListActivity
import com.example.wanandroid.data.model.HotWord
import com.example.wanandroid.databinding.FragmentSearchWordBinding
import com.example.wanandroid.repository.HotWordRepository
import com.google.android.flexbox.FlexboxLayoutManager


/**
 * @program: WanAndroid
 *
 * @description: 搜索热词 ui
 *
 * @author: YangRT
 *
 * @create: 2020-02-22 11:18
 **/

class HotWordFragment:BaseFragment<HotWord,HotWordRepository,HotWordViewModel,FragmentSearchWordBinding>() {

    private lateinit var adapter: HotWordAdapter

    override fun getLayoutId(): Int {
        return R.layout.fragment_search_word
    }

    override fun viewModel(): HotWordViewModel {
        if (viewModel == null){
            viewModel = ViewModelProviders.of(this).get(HotWordViewModel::class.java)
        }
        return viewModel as HotWordViewModel
    }

    override fun dataInsert(data: ObservableArrayList<HotWord>) {
        adapter.setNewData(data)
    }

    override fun refreshCancel() {
    }

    override fun isRefreshing(): Boolean {
        return false
    }

    override fun fragmentTag(): String {
        return "hotWord"
    }

    override fun init() {
        adapter = HotWordAdapter(ArrayList())
        adapter.setOnItemClickListener { adapter, view, position ->
            val intent = Intent(activity,BaseListActivity::class.java)
            intent.putExtra("type","search")
            intent.putExtra("key",(adapter.data[position] as HotWord).name)
            startActivity(intent)
        }
        binding.searchWordRecycler.layoutManager =  FlexboxLayoutManager(context)
        binding.searchWordRecycler.adapter = adapter
        viewModel().getCacheData()
    }
}