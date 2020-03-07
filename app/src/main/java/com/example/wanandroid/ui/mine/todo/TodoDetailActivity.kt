package com.example.wanandroid.ui.mine.todo

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import com.example.wanandroid.R
import com.example.wanandroid.databinding.ActivityTodoDetailBinding

class TodoDetailActivity : AppCompatActivity() {

    private lateinit var binding:ActivityTodoDetailBinding
    private lateinit var status:String
    private lateinit var fragment: TodoDetailFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_todo_detail)
        status = intent.getStringExtra("status")

        setSupportActionBar(binding.toolbar)
        supportActionBar?.let {
            it.title = "详情"
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowHomeEnabled(true)
        }
        binding.toolbar.setTitleTextAppearance(this,R.style.Toolbar_TitleText)
        binding.toolbar.setBackgroundColor(Color.parseColor("#03A9F4"))
        window.statusBarColor = Color.parseColor("#03A9F4")
        setTitleCenter("详情")
        val bundle = Bundle()
        fragment = TodoDetailFragment(status)
        if(status != "add"){
            bundle.putSerializable("detail",intent.getSerializableExtra("detail"));
        }
        fragment.arguments = bundle
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fragment,fragment).commit()
    }

    private fun setTitleCenter(text:String) {
        binding.toolbar.title = "title"
        for (i in 0..binding.toolbar.childCount) {
            val view = binding.toolbar.getChildAt(i)
            if (view is TextView) {
                if ("title" == view.text) {
                    view.gravity = Gravity.CENTER
                    val params = Toolbar.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.MATCH_PARENT)
                    params.gravity = Gravity.CENTER
                    view.setLayoutParams(params)
                }
            }
            binding.toolbar.title = text
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.todo_toolbar_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home) {
            finish()
            return true
        }else if (item.itemId == R.id.todo_edit){
            if(item.title.equals("编辑")){
                item.title = "正在编辑"

            }else {
                item.title = "编辑"

            }
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        if(status == "unfinished"){
            menu?.let {
                it.findItem(R.id.todo_edit).isVisible = true
                it.findItem(R.id.todo_edit).setTitle("编辑")
            }

        }else {
            menu?.findItem(R.id.todo_edit)?.isVisible = false
        }
        return super.onPrepareOptionsMenu(menu)
    }
}
