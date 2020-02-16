package com.example.wanandroid.customview.search

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


/**
 * @program: WanAndroid
 *
 * @description: 记录搜索记录
 *
 * @author: YangRT
 *
 * @create: 2020-02-16 16:13
 **/

class RecordSQLHelper(context: Context) :SQLiteOpenHelper(context,"temp.db",null,1){


    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("create table records(id integer primary key autoincrement,name varchar(200))");

    }
}