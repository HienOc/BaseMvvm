package com.adnet.testmvvm.data.reponsitory

import com.adnet.testmvvm.coroutine.ResultCoroutine
import com.adnet.testmvvm.data.api.response.VideoResponse
import com.adnet.testmvvm.data.source.VideoDataSource

class VideoRemoteReponsitory(
    private val videoDataSource: VideoDataSource.Remote
) : VideoDataSource.Remote {
    override suspend fun getVideo(): ResultCoroutine<VideoResponse> = videoDataSource.getVideo()
}
