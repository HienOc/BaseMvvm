package com.adnet.testmvvm.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    protected val _messenger = MutableLiveData<String>()
    val messenger: LiveData<String>
        get() = _messenger
}
