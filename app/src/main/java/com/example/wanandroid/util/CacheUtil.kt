package com.example.wanandroid.util

import android.content.Context
import com.example.wanandroid.MyApplication
import com.google.gson.Gson
import java.lang.reflect.Type

fun saveData(data:String,key:String){
    val sharedPreferences = MyApplication.context.getSharedPreferences(key, Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    editor.putString("data",data)
    editor.apply();
}

fun saveTime(time:Long,key:String){
    val sharedPreferences = MyApplication.context.getSharedPreferences(key, Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    editor.putLong("time",time)
    editor.apply();
}

fun saveUserInfo(userName:String){
    val sharedPreferences = MyApplication.context.getSharedPreferences("user", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    editor.putString("name",userName)
    editor.apply();
}

fun getSaveData(key: String):String?{
    val sharedPreferences = MyApplication.context.getSharedPreferences("user", Context.MODE_PRIVATE)
    return sharedPreferences.getString(key,null)
}

fun getSaveTime(key: String):Long{
    val sharedPreferences = MyApplication.context.getSharedPreferences("user", Context.MODE_PRIVATE)
    return sharedPreferences.getLong(key,0)
}


fun getUserInfo():String?{
    val sharedPreferences = MyApplication.context.getSharedPreferences("user", Context.MODE_PRIVATE)
    return sharedPreferences.getString("user",null)
}

fun  <T> getData(key:String,type:Type):T?{
    val data = getSaveData(key)
    if(data != null){
        return Gson().fromJson(data,type)
    }
    return null
}