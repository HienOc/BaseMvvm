package com.adnet.testmvvm.di

import com.adnet.testmvvm.ui.home.MainViewModel
import com.adnet.testmvvm.ui.navigation.NavigationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        MainViewModel(get(),get())
    }

    viewModel {
        NavigationViewModel()
    }
}
