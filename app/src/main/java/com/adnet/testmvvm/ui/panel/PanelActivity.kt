package com.adnet.testmvvm.ui.panel

import android.util.Log
import android.view.View
import android.view.animation.TranslateAnimation
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.adnet.testmvvm.R
import com.adnet.testmvvm.data.model.Video
import com.adnet.testmvvm.databinding.ActivityPanelBinding
import com.adnet.testmvvm.ui.base.BaseActivity
import com.adnet.testmvvm.ui.home.MainAdapter
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.SharePreference
import kotlinx.android.synthetic.main.activity_panel.*
import kotlinx.android.synthetic.main.expansion_panel_sample_panel.*
import org.greenrobot.eventbus.Subscribe
import org.koin.androidx.viewmodel.ext.android.viewModel
import setSafeOnClickListener

class PanelActivity : BaseActivity<ActivityPanelBinding, PanelViewModel>() {

    override val viewModel: PanelViewModel by viewModel()

    override val layoutId: Int
        get() = R.layout.activity_panel

    private var list = mutableListOf<Video>().apply {
        add(Video(1, "Video 1", "url", "hiazzz"))
        add(Video(2, "Video 2", "url", "hiazzz"))
        add(Video(3, "Video 3", "url", "hiazzz"))
        add(Video(4, "Video 4", "url", "hiazzz"))
        add(Video(5, "Video 5", "url", "hiazzz"))
        add(Video(6, "Video 6", "url", "hiazzz"))
    }

    private var listDelete = mutableListOf<Video>()

    private var mainAdapter = MainAdapter { video: Video ->
        loadUi()
    }

    private fun loadUi() {
        imageViewClear.visibility = View.VISIBLE
        linearLayoutDelete.visibility = View.VISIBLE

        val translateYAnimation = TranslateAnimation(
            0f,
            0f,
            100f,
            0f
        ).apply {
            duration = 500
        }

        linearLayoutDelete.startAnimation(translateYAnimation)
        imageViewClear.startAnimation(translateYAnimation)
        mainAdapter.notifyDataSetChanged()
    }

    override fun initView() {


        SharePreference.CHECK_ITEM = false
        recyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(
                1,
                StaggeredGridLayoutManager.VERTICAL
            )
            setHasFixedSize(true)
            adapter = mainAdapter
        }
        mainAdapter.submitList(list)

        imageViewClear.setSafeOnClickListener {
            SharePreference.CHECK_ITEM = false
            imageViewClear.visibility = View.GONE
            linearLayoutDelete.visibility = View.GONE
            listDelete.clear()
            mainAdapter.notifyDataSetChanged()
        }

        linearLayoutDelete.setSafeOnClickListener {
            SharePreference.CHECK_ITEM = false
            imageViewClear.visibility = View.GONE
            linearLayoutDelete.visibility = View.GONE
            if(listDelete.isNotEmpty()){
                for (i in 0 until listDelete.size) {
                    list.remove(listDelete[i])
                }
                mainAdapter.apply {
                    submitList(list)
                }
            }
            mainAdapter.notifyDataSetChanged()
        }

    }

    @Subscribe
    fun eventClick(event: Video) {
        Log.d("hiheihe", "ghuhuh")
        if (SharePreference.CHECK_STATUS) {
            listDelete.add(event)
        } else {
            listDelete.remove(event)
        }
    }

    override fun initListener() {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
