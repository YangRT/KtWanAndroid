package com.example.wanandroid.base

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableArrayList
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.wanandroid.R
import com.f1reking.library.statuslayout.StatusClickListener
import com.f1reking.library.statuslayout.StatusLayout


/**
 * @program: WanAndroid
 *
 * @description: mvvm架构v层基类
 *
 * @author: YangRT
 *
 * @create: 2020-02-15 22:50
 **/

abstract class BaseFragment<D,VM:BaseViewModel<D,BaseMvvmRepository<List<D>>>,T: ViewDataBinding>:Fragment(),
    Observer<Any> {

    protected lateinit var viewModel:VM
    protected lateinit var binding:T
    protected lateinit var statusLayout: StatusLayout


    abstract fun getLayoutId():Int
    abstract fun viewModel():VM
    abstract fun dataInsert(data:ObservableArrayList<D>)
    abstract fun refreshCancel()
    abstract fun isRefreshing():Boolean
    abstract fun fragmentTag():String
    abstract fun init()

    open fun loadMoreFailed(){}
    open fun loadMoreFinished(){}
    open fun loadMoreEmpty(){}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = viewModel()
        lifecycle.addObserver(viewModel)
        retainInstance = true
        binding = DataBindingUtil.inflate(inflater,getLayoutId(),container,false)
        statusLayout = StatusLayout.Builder(binding.root)
                        .setOnLoadingLayout(R.layout.loading)
                        .setOnEmptyLayout(R.layout.empty)
                        .setOnEmptyClickTextColor(Color.parseColor("#03A9F4"))
                        .setOnErrorLayout(R.layout.error)
                        .setOnErrorClickTextColor(Color.parseColor("#03A9F4"))
                        .setOnStatusClickListener(object :StatusClickListener{
                            override fun onEmptyClick(view: View) {
                                viewModel().refresh()
                            }

                            override fun onErrorClick(view: View) {
                                viewModel().refresh()
                            }
                        })
                        .build()

        init()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel().status.observe(this,this)
        viewModel().data.observe(this,
            Observer<ObservableArrayList<D>> { dataInsert(it)})
    }

    override fun onChanged(t: Any) {
        if(t is PageStatus){
            when(t){
                PageStatus.LOADING -> statusLayout.showLoadingLayout()
                PageStatus.SHOW_CONTENT -> {
                    statusLayout.showContentLayout()
                    if(isRefreshing()){
                        Toast.makeText(context,"刷新成功！",Toast.LENGTH_SHORT).show();
                        refreshCancel()
                    }
                    loadMoreFinished()
                }
                PageStatus.EMPTY -> statusLayout.showEmptyLayout()
                PageStatus.NO_MORE_DATA -> loadMoreEmpty()
                PageStatus.LOAD_MORE_FAILED -> loadMoreFailed()
                PageStatus.REFRESH_ERROR -> Toast.makeText(context,"刷新失败！",Toast.LENGTH_SHORT).show()
                PageStatus.REQUEST_ERROR -> Toast.makeText(context,"请求失败,请检查网络！",Toast.LENGTH_SHORT).show();

            }
        }
    }


}