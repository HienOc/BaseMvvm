package com.adnet.testmvvm.ui.home

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.adnet.testmvvm.R
import com.adnet.testmvvm.data.model.Video
import com.adnet.testmvvm.databinding.ActivityMainBinding
import com.adnet.testmvvm.ui.base.BaseActivity
import isNetworkAvailable
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override val viewModel: MainViewModel by viewModel()

    override val layoutId: Int
        get() = R.layout.activity_main

    private var mainAdapter = MainAdapter { video: Video ->
        displayVideo(video)
    }

    override fun checkInternet(): Boolean = isNetworkAvailable(this)

    override fun initView() {
        recyclerViewMain.apply {
            layoutManager = StaggeredGridLayoutManager(
                1,
                StaggeredGridLayoutManager.VERTICAL
            )
            setHasFixedSize(true)
            adapter = mainAdapter
        }
    }

    override fun setupPermissions() {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initListener() {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun observeViewModel() {
        viewModel.videoList.observe(this, Observer {
            mainAdapter.submitList(it)
        })
    }

    private fun displayVideo(video: Video) {
        Toast.makeText(this, video.name, Toast.LENGTH_LONG).show()
    }
}
