package com.adnet.testmvvm.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.adnet.testmvvm.coroutine.Results
import com.adnet.testmvvm.data.model.Video
import com.adnet.testmvvm.data.reponsitory.local.VideoLocalReponsitory
import com.adnet.testmvvm.data.reponsitory.remote.VideoRemoteReponsitory
import com.adnet.testmvvm.ui.base.BaseViewModel
import com.adnet.testmvvm.utils.Constants
import kotlinx.coroutines.launch

class MainViewModel(
    private val videoRemoteReponsitory: VideoRemoteReponsitory, private val videoLocalReponsitory: VideoLocalReponsitory
) : BaseViewModel() {

    private val _videoList = MutableLiveData<List<Video>>()
    val videoList: LiveData<List<Video>>
        get() = videoLocalReponsitory.getVideo()

    init {
        getVideoRemote()
    }

    fun getVideoRemote() = viewModelScope.launch {

        when (val videoResponse =
            videoRemoteReponsitory.getVideo()) {
            is Results.Success -> {
                addVideo(videoResponse.data.data.toList())
            }
            is Results.Error -> {
                _messenger.postValue(Constants.ERROR_MESSAGE)
            }
        }
    }


    private fun addVideo(videos: List<Video>) = viewModelScope.launch {
        for (i in videos.indices) {
            when (videoLocalReponsitory.insertVideo(videos[i])) {
                is Results.Success -> {
                    _messenger.postValue("OKIEEE")
                }
                is Results.Error -> {
                    _messenger.postValue(Constants.ERROR_MESSAGE)
                }
            }
        }
    }

    fun deleteVideo(video: Video) = viewModelScope.launch {
        when (videoLocalReponsitory.deleteVideo(video)) {
            is Results.Success -> {
                _messenger.postValue("OKIEEE")
            }
            is Results.Error -> {
                _messenger.postValue(Constants.ERROR_MESSAGE)
            }
        }
    }



}
