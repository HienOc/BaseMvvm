package com.adnet.testmvvm.data.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIFactory {
    fun <T> buildRestApi(
        baseUrl: String,
        restApi: Class<T>,
        okHttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory
    ): T =
        Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(converterFactory)
            .client(okHttpClient)
            .build()
            .create(restApi)
}
