package com.adnet.testmvvm.di

import com.adnet.testmvvm.data.reponsitory.VideoRemoteReponsitory
import com.adnet.testmvvm.utils.KoinNames
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

val repositoryModule: Module = module {

    single(named(KoinNames.VIDEO_REOMTE_REPOSITORY)) {
        VideoRemoteReponsitory(videoDataSource = get(named(KoinNames.VIDEO_REOMTE_DATA_SOURCE)))
    }
}
