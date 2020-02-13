package com.example.wanandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.wanandroid.util.getData
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val data = getData<List<String>>("data", object :TypeToken<List<String>>(){}.type)
        
    }
}
