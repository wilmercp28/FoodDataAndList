package com.wilmer.fooddataandlist.data.remote

import com.wilmer.fooddataandlist.data.model.Resource
import retrofit2.Response
import timber.log.Timber

abstract class ApiResponse {

    protected suspend fun <T> safeApiCall(
        apiCall: suspend () -> Response<T>
    ): Resource<T> {
        return try {
            Timber.tag("ApiResponse").d("Checking API call...")
            val response = apiCall()
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    Resource.Success(it)
                } ?: Resource.Error("Response body is null")
            } else {
                Resource.Error("${response.code()} ${response.message()}")
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: e.toString())
        }
    }
}
