package com.adnet.testmvvm.data.reponsitory.local

import android.util.Log
import com.adnet.testmvvm.coroutine.Results
import com.adnet.testmvvm.data.db.AppDatabase
import com.adnet.testmvvm.data.model.Video
import com.adnet.testmvvm.data.reponsitory.VideoDataSource

class VideoLocalReponsitory(private val appDatabase: AppDatabase) : VideoDataSource.Local {
    override fun getVideo()= appDatabase.videoDao().getAllVideo()

    override fun insertVideo(video: Video): Results<Video> = try {
        appDatabase.videoDao().insert(video)
        Results.Success(video)
    } catch (e: Exception) {
        Log.d("Hien",e.toString())
        Results.Error(e)
    }

    override fun deleteVideo(video: Video): Results<Video> = try {
        appDatabase.videoDao().remove(video)
        Results.Success(video)
    } catch (e: Exception) {
        Results.Error(e)
    }
}
