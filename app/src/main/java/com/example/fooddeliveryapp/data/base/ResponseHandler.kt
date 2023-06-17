package com.example.fooddeliveryapp.data.base

import com.example.fooddeliveryapp.data.cloud.CloudDataRequestState
import retrofit2.Response

interface ResponseHandler {

    suspend fun <T> safeApiCall(
        apiCall: suspend () -> Response<T>,
    ): CloudDataRequestState<T>
}