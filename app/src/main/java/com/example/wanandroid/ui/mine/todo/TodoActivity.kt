package com.example.wanandroid.ui.mine.todo

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.wanandroid.R
import com.example.wanandroid.databinding.ActivityTodoBinding
import com.example.wanandroid.ui.mine.todo.finish.FinishFragment
import com.example.wanandroid.ui.mine.todo.unfinished.UnfinishedFragment

class TodoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTodoBinding
    private val finishFragment = FinishFragment()
    private val unFinishFragment = UnfinishedFragment()
    private lateinit var from:Fragment
    private var choseFragmentId = R.id.todo_unfinished

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_todo)
        window.statusBarColor = Color.parseColor("#03A9F4")

        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fragment,unFinishFragment).commit();
        from = unFinishFragment

        binding.bottomView.setOnNavigationItemSelectedListener {
            choseFragmentId = it.itemId
            var fragment:Fragment? = null
            when(it.itemId){
                R.id.todo_unfinished -> fragment = unFinishFragment
                R.id.todo_finish -> fragment = finishFragment
            }
            fragment?.let { i ->
                if(i == from){
                    return@setOnNavigationItemSelectedListener true
                }
                switchFragment(from, i)
                from = i
            }
            invalidateOptionsMenu()
            return@setOnNavigationItemSelectedListener true
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
}
