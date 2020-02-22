package com.example.wanandroid.ui.square.share

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.wanandroid.R
import com.example.wanandroid.databinding.FragmentShareBinding


/**
 * @program: WanAndroid
 *
 * @description: 分享文章 ui
 *
 * @author: YangRT
 *
 * @create: 2020-02-22 16:34
 **/

class ShareArticleFragment:Fragment(),View.OnClickListener{


    private lateinit var binding:FragmentShareBinding
    private lateinit var viewModel: ShareViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_share,container,false)
        viewModel = ViewModelProviders.of(this).get(ShareViewModel::class.java)
        binding.btShare.setOnClickListener(this)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.shareResponse.observe(this, Observer {
            if (it == 1){
                activity?.finish()
            }
        })
    }
    override fun onClick(v: View?) {
        if (v?.id == R.id.bt_share){
            if(binding.shareLink.text.toString() == "" || binding.shareTitle.text.toString() == ""){
                Toast.makeText(getContext(),"请填齐信息！",Toast.LENGTH_SHORT).show();
            }else {
                viewModel.shareArticle(binding.shareTitle.text.toString(),binding.shareLink.text.toString());
            }
        }
    }
}