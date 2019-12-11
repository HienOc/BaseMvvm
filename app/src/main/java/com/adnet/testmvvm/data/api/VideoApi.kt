package com.adnet.testmvvm.data.api

import com.adnet.testmvvm.data.api.response.VideoResponse
import retrofit2.Response
import retrofit2.http.GET

interface VideoApi {
    @GET(BACKGROUND)
    suspend fun getAirCurrentAsync(): Response<VideoResponse>
}
