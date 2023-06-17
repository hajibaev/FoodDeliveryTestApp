package com.example.fooddeliveryapp.data.cloud.source.food

import com.example.fooddeliveryapp.data.data.models.FoodData
import com.example.fooddeliveryapp.data.cloud.CloudDataRequestState
import kotlinx.coroutines.flow.Flow

interface FoodDataSource {

    fun fetchFoods(): Flow<List<FoodData>>

    suspend fun fetchFoodsById(id: Int): CloudDataRequestState<FoodData>

}