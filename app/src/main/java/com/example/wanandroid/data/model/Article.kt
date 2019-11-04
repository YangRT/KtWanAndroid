package com.example.wanandroid.data.model

data class Article(val apkLink:String?, val audit:Int, val author:String?, val chapterId:Int, val chapterName:String,
                   val collect:Boolean, val courseId:Int, val desc:String?, val envelopePic:String?, val fresh:Boolean,
                   val id:Int, val link:String, val niceDate:String, val niceShareDate:String, val origin:String?, val prefix:String?,
                   val projectLink:String?, val publishTime:String, val selfVisible:Int, val shareDate:String, val shareUser:String,
                   val superChapterName:String, val tags:List<Tag>?, val title:String, val type:Int, val userId:Int, val visible:Int, val zan:Int)