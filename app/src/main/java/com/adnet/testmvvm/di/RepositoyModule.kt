package com.adnet.testmvvm.di

import com.adnet.testmvvm.data.reponsitory.remote.VideoRemoteReponsitory
import org.koin.core.module.Module
import org.koin.dsl.module

val repositoryModule: Module = module {
    single {
        VideoRemoteReponsitory(get())
    }
}
