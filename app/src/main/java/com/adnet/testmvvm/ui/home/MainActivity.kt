package com.adnet.testmvvm.ui.home

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.adnet.testmvvm.R
import com.adnet.testmvvm.databinding.ActivityMainBinding
import com.adnet.testmvvm.ui.base.BaseActivity
import isNetworkAvailable
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding,MainViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override val viewModel: MainViewModel by viewModel()
    override val layoutId: Int
        get() = R.layout.activity_main

    override fun observeViewModel() {
        viewModel.videoList.observe(this, Observer {
            text.text= it.toString()
        })
    }

    override fun initView() {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun checkInternet(): Boolean = isNetworkAvailable(this)

    override fun setupPermissions() {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initListener() {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
