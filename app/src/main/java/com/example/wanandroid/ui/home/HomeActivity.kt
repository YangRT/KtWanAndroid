package com.example.wanandroid.ui.home

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.wanandroid.R
import com.example.wanandroid.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private val TAG = "HomeActivity"

    private lateinit var binding:ActivityHomeBinding

    private var choseFragmentId = R.id.main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_home)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "首页"
        binding.toolbar.setTitleTextAppearance(this,R.style.Toolbar_TitleText_low)
        window.statusBarColor = Color.parseColor("#03A9F4")

        binding.bottomView.setOnNavigationItemSelectedListener {
            choseFragmentId = it.itemId
            var fragment:Fragment
            when(it.itemId){
                R.id.main -> Log.e(TAG,"main")
                R.id.square -> Log.e(TAG,"squre")
                R.id.project ->  Log.e(TAG,"project")
                R.id.mine -> Log.e(TAG,"mine")
            }
            invalidateOptionsMenu()
            return@setOnNavigationItemSelectedListener true
        }
    }

    fun switchFragment(from:Fragment,to:Fragment){
        if(from != to){
            val transaction = supportFragmentManager.beginTransaction()
            if(!to.isAdded){
                transaction.hide(from)
                transaction.add(R.id.fragment,to).commit()
            }else{
                transaction.hide(from)
                transaction.show(to).commit()
            }
        }
    }


}
