package com.adnet.testmvvm.data.source.remote

import com.adnet.testmvvm.coroutine.ApiResultHandler
import com.adnet.testmvvm.coroutine.ResultCoroutine
import com.adnet.testmvvm.data.api.VideoApi
import com.adnet.testmvvm.data.api.response.VideoResponse
import com.adnet.testmvvm.data.source.VideoDataSource
import com.adnet.testmvvm.utils.Constants

class VideoRemoteDataSource(
    private val videoApi: VideoApi
) : VideoDataSource.Remote,
    ApiResultHandler() {
    override suspend fun getVideo(): ResultCoroutine<VideoResponse> = safeApiResult(
        call = { videoApi.getAirCurrentAsync() },
        errorMessage = Constants.ERROR_MESSAGE
    )
}
