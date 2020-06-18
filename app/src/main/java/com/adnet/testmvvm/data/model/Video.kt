package com.adnet.testmvvm.data.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = Video.TABLE_NAME)

data class Video(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = ID)
    @SerializedName("id")
    @Expose
    val id: Int,
    @NonNull
    @ColumnInfo(name = NAME)
    @SerializedName("name")
    @Expose
    val name: String,
    @NonNull
    @ColumnInfo(name = URI)
    @SerializedName("uri")
    @Expose
    val uri: String,
    @NonNull
    @ColumnInfo(name = TYPE)
    @SerializedName("type")
    @Expose
    val type: String
){
    companion object {
        const val TABLE_NAME = "video"
        const val ID = "video_id"
        const val NAME = "video_name"
        const val URI = "video_uri"
        const val TYPE = "video_type"
    }
}
