package com.example.wanandroid.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ObservableArrayList
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer


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
    Observer<VM> {

    protected lateinit var viewModel:VM
    protected lateinit var binding:T

    abstract fun getLayoutId():Int
    abstract fun viewModel():VM
    abstract fun dataInsert(data:ObservableArrayList<D>)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        lifecycle.addObserver(viewModel)
        return super.onCreateView(inflater, container, savedInstanceState)
    }


}