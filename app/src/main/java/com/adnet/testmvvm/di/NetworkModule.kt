package com.adnet.testmvvm.di

import com.adnet.testmvvm.data.api.APIFactory
import com.adnet.testmvvm.data.api.ApiConstants
import com.adnet.testmvvm.data.api.VideoApi
import com.adnet.testmvvm.data.db.AppDatabase
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {

    single { AppDatabase.getInstance(get()) }

    single {
        GsonConverterFactory.create()
    }

    single { createOkHttpClient() }

    single {
        ApiConstants.API_BASE_URL
    }

    single {
        APIFactory.buildRestApi(
            get(),
            VideoApi::class.java,
            get()
        )
    }
//    single {
//        APIFactory.buildRestApiXML(
//            IyashiLanguage::class.java,
//            get()
//        )
//    }
}

fun createOkHttpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    return OkHttpClient.Builder()
        .connectTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)
        /*.addInterceptor(httpLoggingInterceptor)*/.build()
}


