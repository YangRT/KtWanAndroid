package com.example.wanandroid

import android.app.Application
import android.content.Context
import com.drake.statelayout.StateConfig

class MyApplication:Application() {

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        StateConfig.apply {
            emptyLayout = R.layout.empty
            errorLayout = R.layout.error
            loadingLayout = R.layout.loading

            setRetryIds(R.id.msg)
        }
    }
    companion object {
        lateinit var context: Context
    }
}