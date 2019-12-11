package com.adnet.testmvvm.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Video(
    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("name")
    @Expose
    val name: String,
    @SerializedName("uri")
    @Expose
    val uri: String,
    @SerializedName("type")
    @Expose
    val type: String
)
