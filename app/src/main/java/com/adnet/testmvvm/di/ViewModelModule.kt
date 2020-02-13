package com.adnet.testmvvm.di

import com.adnet.testmvvm.ui.home.MainViewModel
import com.adnet.testmvvm.ui.video.VideoViewModel
import com.adnet.testmvvm.ui.listsvideo.ListsVideoViewModel
import com.adnet.testmvvm.ui.navigation.NavigationViewModel
import com.adnet.testmvvm.ui.panel.PanelViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        MainViewModel(get(),get())
    }
    viewModel {
        VideoViewModel()
    }
    viewModel {
        ListsVideoViewModel()
    }
    viewModel {
        PanelViewModel()
    }
    viewModel {
        NavigationViewModel()
    }
}
