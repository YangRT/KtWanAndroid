package com.example.wanandroid.ui.home

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.wanandroid.R
import com.example.wanandroid.databinding.ActivityHomeBinding
import com.example.wanandroid.ui.mainPage.MainPageFragment
import com.example.wanandroid.ui.mine.MineFragment
import com.example.wanandroid.ui.project.ProjectFragment
import com.example.wanandroid.ui.square.SquareFragment

class HomeActivity : AppCompatActivity() {

    private val TAG = "HomeActivity"

    private var mainPageFragment:Fragment = MainPageFragment()
    private var squareFragment:Fragment = SquareFragment()
    private var projectFragment:Fragment = ProjectFragment()
    private var mineFragment:Fragment = MineFragment()
    private lateinit var from :Fragment
    private lateinit var binding:ActivityHomeBinding

    private var choseFragmentId = R.id.main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_home)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = ""
        binding.toolbarTitle.text = "首页"
        binding.toolbar.setTitleTextAppearance(this,R.style.Toolbar_TitleText_low)
        window.statusBarColor = Color.parseColor("#03A9F4")
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fragment, mainPageFragment).commit()
        from = mainPageFragment

        binding.bottomView.setOnNavigationItemSelectedListener {
            choseFragmentId = it.itemId
            var fragment:Fragment? = null
            when(it.itemId){
                R.id.main -> fragment = mainPageFragment
                R.id.square -> fragment = squareFragment
                R.id.project ->  fragment = projectFragment
                R.id.mine -> fragment = mineFragment
            }
            fragment?.let { i ->
                if(fragment == from){
                    return@setOnNavigationItemSelectedListener true
                }
                switchFragment(from, i)
                from = fragment
            }
            binding.toolbarTitle.text = it.title
            invalidateOptionsMenu()
            return@setOnNavigationItemSelectedListener true
        }
    }

    private fun switchFragment(from:Fragment,to:Fragment){
        if(from != to){
            Log.e("Home","switchFragment")
            val transaction = supportFragmentManager.beginTransaction()
            if(!to.isAdded){
                Log.e("Home","false")
                transaction.hide(from).add(R.id.fragment,to).commit()
            }else{
                Log.e("Home","to")
                transaction.hide(from)
                transaction.show(to).commit()
            }
        }
    }


}
