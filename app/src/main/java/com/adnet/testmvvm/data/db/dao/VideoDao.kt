package com.adnet.testmvvm.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.adnet.testmvvm.data.model.Video

@Dao
interface VideoDao {

    @Query("SELECT * FROM $TABLE_NAME")
    fun getAllVideo(): LiveData<List<Video>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(video: Video): Long

    @Delete
    fun remove(video: Video)

    companion object {
        private const val TABLE_NAME = Video.TABLE_NAME
    }
}
