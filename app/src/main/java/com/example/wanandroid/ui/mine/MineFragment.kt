package com.example.wanandroid.ui.mine

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wanandroid.R
import com.example.wanandroid.base.BaseFragment
import com.example.wanandroid.base.BaseListActivity
import com.example.wanandroid.base.PageStatus
import com.example.wanandroid.data.model.MineData
import com.example.wanandroid.databinding.FragmentMineBinding
import com.example.wanandroid.repository.MineRepository
import com.example.wanandroid.util.getUserInfo
import com.example.wanandroid.util.saveUserInfo


/**
 * @program: WanAndroid
 *
 * @description: "我的" ui
 *
 * @author: YangRT
 *
 * @create: 2020-02-16 15:06
 **/

class MineFragment :BaseFragment<MineData,MineRepository, MineViewModel,FragmentMineBinding>(){

    private lateinit var mineData:MineData
    private lateinit var itemAdapter:MineAdapter
    private  var itemList: MutableList<MineItemInfo> = ArrayList()
    override fun getLayoutId(): Int {
        return R.layout.fragment_mine
    }

    override fun viewModel(): MineViewModel {
        if(viewModel == null){
            viewModel = ViewModelProviders.of(this).get(MineViewModel::class.java)
        }
        return viewModel as MineViewModel;
    }

    override fun dataInsert(data: ObservableArrayList<MineData>) {
        if (data.size > 0){
            mineData = data[0]
            setData()
            saveUserInfo(mineData.username)
        }


    }

    override fun refreshCancel() {
        if (binding.mainPageRefreshLayout.isRefreshing){
            binding.mainPageRefreshLayout.isRefreshing = false
        }
    }

    override fun isRefreshing(): Boolean {
        return binding.mainPageRefreshLayout.isRefreshing
    }

    override fun fragmentTag(): String {
        return "mine"
    }

    override fun init() {
        setItemInfo()
        itemAdapter = MineAdapter(R.layout.mine_item,itemList)
        val layoutManager = object :LinearLayoutManager(context){
            override fun canScrollVertically(): Boolean {
                return false
            }
        }
        binding.mineRecyclerview.layoutManager = layoutManager
        binding.mineRecyclerview.adapter = itemAdapter
        binding.mineRecyclerview.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        binding.mainPageRefreshLayout.setOnRefreshListener {
            viewModel().refresh()
        }
        itemAdapter.setOnItemClickListener { adapter, view, position ->
            if (position < 3){
                if(getUserInfo() == null){
                    Log.e("Mine","请先登录")
                    Toast.makeText(context,"请先登录！",Toast.LENGTH_SHORT).show()
                    return@setOnItemClickListener
                }
            }
            val intent = Intent(activity,BaseListActivity::class.java)
            intent.putExtra("type",(adapter.data[position] as MineItemInfo).title)
            if(position == 2){
                intent.putExtra("count",binding.minePoint.text.toString());
            }
            startActivity(intent)
        }
        viewModel().getCacheData()
    }

    override fun onChanged(t: Any) {
        if(t is PageStatus) {
            if(t == PageStatus.EMPTY){
                setDefaultValue()
                refreshCancel()
            }else if(t == PageStatus.REFRESH_ERROR){
                refreshCancel()
                Toast.makeText(context,"刷新失败！",Toast.LENGTH_SHORT).show()
            }else if(t == PageStatus.SHOW_CONTENT){
                refreshCancel()
            }
        }
    }

    private fun setItemInfo(){
        val collect = MineItemInfo()
        collect.title= "我的收藏"
        collect.image = R.drawable.collect
        val share =  MineItemInfo()
        share.title = "我的分享"
        share.image = R.drawable.share
        val point =  MineItemInfo()
        point.title = "积分明细"
        point.image = R.drawable.money
        val rank =  MineItemInfo()
        rank.title = "积分排行"
        rank.image = R.drawable.ph
        val knowledge =  MineItemInfo()
        knowledge.title = "知识体系"
        knowledge.image = R.drawable.knowledge
        val wx =  MineItemInfo()
        wx.title = "公众号文章"
        wx.image = R.drawable.gzh
        val navigation =  MineItemInfo()
        navigation.title = "导航"
        navigation.image = R.drawable.navigation
        itemList.add(collect);
        itemList.add(share);
        itemList.add(point);
        itemList.add(rank);
        itemList.add(knowledge);
        itemList.add(wx);
        itemList.add(navigation);
    }

    private fun setData(){
        binding.mineLevel.text = "等级:${mineData.level}"
        binding.mineUsername.text = mineData.username
        binding.mineRank.text = "排名:${mineData.rank}"
        binding.minePoint.text = "积分:${mineData.coinCount}"
    }
    private fun setDefaultValue(){
        binding.mineLevel.text = "等级:0"
        binding.mineUsername.text = "未登录"
        binding.mineRank.text = "排名:000"
        binding.minePoint.text = "积分:0000"
    }

}