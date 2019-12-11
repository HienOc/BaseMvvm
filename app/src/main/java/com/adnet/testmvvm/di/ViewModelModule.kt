package com.adnet.testmvvm.di

import com.adnet.testmvvm.ui.home.MainViewModel
import com.adnet.testmvvm.utils.KoinNames
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        MainViewModel(
            videoRemoteReponsitory = get(named(KoinNames.VIDEO_REOMTE_REPOSITORY))
        )
    }
}
