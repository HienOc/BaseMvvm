package com.adnet.testmvvm.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.adnet.testmvvm.coroutine.Results
import com.adnet.testmvvm.data.db.AppDatabase
import com.adnet.testmvvm.data.model.Video
import com.adnet.testmvvm.data.reponsitory.remote.VideoRemoteReponsitory
import com.adnet.testmvvm.ui.base.BaseViewModel
import com.adnet.testmvvm.utils.Constants
import kotlinx.coroutines.launch

class MainViewModel(
    private val videoRemoteReponsitory: VideoRemoteReponsitory, private val appDatabase: AppDatabase
) : BaseViewModel() {

    private val _videoList = MutableLiveData<List<Video>>()
    val videoList: LiveData<List<Video>>
        get() = appDatabase.videoDao().getAllVideo()

    init {
        getVideoRemote()
    }

    fun getVideoRemote() = viewModelScope.launch {

        when (val videoResponse =
            videoRemoteReponsitory.getVideo()) {
            is Results.Success -> {
                appDatabase.videoDao().insertAllAlbum(videoResponse.data.data.toList())
            }
            is Results.Error -> {
                _messenger.postValue(Constants.ERROR_MESSAGE)
            }
        }
    }


    fun deleteVideo(video: Video) = viewModelScope.launch {
        when (appDatabase.videoDao().remove(video)) {
//            is Results.Success -> {
//                _messenger.postValue("OKIEEE")
//            }
//            is Results.Error -> {
//                _messenger.postValue(Constants.ERROR_MESSAGE)
//            }
        }
    }


}
