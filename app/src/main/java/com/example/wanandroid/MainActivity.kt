package com.example.wanandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.example.wanandroid.databinding.ActivityMainBinding
import com.f1reking.library.statuslayout.StatusClickListener

import com.f1reking.library.statuslayout.StatusLayout
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity() {

    lateinit var binding:ActivityMainBinding
    lateinit var statusLayout: StatusLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        statusLayout = StatusLayout.Builder(binding.root)
                    .setOnEmptyLayout(R.layout.empty)
                    .setOnEmptyClickTextColor(R.color.colorMain)
                    .setOnErrorLayout(R.layout.error)
                    .setOnErrorClickTextColor(R.color.colorMain)
                    .setOnLoadingLayout(R.layout.loading)
                    .setOnStatusClickListener(object : StatusClickListener {
                        override fun onEmptyClick(view: View) {
                            statusLayout.showErrorLayout()
                        }

                        override fun onErrorClick(view: View) {
                            statusLayout.showEmptyLayout()
                        }
                    })
                    .build()
        statusLayout.showEmptyLayout()
    }
}
