package com.adnet.testmvvm.coroutine

sealed class ResultCoroutine<out T : Any> {
    data class Success<out T : Any>(val data: T) : ResultCoroutine<T>()
    data class Error(val exception: Exception) : ResultCoroutine<Nothing>()
}
