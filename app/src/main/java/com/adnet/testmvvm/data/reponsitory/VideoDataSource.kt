package com.adnet.testmvvm.data.reponsitory

import androidx.lifecycle.LiveData
import com.adnet.testmvvm.coroutine.Results
import com.adnet.testmvvm.data.api.response.VideoResponse
import com.adnet.testmvvm.data.model.Video

interface VideoDataSource {
    interface Remote {
        suspend fun getVideo(): Results<VideoResponse>
    }
    interface Local{
        fun getVideo(): LiveData<List<Video>>
        fun insertVideo(video: Video): Results<Video>
        fun deleteVideo(video: Video): Results<Video>
    }
}
