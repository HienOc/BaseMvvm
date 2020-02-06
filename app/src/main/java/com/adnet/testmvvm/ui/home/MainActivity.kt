package com.adnet.testmvvm.ui.home

import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import assignViews
import com.adnet.testmvvm.R
import com.adnet.testmvvm.data.model.Video
import com.adnet.testmvvm.databinding.ActivityMainBinding
import com.adnet.testmvvm.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(), View.OnClickListener,
    SwipeRefreshLayout.OnRefreshListener {

    override val viewModel: MainViewModel by viewModel()

    override val layoutId: Int
        get() = R.layout.activity_main

    private var mainAdapter = MainAdapter { video: Video ->
        showReminderRemoveAlert(video)
    }

    override fun initView() {
        recyclerViewMain.apply {
            layoutManager = StaggeredGridLayoutManager(
                1,
                StaggeredGridLayoutManager.VERTICAL
            )
            setHasFixedSize(true)
            adapter = mainAdapter
        }

        swipeRefreshLayout.setOnRefreshListener(this)
    }

    override fun initListener() {
        assignViews(imageButtonNext)
    }

    override fun observeViewModel() {
        viewModel.videoList.observe(this, Observer {
            mainAdapter.submitList(it)
            swipeRefreshLayout.isRefreshing = false
        })
    }

    private fun showReminderRemoveAlert(video: Video) {
        val alertDialog = AlertDialog.Builder(this).create()
        alertDialog.run {
            setMessage("Xoa ban ghi")
            setButton(AlertDialog.BUTTON_POSITIVE,
                "Ok") { dialog, _ ->
                removeReminder(video)
                dialog.dismiss()
            }
            setButton(AlertDialog.BUTTON_NEGATIVE,
               "Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            show()
        }
    }
    
    private fun removeReminder(video: Video) {
        viewModel.deleteVideo(video)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.imageButtonNext -> {

            }
        }
    }

    override fun onRefresh() {
       viewModel.getVideoRemote()
    }
}
