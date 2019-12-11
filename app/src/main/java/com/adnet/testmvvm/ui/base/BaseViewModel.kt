package com.adnet.testmvvm.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel(), CoroutineScope {

    protected val _messenger = MutableLiveData<String>()
    val messenger: LiveData<String>
        get() = _messenger

    private val parentJob = Job()

    override val coroutineContext: CoroutineContext = parentJob + Dispatchers.Default

    fun cancelAllRequests() = coroutineContext.cancel()
}
