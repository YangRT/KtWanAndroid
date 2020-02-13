package com.example.wanandroid.base

import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine


/**
 * @program: WanAndroid
 *
 * @description: mvvm架构m层基类
 *
 * @author: YangRT
 *
 * @create: 2020-02-12 21:52
 **/

public abstract class BaseMvvmRepository<T>(var isPaging:Boolean,var key:String?,var data:String?) {

    private  val TAG = "BaseMvvmRepository"


    protected var pageNum = 0
    protected val mIsPaging = isPaging
    protected var mCachedPreferenceKey = key
    protected var mApkPreferenceData = data

    private var isFirst = true
    protected var isRefreshing = false


    protected fun saveDataToPreference(data:T?){
        //保存数据
    }

    suspend fun getCachedDataAndLoad(){
        if(mCachedPreferenceKey != null){
            //获取数据
        }else{

        }
        load()
    }

    abstract suspend fun load():T
    abstract fun refresh()

    protected fun isNeedToUpdate():Boolean{
        return true
    }

    protected  fun getTClass():Type?{
        return null;
    }

    private suspend fun <T> Call<T>.await(): T {
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T> {
                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }

                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    if (body != null) continuation.resume(body)
                    else continuation.resumeWithException(RuntimeException("response body is null"))
                }
            })
        }
    }

}
