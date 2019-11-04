package com.example.wanandroid.data.model

data class ArticlesData(val curPage:Int, val data:List<Article>?, val offest:Int, val over:Boolean, val pageCount:Int, val size:Int, val total:Int)