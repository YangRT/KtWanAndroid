package com.example.wanandroid.data.network

import android.util.Log
import com.example.wanandroid.data.network.api.*
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.*

class WanNetwork {

    private val loginService = ServiceCreator.createLogin(LoginService::class.java)
    private val registerService: RegisterService = ServiceCreator.create(RegisterService::class.java)
    private val getArticleService:GetArticlesService = ServiceCreator.create(GetArticlesService::class.java)
    private val getProjectService:GetProjectService = ServiceCreator.create(GetProjectService::class.java)
    private val getSquareService:GetSquareService = ServiceCreator.create(GetSquareService::class.java)
    private val getBannerService:GetBannerService = ServiceCreator.create(GetBannerService::class.java)
    private val getMineService:GetMineService = ServiceCreator.create(GetMineService::class.java)
    private val getRankService:GetRankService = ServiceCreator.create(GetRankService::class.java)
    private val getKnowledgeService:GetKnowledgeService = ServiceCreator.create(GetKnowledgeService::class.java)
    private val getNavigationService:GetNavigationService = ServiceCreator.create(GetNavigationService::class.java)
    private val getGzhService:GetGzhService = ServiceCreator.create(GetGzhService::class.java)
    private val getMyArticleService:GetMyArticleService = ServiceCreator.create(GetMyArticleService::class.java)
    private val getTodoService:TodoService = ServiceCreator.create(TodoService::class.java)
    private val searchService:SearchService = ServiceCreator.create(SearchService::class.java)
    private val collectService:CollectService = ServiceCreator.create(CollectService::class.java)
    private val shareService:ShareService = ServiceCreator.create(ShareService::class.java)
    private val exitService:ExitService = ServiceCreator.createLogin(ExitService::class.java)

    suspend fun login(username:String,password:String) = withContext(Dispatchers.IO){loginService.getLoginInfo(username,password).await()}
    suspend fun register(username:String,password:String,repassword:String) = withContext(Dispatchers.IO){registerService.getRegisterInfo(username,password,repassword).await()}
    suspend fun getArticle(page:Int) = withContext(Dispatchers.IO){getArticleService.getArticles(page).await()}
    suspend fun getProject(page: Int) = withContext(Dispatchers.IO){getProjectService.getProjectInfo(page).await()}
    suspend fun getProjectClassic() = withContext(Dispatchers.IO){getProjectService.getProjectClassic().await()}
    suspend fun getProjectArticle(page: Int,cid:Int) = withContext(Dispatchers.IO){getProjectService.getProjectArticle(page, cid).await()}
    suspend fun getSquare(page: Int) = withContext(Dispatchers.IO){getSquareService.getSquareInfo(page).await()}
    suspend fun getBanner() = withContext(Dispatchers.IO){getBannerService.getBannerInfo().await()}
    suspend fun getMine() = withContext(Dispatchers.IO){getMineService.getMineInfo().await()}
    suspend fun getPointDetail(page: Int) = withContext(Dispatchers.IO){getMineService.getPointDetail(page).await()}
    suspend fun getRank(page:Int) = withContext(Dispatchers.IO){getRankService.getRankInfo(page).await()}
    suspend fun getKnowledgeInfo() = withContext(Dispatchers.IO){getKnowledgeService.getKnowledgeInfo().await()}
    suspend fun getKnowledgeArticle(page:Int,id:Int) = withContext(Dispatchers.IO){getKnowledgeService.getKnowledgeArticle(page,id).await()}
    suspend fun getNavigationInfo() = withContext(Dispatchers.IO){getNavigationService.getNavigationInfo().await()}
    suspend fun getGzhList() = withContext(Dispatchers.IO){getGzhService.getGzhListInfo().await()}
    suspend fun getGzhArticleById(page: Int,id: Int) = withContext(Dispatchers.IO){ getGzhService.getGzhArticlesById(id,page).await() }
    suspend fun getShareArticle(page: Int) = withContext(Dispatchers.IO){ getMyArticleService.getShareArticle(page).await()}
    suspend fun getCollectArticle(page: Int) = withContext(Dispatchers.IO){getMyArticleService.getCollectArticle(page).await()}
    suspend fun getHotWord() = withContext(Dispatchers.IO){searchService.getHotWord().await()}
    suspend fun getSearchArticle(page:Int,key:String) = withContext(Dispatchers.IO){searchService.search(page,key).await()}
    suspend fun addCollect(id:Int) = withContext(Dispatchers.IO){collectService.addCollectArticle(id).await()}
    suspend fun unCollect(id:Int) = withContext(Dispatchers.IO){collectService.getUnCollectResponse(id).await()}
    suspend fun unCollectInMine(id: Int,originId:Int) = withContext(Dispatchers.IO){collectService.getUnCollectInMineResponse(id, originId).await()}
    suspend fun shareArticle(title:String,link:String) = withContext(Dispatchers.IO){shareService.getShareArticleResponse(title, link).await()}
    suspend fun exit() = withContext(Dispatchers.IO){exitService.getExitInfo().await()}

    suspend fun getTodoList(page: Int,status:Int,type:Int?) = withContext(Dispatchers.IO){ getTodoService.getTodoList(page,status,type).await() }
    suspend fun getDeleteTodoResponse(id:Int) = withContext(Dispatchers.IO){ getTodoService.getDeleteResponse(id).await() }
    suspend fun getAddTodoResponse(title:String,content:String,type:Int,date:String) = withContext(Dispatchers.IO){ getTodoService.getAddEventResponse(title,content,date,type).await() }
    suspend fun getChangeTodoStatusResponse(id:Int,status: Int) = withContext(Dispatchers.IO){ getTodoService.getChangeStatusResponse(id,status).await() }
    suspend fun getUpdateTodoResponse(id:Int,title: String,content: String,type: Int,date: String) = withContext(Dispatchers.IO){ getTodoService.getUpdateEventResponse(id, title, content, date, type).await() }

    private suspend fun <T> Call<T>.await(): T {
        //suspendCoroutine 这个方法并不是帮我们启动协程的，它运行在协程当中
        // 并且帮我们获取到当前协程的 Continuation 实例，
        // 也就是拿到回调，方便后面我们调用它的
        // resume 或者 resumeWithException 来返回结果或者抛出异常
        return suspendCoroutine { continuation ->
            Log.e("await",Thread.currentThread().name)
            enqueue(object : Callback<T> {
                override fun onFailure(call: Call<T>, t: Throwable) {
                    t.printStackTrace()
                    continuation.resumeWithException(t)
                }

                override fun onResponse(call: Call<T>, response: Response<T>) {
                    Log.e("onResponse",Thread.currentThread().name)
                    val body = response.body()
                    if(response.isSuccessful){
                        Log.e("BaseOnResponse","isSuccessful")
                        Log.e("BaseOnResponse",response.message())
                    }
                    if (body != null){
                        Log.e("BaseOnResponse",response.body().toString())
                        continuation.resume(body)
                    }
                    else continuation.resumeWithException(RuntimeException("response body is null"))
                }
            })
        }
    }

    companion object {
        private var network: WanNetwork? = null
        fun getInstance(): WanNetwork {
            if (network == null) {
                synchronized(WanNetwork::class.java) {
                    if (network == null) {
                        network = WanNetwork()
                    }
                }
            }
            return network!!
        }

    }





}