package com.adnet.testmvvm.data.reponsitory.remote

import com.adnet.testmvvm.coroutine.Results
import com.adnet.testmvvm.data.api.VideoApi
import com.adnet.testmvvm.data.api.response.VideoResponse
import com.adnet.testmvvm.data.reponsitory.VideoDataSource
import com.adnet.testmvvm.coroutine.safeApiCall
import com.adnet.testmvvm.coroutine.safeApiResult

class VideoRemoteReponsitory(
    private val videoApi: VideoApi
) : VideoDataSource.Remote{

    override suspend fun getVideo(): Results<VideoResponse> =
        safeApiCall(
            call = {
                safeApiResult(
                    call = { videoApi.getVideoAsync() }
                )
            }
        )
}
