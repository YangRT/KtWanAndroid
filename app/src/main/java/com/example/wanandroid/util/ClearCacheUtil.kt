package com.example.wanandroid.util

import android.content.Context
import java.io.File


private fun cleanSharedPreference(context: Context){
    deleteFilesByDirectory(File(context.filesDir.path
            + context.packageName + "/shared_prefs"))
}


private fun cleanDatabases(context: Context){
    deleteFilesByDirectory(File(context.filesDir.path
            + context.getPackageName() + "/databases"))
}

private fun deleteFilesByDirectory(directory:File){
    if (directory.exists() && directory.isDirectory) {
        for (item in directory.listFiles()) {
            item.delete()
        }
    }
}

fun cleanApplicationData(context: Context){
    cleanDatabases(context)
    cleanSharedPreference(context)
}