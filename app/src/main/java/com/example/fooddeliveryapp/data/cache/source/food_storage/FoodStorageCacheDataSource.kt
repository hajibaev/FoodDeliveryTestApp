package com.example.fooddeliveryapp.data.cache.source.food_storage

import com.example.fooddeliveryapp.data.data.models.FoodData
import kotlinx.coroutines.flow.Flow

interface FoodStorageCacheDataSource {

    suspend fun save(dishes: FoodData)

    suspend fun delete(dishesId: Int)

    fun getAlleForBasket(): Flow<List<FoodData>>

}