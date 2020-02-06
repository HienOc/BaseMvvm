package com.adnet.testmvvm.ui.video

import android.content.pm.PackageManager
import android.content.res.Configuration
import android.os.Build
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.adnet.testmvvm.R
import com.adnet.testmvvm.databinding.ActivityVideoBinding
import com.adnet.testmvvm.ui.base.BaseActivity
import com.adnet.testmvvm.ui.listsvideo.EventTest
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.loadOrCueVideo
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.ui.SharePreference
import kotlinx.android.synthetic.main.activity_video.*
import org.koin.androidx.viewmodel.ext.android.viewModel

@Suppress("DEPRECATION")
class VideoActivity : BaseActivity<ActivityVideoBinding,VideoViewModel>() {

    override val viewModel: VideoViewModel by viewModel()

    private lateinit var youTube: YouTubePlayer

    override val layoutId: Int
        get() = R.layout.activity_video

    override fun initView() {
        initYouTubePlayerView()
        lifecycle.addObserver(youtubePlayerView)

//        val customPlayerUi = youtubePlayerView.inflateCustomPlayerUi(R.layout.custom_player_ui)
//       // initPictureInPicture(youtubePlayerView)
//        youtubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
//            override fun onReady(youTubePlayer: YouTubePlayer) {
//                //customPlayerUi.progressbar.gone()
//                Log.d("Hienfff","MMMMMMM")
//                val customPlayerUiController= CustomPlayerUiController(this@VideoActivity,customPlayerUi,youTubePlayer,youtubePlayerView)
//                youTubePlayer.addListener(customPlayerUiController)
//                youtubePlayerView.addFullScreenListener(customPlayerUiController)
//                youTubePlayer.loadOrCueVideo(lifecycle, SharePreference.MUSIC, 0f)
//            }
//        })
    }

    override fun initListener() {
        //
    }

    private fun initYouTubePlayerView() {
        initPictureInPicture(youtubePlayerView)
        youtubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
               // setPlayNextVideo(youTubePlayer)
                youTube = youTubePlayer
                youTubePlayer.loadOrCueVideo(lifecycle, SharePreference.MUSIC, 0f)
            }

            override fun onStateChange(
                youTubePlayer: YouTubePlayer,
                state: PlayerConstants.PlayerState
            ) {
                super.onStateChange(youTubePlayer, state)
                if(SharePreference.EDIT=="EDIT"){
                    finish()
                    SharePreference.EDIT=" "
                }
                if(SharePreference.CHANGE=="NEXT"){
                    SharePreference.CHANGE=" "
                    setPlayNextVideo(youTube)
                }else{
                    if(SharePreference.CHANGE=="PREVIOUS"){
                        SharePreference.CHANGE=" "
                        setPlayNextVideo(youTube)
                    }
                }
            }
        })
    }

    private fun initPictureInPicture(youTubePlayerView: YouTubePlayerView) {
        val pictureInPictureIcon = ImageView(this)
        pictureInPictureIcon.setImageDrawable(
            ContextCompat.getDrawable(
                this,
                R.drawable.ic_picture_in_picture
            )
        )

        pictureInPictureIcon.setOnClickListener { view: View? ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                val supportsPIP = packageManager.hasSystemFeature(
                    PackageManager.FEATURE_PICTURE_IN_PICTURE
                )
                if (supportsPIP) {
                    SharePreference.CHECK_PICTURE=true
                    enterPictureInPictureMode()

                }
            } else {
                AlertDialog.Builder(this)
                    .setTitle("Can't enter picture in picture mode")
                    .setMessage("In order to enter picture in picture mode you need a SDK version >= N.")
                    .show()
            }
        }

        youTubePlayerView.getPlayerUiController().addView(pictureInPictureIcon)
    }

    override fun onPictureInPictureModeChanged(
        isInPictureInPictureMode: Boolean,
        newConfig: Configuration?
    ) {
        super.onPictureInPictureModeChanged(isInPictureInPictureMode, newConfig)
        if (isInPictureInPictureMode) {
            SharePreference.CHECK_PICTURE=true
            youtubePlayerView.enterFullScreen()
            youtubePlayerView.getPlayerUiController().showUi(false)
        } else {
            SharePreference.CHECK_PICTURE=false
            youtubePlayerView.exitFullScreen()
            youtubePlayerView.getPlayerUiController().showUi(true)
        }
    }

    override fun onStop() {
        super.onStop()
        Log.d("Hiennnn","EEEEEEE")
    }

    override fun eventBus(event: EventTest) {
        super.eventBus(event)
        finish()
//        if( SharePreference.CHECK_PICTURE){
//            youtubePlayerView.exitFullScreen()
//            youtubePlayerView.getPlayerUiController().showUi(true)
//        }
    }

    private fun setPlayNextVideo(youTubePlayer: YouTubePlayer){
        if(SharePreference.CHANGE=="NEXT"){
            youTubePlayer.loadOrCueVideo(lifecycle, "ZAzWT8mRoR0", 0f)
        }
    }
}
