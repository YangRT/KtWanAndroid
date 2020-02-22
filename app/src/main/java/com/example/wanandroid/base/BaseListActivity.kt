package com.example.wanandroid.base

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.wanandroid.R
import com.example.wanandroid.databinding.ActivityBaseListBinding
import com.example.wanandroid.ui.mainPage.search.article.SearchArticleFragment
import com.example.wanandroid.ui.mine.collect.CollectFragment
import com.example.wanandroid.ui.mine.knowledge.KnowledgeFragment
import com.example.wanandroid.ui.mine.navigation.NavigationFragment
import com.example.wanandroid.ui.mine.point.PointFragment
import com.example.wanandroid.ui.mine.rank.RankFragment
import com.example.wanandroid.ui.mine.share.ShareFragment
import com.example.wanandroid.ui.square.share.ShareArticleFragment
import com.example.wanandroid.ui.tab.TabFragment

class BaseListActivity : AppCompatActivity() {

    private lateinit var binding:ActivityBaseListBinding
    private var type:String? = null
    private lateinit var mFragment:Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_base_list)
        this.setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        window.statusBarColor = Color.parseColor("#03A9F4")
        binding.toolbar.setBackgroundColor(Color.parseColor("#03A9F4"))
        supportActionBar?.title = ""
        val intent = intent
        type = intent.getStringExtra("type")
        val bundle = Bundle()
        type?.let {
            binding.toolbarTitle.text = type
            when(type){
                "我的收藏" ->{
                    mFragment = CollectFragment()
                }
                "我的分享" ->{
                    mFragment = ShareFragment()
                }
                "积分排行" ->{
                    mFragment = RankFragment()
                }
                "积分明细" ->{
                    mFragment = PointFragment()
                    bundle.putString("count",intent.getStringExtra("count"))
                }
                "知识体系" ->{
                    mFragment = KnowledgeFragment()
                }
                "导航" ->{
                    mFragment = NavigationFragment()
                }
                "公众号文章" ->{
                    mFragment = TabFragment("公众号文章")
                }
                "项目分类" ->{
                    mFragment = TabFragment("项目分类")
                }
                "search" ->{
                    binding.toolbarTitle.text = intent.getStringExtra("key")
                    mFragment = SearchArticleFragment(intent.getStringExtra("key"))
                }
                "分享文章" ->{
                    mFragment = ShareArticleFragment()
                }
                else ->{
                    mFragment = TabFragment(intent.getStringExtra("type"))
                    bundle.putStringArrayList("tabTitle",intent.getStringArrayListExtra("tabTitle"))
                    bundle.putIntegerArrayList("tabId",intent.getIntegerArrayListExtra("tabId"))
                    Log.e("BaseList","${intent.getStringArrayListExtra("tabTitle").size}")
                }
            }
        }
        mFragment.arguments = bundle
        val transition = supportFragmentManager.beginTransaction()
        transition.add(R.id.fragment_container,mFragment).commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
