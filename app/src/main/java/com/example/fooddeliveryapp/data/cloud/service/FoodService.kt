package com.example.fooddeliveryapp.data.cloud.service

import com.example.fooddeliveryapp.data.cloud.models.FoodResponseCloud
import retrofit2.Response
import retrofit2.http.GET

interface FoodService {

    @GET("aba7ecaa-0a70-453b-b62d-0e326c859b3b")
    suspend fun fetchAllFoods(): Response<FoodResponseCloud>

}