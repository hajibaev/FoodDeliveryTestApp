package com.example.fooddeliveryapp.data.cloud.service

import com.example.fooddeliveryapp.data.cloud.models.FoodCategoryResponseCloud
import retrofit2.Response
import retrofit2.http.GET

interface CategotyService {

    @GET("058729bd-1402-4578-88de-265481fd7d54")
    suspend fun getFoodCategory(
    ): Response<FoodCategoryResponseCloud>


}