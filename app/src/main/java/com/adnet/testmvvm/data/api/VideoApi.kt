package com.adnet.testmvvm.data.api

import com.adnet.testmvvm.data.api.response.VideoResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface VideoApi {
    @GET(BACKGROUND)
    fun getAirCurrentAsync(
    ): Deferred<Response<VideoResponse>>
}
