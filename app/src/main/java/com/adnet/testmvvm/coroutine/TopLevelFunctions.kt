package com.adnet.testmvvm.coroutine

import retrofit2.Response
import java.io.IOException

suspend fun <T : Any> safeApiCall(call: suspend () -> Results<T>): Results<T> = try {
    call.invoke()
} catch (e: Exception) {
    Results.Error(IOException(e.message, e))
}

suspend fun <T : Any> safeApiResult(
    call: suspend () -> Response<T>
): Results<T> {
    val response = call.invoke()
    if (response.isSuccessful) {
        response.body()?.let {
            return Results.Success(it)
        }
    }
    return Results.Error(IOException(response.code().toString()))
}

val <T> T.exhaustive: T get() = this
