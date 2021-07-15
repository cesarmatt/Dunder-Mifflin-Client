package com.example.dundermifflinapp.utils

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.io.IOException

suspend fun <T : Any> safeApiCall(call: suspend () -> Result<T>, errorMessage: String): Result<T> {
    return try {
        call()
    } catch (e: Exception) {
        Log.e("SafeApiCall", e.localizedMessage, e)
        Result.Error(IOException(errorMessage, e))
    }
}

suspend inline fun <T : Any> networkCall(
    crossinline call: suspend () -> Response<T>,
    errorMessage: String
): Result<T> {
    try {
        val response = withContext(Dispatchers.IO) { call() }
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                return Result.Success(body)
            }
        }

        val exception = IOException("$errorMessage ${response.code()} ${response.message()}")
        Log.e("NetworkCall", exception.localizedMessage, exception)

        return Result.Error(exception)
    } catch (e: Exception) {
        Log.e("NetworkCall", e.localizedMessage, e)
        return Result.Error(Exception(errorMessage, e))
    }
}
