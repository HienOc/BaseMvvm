package com.adnet.testmvvm.coroutine

import retrofit2.Response
import java.io.IOException

open class ApiResultHandler {

    suspend fun <T : Any> safeApiResult(
        call: suspend () -> Response<T>,
        errorMessage: String
    ): ResultCoroutine<T> {
        val response = call.invoke()
        if (response.isSuccessful) {
            response.body()?.let {
                return ResultCoroutine.Success(it)
            }
        }
        return ResultCoroutine.Error(IOException("Error Occurred during getting safe Api result, Custom ERROR - $errorMessage"))
    }
}
