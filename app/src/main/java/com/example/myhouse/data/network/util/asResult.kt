package com.example.myhouse.data.network.util

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import okhttp3.ResponseBody

fun <T> Flow<T>.asResult(): Flow<MyHouseResult<T>> {
    return this
        .map<T, MyHouseResult<T>> { MyHouseResult.Success(it) }
        .onStart { emit(MyHouseResult.Loading) }
        .catch { emit(MyHouseResult.Error(it)) }
}

suspend fun <T> Flow<T>.collectAsResult(
    onSuccess: suspend (T) -> Unit = {},
    onError: (Throwable) -> Unit = {},
    onLoading: () -> Unit = {},
) {
    asResult().collect { result ->
        when (result) {
            is MyHouseResult.Success -> onSuccess(result.data)
            is MyHouseResult.Error -> onError(result.exception ?: UnknownError())
            MyHouseResult.Loading -> onLoading()
        }
    }
}

sealed interface MyHouseResult<out T> {
    data class Success<T>(val data: T) : MyHouseResult<T>

    data class Error(
        val exception: Throwable? = null,
        val responseBody: ResponseBody? = null,
    ) : MyHouseResult<Nothing>

    data object Loading : MyHouseResult<Nothing>
}
