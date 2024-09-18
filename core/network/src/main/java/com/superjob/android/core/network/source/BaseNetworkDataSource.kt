package com.superjob.android.core.network.source

import retrofit2.Response
import com.superjob.android.core.common.Result
import com.superjob.android.core.common.emptyIfNull

abstract class BaseNetworkDataSource {
    suspend fun <T> getResult(call: suspend () -> Response<T>): Result<T> {
        return try {
            val response = call()
            val body = response.body()
            if (response.isSuccessful && body != null) {
                Result.Success(body)
            } else if (response.errorBody() != null) {
                Result.Error(response.message())
            }
            else {
                Result.Error("Unknown error")
            }
        } catch (e: Exception) {
            Result.Error(e.message.emptyIfNull(e.toString()))
        }
    }
}