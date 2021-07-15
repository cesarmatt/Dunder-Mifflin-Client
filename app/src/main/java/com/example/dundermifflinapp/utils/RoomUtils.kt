package com.example.dundermifflinapp.utils

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

suspend inline fun <T : Any> doQuery(
    crossinline query: suspend () -> T?,
    errorMessage: String
): Result<T> {
    return try {
        val data = withContext(Dispatchers.IO) { query() }
        if (data != null) {
            Result.Success(data)
        } else {
            Result.Error(IOException("$errorMessage - returned null"))
        }
    } catch (e: Exception) {
        Log.e("doQuery", e.localizedMessage, e)
        Result.Error(IOException(errorMessage, e))
    }
}