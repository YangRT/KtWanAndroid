package com.example.wanandroid.data.network

import android.util.Log
import com.example.wanandroid.data.network.api.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

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

    suspend fun login(username:String,password:String) = loginService.getLoginInfo(username,password).await()
    suspend fun register(username:String,password:String,repassword:String) = registerService.getRegisterInfo(username,password,repassword).await()
    suspend fun getArticle(page:Int) = getArticleService.getArticles(page).await()
    suspend fun getProject(page: Int) = getProjectService.getProjectInfo(page).await()
    suspend fun getProjectClassic() = getProjectService.getProjectClassic().await()
    suspend fun getSquare(page: Int) = getSquareService.getSquareInfo(page).await()
    suspend fun getBanner() = getBannerService.getBannerInfo().await()
    suspend fun getMine() = getMineService.getMineInfo().await()
    suspend fun getRank(page:Int) = getRankService.getRankInfo(page).await()
    suspend fun getKnowledgeInfo() = getKnowledgeService.getKnowledgeInfo().await()
    suspend fun getKnowledgeArticle(page:Int,id:Int) = getKnowledgeService.getKnowledgeArticle(page,id).await()
    suspend fun getNavigationInfo() = getNavigationService.getNavigationInfo().await()
    suspend fun getGzhList() = getGzhService.getGzhListInfo().await()
    suspend fun getGzhArticleById(page: Int,id: Int) = getGzhService.getGzhArticlesById(id,page).await()

    private suspend fun <T> Call<T>.await(): T {
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T> {
                override fun onFailure(call: Call<T>, t: Throwable) {
                    t.printStackTrace()
                    continuation.resumeWithException(t)
                }

                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    if(response.isSuccessful){
                        Log.e("BaseOnResponse","isSuccessful");
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