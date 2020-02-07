package com.adnet.testmvvm.ui.listsvideo

import android.content.Intent
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

class ListsVideoActivity : BaseActivity<ActivityListsVideoBinding, ListsVideoViewModel>() {

    override val viewModel: ListsVideoViewModel by viewModel()

    override val layoutId: Int
        get() = R.layout.activity_lists_video

    private var videoAdapter = VideoAdapter { video: VideoYoutube ->
        SharePreference.MUSIC = video.idVideo.toString()
        EventBus.getDefault().post(EventTest("ddd"))
        val intent = Intent(this, VideoActivity::class.java)
        intent.putParcelableArrayListExtra("video", listVideo)
        startActivity(intent)
    }

    private val listVideo = arrayListOf<VideoYoutube>()

    override fun initView() {
        listVideo.apply {
            add(VideoYoutube("ZAzWT8mRoR0", "video 1", "33"))
            add(VideoYoutube("XyzaMpAVm3s", "video 2", "33"))
            add(VideoYoutube("fTc5tuEn6_U", "video 3", "33"))
            add(VideoYoutube("XyzaMpAVm3s", "video 4", "33"))
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
        SharePreference.CHECK_PICTURE = false
    }

    override fun initListener() {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
