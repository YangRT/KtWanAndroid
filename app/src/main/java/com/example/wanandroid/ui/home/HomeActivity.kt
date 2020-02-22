package com.example.wanandroid.ui.home

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.wanandroid.MyApplication
import com.example.wanandroid.R
import com.example.wanandroid.base.BaseListActivity
import com.example.wanandroid.databinding.ActivityHomeBinding
import com.example.wanandroid.ui.login.LoginActivity
import com.example.wanandroid.ui.mainPage.MainPageFragment
import com.example.wanandroid.ui.mainPage.search.SearchActivity
import com.example.wanandroid.ui.mine.MineFragment
import com.example.wanandroid.ui.mine.todo.TodoActivity
import com.example.wanandroid.ui.project.ProjectFragment
import com.example.wanandroid.ui.square.SquareFragment
import com.example.wanandroid.util.cleanApplicationData
import com.example.wanandroid.util.getUserInfo

class HomeActivity : AppCompatActivity(),Observer<Int> {


    private val TAG = "HomeActivity"

    private lateinit var viewModel: HomeViewModel
    private var mainPageFragment = MainPageFragment()
    private var squareFragment = SquareFragment()
    private var projectFragment = ProjectFragment()
    private var mineFragment = MineFragment()
    private lateinit var from :Fragment
    private lateinit var binding:ActivityHomeBinding

    private var choseFragmentId = R.id.main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_home)
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

        viewModel.exitResponse.observe(this, this)
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
                if(i == from){
                    return@setOnNavigationItemSelectedListener true
                }
                switchFragment(from, i)
                from = i
            }
            binding.toolbarTitle.text = it.title
            invalidateOptionsMenu()
            return@setOnNavigationItemSelectedListener true
        }
    }

    override fun onChanged(t: Int?) {
        if (t == 1){
            Log.e("Home","success")
            cleanApplicationData(MyApplication().getContext())
            mineFragment.tryToRefresh()
            invalidateOptionsMenu()
        }else{
            Log.e("Home","fail")

        }
    }

    private fun switchFragment(from:Fragment,to:Fragment){
        if(from != to){
            val transaction = supportFragmentManager.beginTransaction()
            if(!to.isAdded){
                transaction.hide(from).add(R.id.fragment,to).commit()
            }else{
                transaction.hide(from)
                transaction.show(to).commit()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_toolbar_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_search ->{
                val intent = Intent(this,SearchActivity::class.java)
                startActivity(intent)
            }
            R.id.action_add ->{
                val intent = Intent(this,BaseListActivity::class.java)
                intent.putExtra("type","分享文章")
                startActivity(intent)
            }
            R.id.action_project ->{
                val intent = Intent(this,BaseListActivity::class.java)
                intent.putExtra("type","项目分类")
                startActivity(intent)

            }
            R.id.action_write ->{
                if(getUserInfo() == null){
                    Toast.makeText(this,"请先登录!",Toast.LENGTH_SHORT).show()
                }else{
                    val intent = Intent(this,TodoActivity::class.java)
                    startActivity(intent)
                }
            }
            R.id.action_exit ->{
                viewModel.exit()
            }
            R.id.action_mine ->{
                val intent = Intent(this,LoginActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        when(choseFragmentId){
            R.id.main ->{
                menu?.findItem(R.id.action_search)?.isVisible = true
                menu?.findItem(R.id.action_add)?.isVisible = false
                menu?.findItem(R.id.action_project)?.isVisible = false
                menu?.findItem(R.id.action_mine)?.isVisible = false
                menu?.findItem(R.id.action_exit)?.isVisible = false
                menu?.findItem(R.id.action_write)?.isVisible = false
            }
            R.id.square ->{
                menu?.findItem(R.id.action_search)?.isVisible = false
                menu?.findItem(R.id.action_add)?.isVisible = true
                menu?.findItem(R.id.action_project)?.isVisible = false
                menu?.findItem(R.id.action_mine)?.isVisible = false
                menu?.findItem(R.id.action_exit)?.isVisible = false
                menu?.findItem(R.id.action_write)?.isVisible = false
            }
            R.id.project ->{
                menu?.findItem(R.id.action_search)?.isVisible = false
                menu?.findItem(R.id.action_add)?.isVisible = false
                menu?.findItem(R.id.action_project)?.isVisible = true
                menu?.findItem(R.id.action_mine)?.isVisible = false
                menu?.findItem(R.id.action_exit)?.isVisible = false
                menu?.findItem(R.id.action_write)?.isVisible = false
            }
            R.id.mine ->{
                menu?.findItem(R.id.action_search)?.isVisible = false
                menu?.findItem(R.id.action_add)?.isVisible = false
                menu?.findItem(R.id.action_project)?.isVisible = false
                if(getUserInfo() != null){
                    menu?.findItem(R.id.action_mine)?.isVisible = false
                    menu?.findItem(R.id.action_exit)?.isVisible = true
                }else{
                    menu?.findItem(R.id.action_mine)?.isVisible = true
                    menu?.findItem(R.id.action_exit)?.isVisible = false
                }
                menu?.findItem(R.id.action_write)?.isVisible = true
            }
        }
        return super.onPrepareOptionsMenu(menu)
    }


}
