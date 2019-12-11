package com.adnet.testmvvm.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIFactory {
    fun <T> buildRestApi(
        baseUrl: String,
        restApi: Class<T>,
        converterFactory: GsonConverterFactory
    ): T =
        Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(converterFactory)
            .build()
            .create(restApi)
}
