package com.adnet.testmvvm.di

import android.annotation.SuppressLint
import com.adnet.testmvvm.data.api.APIFactory
import com.adnet.testmvvm.data.api.ApiConstants
import com.adnet.testmvvm.data.api.VideoApi
import com.adnet.testmvvm.utils.KoinNames
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import org.koin.android.ext.koin.androidApplication
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.converter.gson.GsonConverterFactory

@SuppressLint("ConstantLocale")
val appModule = module {
    single { androidApplication().resources }
    single(named(KoinNames.COROUTINE_CALL_ADAPTER_FACTORY)) {
        CoroutineCallAdapterFactory()
    }

    single(named(KoinNames.GSON_CONVERTER_FACTORY)) {
        GsonConverterFactory.create()
    }


    single(named(KoinNames.API_BASE_URL)) {
        ApiConstants.API_BASE_URL
    }

    single(named(KoinNames.VIDEO_API)) {
        APIFactory.buildRestApi(
            baseUrl = get(named(KoinNames.API_BASE_URL)),
            restApi = VideoApi::class.java,
            converterFactory = get(named(KoinNames.GSON_CONVERTER_FACTORY))
        )
    }
}
