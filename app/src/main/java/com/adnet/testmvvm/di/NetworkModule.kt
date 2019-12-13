package com.adnet.testmvvm.di

import com.adnet.testmvvm.data.api.APIFactory
import com.adnet.testmvvm.data.api.ApiConstants
import com.adnet.testmvvm.data.api.VideoApi
import com.adnet.testmvvm.data.db.AppDatabase
import org.koin.dsl.module
import retrofit2.converter.gson.GsonConverterFactory

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
            get()
        )
    }
}


