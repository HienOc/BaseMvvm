package com.adnet.testmvvm.data.api.response

import com.adnet.testmvvm.data.model.Video
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class VideoResponse(
    @SerializedName("data")
    @Expose
    val data: List<Video>,
    @SerializedName("message")
    @Expose
    val message: String,
    @SerializedName("result")
    @Expose
    val result: String
)
