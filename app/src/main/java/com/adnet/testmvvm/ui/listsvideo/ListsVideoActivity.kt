package com.adnet.testmvvm.ui.listsvideo

import android.content.Intent
import android.util.Log
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.adnet.testmvvm.R
import com.adnet.testmvvm.data.model.VideoYoutube
import com.adnet.testmvvm.databinding.ActivityListsVideoBinding
import com.adnet.testmvvm.ui.base.BaseActivity
import com.adnet.testmvvm.ui.video.VideoActivity
import com.pierfrancescosoffritti.androidyoutubeplayer.core.ui.SharePreference
import kotlinx.android.synthetic.main.activity_lists_video.*
import org.greenrobot.eventbus.EventBus
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListsVideoActivity : BaseActivity<ActivityListsVideoBinding,ListsVideoViewModel>() {

    override val viewModel: ListsVideoViewModel by viewModel()

    private var videoAdapter = VideoAdapter { video: VideoYoutube ->
        SharePreference.MUSIC=video.idVideo
        EventBus.getDefault().post(EventTest("ddd"))
        startActivity(Intent(this,VideoActivity::class.java))
    }

    private var listVideo=mutableListOf<VideoYoutube>()

    override val layoutId: Int
        get() = R.layout.activity_lists_video

    override fun initView() {
        listVideo.apply {
            add(VideoYoutube("ZAzWT8mRoR0","",""))
            add(VideoYoutube("XyzaMpAVm3s","",""))
            add(VideoYoutube("fTc5tuEn6_U","",""))
            add(VideoYoutube("XyzaMpAVm3s","",""))
        }
        recyclerViewMain.apply {
            layoutManager = StaggeredGridLayoutManager(
                1,
                StaggeredGridLayoutManager.VERTICAL
            )
            setHasFixedSize(true)
            adapter = videoAdapter
        }
        videoAdapter.submitList(listVideo)
        Log.d("Hiennnn","DDDDDD")
        SharePreference.CHECK_PICTURE= false
    }

    override fun initListener() {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
