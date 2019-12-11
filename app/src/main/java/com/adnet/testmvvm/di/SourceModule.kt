package com.adnet.testmvvm.di

import com.adnet.testmvvm.data.source.remote.VideoRemoteDataSource
import com.adnet.testmvvm.utils.KoinNames
import org.koin.core.qualifier.named
import org.koin.dsl.module

val sourceModule = module {

    single(named(KoinNames.VIDEO_REOMTE_DATA_SOURCE)) {
        VideoRemoteDataSource(videoApi = get(named(KoinNames.VIDEO_API)))
    }
}
