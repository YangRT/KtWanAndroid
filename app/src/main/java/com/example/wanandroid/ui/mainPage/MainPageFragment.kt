package com.example.wanandroid.ui.mainPage

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.example.wanandroid.MyApplication.Companion.context
import com.example.wanandroid.R
import com.example.wanandroid.SplashActivity
import com.example.wanandroid.base.BaseArticleAdapter
import com.example.wanandroid.base.BaseArticleModel
import com.example.wanandroid.base.BaseFragment
import com.example.wanandroid.base.BaseListFragment
import com.example.wanandroid.customview.FigureIndicatorView
import com.example.wanandroid.databinding.FragmentListBinding
import com.example.wanandroid.repository.MainPageRepository
import com.example.wanandroid.ui.mainPage.banner.BannerAdapter
import com.example.wanandroid.ui.mainPage.banner.BannerModel
import com.example.wanandroid.ui.mainPage.banner.BannerViewModel
import com.zhpan.bannerview.BannerViewPager
import com.zhpan.bannerview.adapter.OnPageChangeListenerAdapter
import com.zhpan.bannerview.constants.*
import com.zhpan.bannerview.transform.AccordionTransformer
import java.security.AccessController.getContext


/**
 * @program: WanAndroid
 *
 * @description: "主页" ui
 *
 * @author: YangRT
 *
 * @create: 2020-02-16 15:23
 **/

class MainPageFragment:BaseListFragment<BaseArticleModel,MainPageRepository,MainPageViewModel>() {

    private  var list: MutableList<BaseArticleModel> = ArrayList()
    private val bannerViewModel:BannerViewModel = BannerViewModel()
    private lateinit var headView: LinearLayout
    private lateinit var bannerViewPager:BannerViewPager<BannerModel,BannerAdapter>
    private var bannerList :ArrayList<BannerModel> = ArrayList()

    override fun viewModel(): MainPageViewModel {
        if(viewModel == null){
            viewModel = ViewModelProviders.of(this).get(MainPageViewModel::class.java)
        }
        return viewModel as MainPageViewModel
    }

    override fun dataInsert(data: ObservableArrayList<BaseArticleModel>) {
        adapter.setNewData(data)
    }


    override fun fragmentTag(): String {
        return "mainPage"
    }

    override fun init() {
        headView = LayoutInflater.from(context).inflate(R.layout.banner,null) as LinearLayout
        bannerViewPager = headView.findViewById(R.id.main_page_banner)
        bannerViewPager
            .setCanLoop(false)
            .setIndicatorGravity(IndicatorGravity.END)
            .setAutoPlay(true)
            .setPageStyle(PageStyle.MULTI_PAGE_SCALE)
            .setIndicatorColor(Color.BLACK,Color.WHITE)
            .setIndicatorSlideMode(IndicatorSlideMode.SMOOTH)
            .setIndicatorWidth(14)
            .setIndicatorMargin(0,0,52,12)
            .setScrollDuration(800)
            .setPageMargin(28)
            .setIndicatorColor(Color.BLACK,Color.WHITE)
            .setHolderCreator{BannerAdapter()}
            .create(bannerList)
        bannerViewPager.setPageTransformer(object :AccordionTransformer(){})
        binding.articleRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.mainPageRefreshLayout.setOnRefreshListener {
            viewModel().loadNextPage()
        }
        adapter = BaseArticleAdapter(list)
        adapter.loadMoreModule?.setOnLoadMoreListener {
            viewModel().loadNextPage()
        }
        adapter.loadMoreModule?.isEnableLoadMoreIfNotFullPage = false
        binding.articleRecyclerView.adapter = adapter
        binding.articleRecyclerView.addItemDecoration( DividerItemDecoration(getContext(),
            DividerItemDecoration.VERTICAL))
        adapter.addHeaderView(headView)
        viewModel().getCacheData()
        bannerViewModel.getCacheData()
        bannerViewModel.data.observe(this, Observer {
            bannerList.clear()
            bannerList.addAll(it)
            bannerViewPager.create(bannerList)
        })
    }


}