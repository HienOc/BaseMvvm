package com.adnet.testmvvm.di

import com.adnet.testmvvm.data.api.APIFactory
import com.adnet.testmvvm.data.api.ApiConstants
import com.adnet.testmvvm.data.api.VideoApi
import com.adnet.testmvvm.data.db.AppDatabase
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {

    single { AppDatabase.getInstance(get()) }

    single {
        GsonConverterFactory.create()
    }

    single {
        ApiConstants.API_BASE_URL
    }

    single {
        APIFactory.buildRestApi(
            get(),
            VideoApi::class.java,
            createOkHttpClient(),
            get()
        )
    }
    single {
        OkHttpClient.Builder()
            .connectTimeout(60L, TimeUnit.SECONDS)
            .readTimeout(60L, TimeUnit.SECONDS)
             .build()
    }
}
fun createOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .connectTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)
        .build()
}



