package com.adnet.testmvvm.di

import android.annotation.SuppressLint
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

@SuppressLint("ConstantLocale")
val appModule = module {
    single {
        androidApplication().resources
    }

}
val appModules = listOf(appModule, repositoryModule, networkModule, viewModelModule)
