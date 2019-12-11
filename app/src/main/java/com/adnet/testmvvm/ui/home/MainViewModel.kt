package com.adnet.testmvvm.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.adnet.testmvvm.coroutine.ResultCoroutine
import com.adnet.testmvvm.data.model.Video
import com.adnet.testmvvm.data.reponsitory.VideoRemoteReponsitory
import com.adnet.testmvvm.ui.base.BaseViewModel
import com.adnet.testmvvm.utils.Constants
import kotlinx.coroutines.launch

class MainViewModel(
    private val videoRemoteReponsitory: VideoRemoteReponsitory
) : BaseViewModel() {

    private val _videoList = MutableLiveData<List<Video>>()
    val videoList: LiveData<List<Video>>
        get() = _videoList

    init {
        getVideo()
    }

    private fun getVideo() = viewModelScope.launch {

        when (val videoResponse =
            videoRemoteReponsitory.getVideo()) {
            is ResultCoroutine.Success -> {
                _videoList.postValue(videoResponse.data.data)
            }
            is ResultCoroutine.Error -> {
                _messenger.postValue(Constants.ERROR_MESSAGE)
            }
        }
    }

}
