package com.example.wanandroid.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.wanandroid.R
import com.example.wanandroid.databinding.ActivityBaseListBinding
import com.example.wanandroid.ui.mine.rank.RankFragment

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
        supportActionBar?.title = ""
        val intent = intent
        type = intent.getStringExtra("type")
        val bundle = Bundle()
        type?.let {
            binding.toolbarTitle.text = type
            when(type){
                "我的收藏" ->{
                    bundle.putString("type","collect")
                }
                "我的分享" ->{
                    bundle.putString("type","share")
                }
                "积分排行" ->{
                    mFragment = RankFragment()
                }
                "积分明细" ->{
                    bundle.putString("count",intent.getStringExtra("count"))
                }
                "知识体系" ->{

                }
                "导航" ->{

                }
                "search" ->{
                    bundle.putString("key",intent.getStringExtra("key"));
                }
                "分享文章" ->{

                }
                else ->{
                    bundle.putString("type",type)
                    bundle.putStringArrayList("tabTitle",intent.getStringArrayListExtra("tabTitle"))
                    bundle.putIntegerArrayList("tabId",intent.getIntegerArrayListExtra("tabId"))
                }
            }
        }
        mFragment.arguments = bundle
        val transition = supportFragmentManager.beginTransaction()
        transition.add(R.id.fragment_container,mFragment).commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item)
    }
}
