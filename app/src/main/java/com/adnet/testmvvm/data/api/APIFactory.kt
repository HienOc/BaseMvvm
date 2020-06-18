package com.adnet.testmvvm.data.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

object APIFactory {
    fun <T> buildRestApi(
        baseUrl: String,
        restApi: Class<T>,
        okHttpClient: OkHttpClient
    ): T =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(restApi)

    fun <T> buildRestApiXML(
        restApi: Class<T>,
        okHttpClient: OkHttpClient
    ): T =
        Retrofit.Builder()
            .baseUrl(ApiConstants.API_XML_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .build()
            .create(restApi)
}
