package com.adnet.testmvvm.data.source

import com.adnet.testmvvm.coroutine.ResultCoroutine
import com.adnet.testmvvm.data.api.response.VideoResponse

interface VideoDataSource {
    interface Remote {
        suspend fun getVideo(): ResultCoroutine<VideoResponse>
    }
}
