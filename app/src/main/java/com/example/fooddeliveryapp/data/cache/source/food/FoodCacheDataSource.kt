package com.example.fooddeliveryapp.data.cache.source.food

import com.example.fooddeliveryapp.data.cache.models.FoodCache
import com.example.fooddeliveryapp.data.data.models.FoodData
import kotlinx.coroutines.flow.Flow

interface FoodCacheDataSource {

    fun fetchAllFoodsFromCacheObservable(): Flow<List<FoodData>>

    suspend fun fetchAllFoodsFromCacheSingle(): List<FoodData>

    suspend fun addNewFoodsToCache(dishes: FoodData)

    fun fetchFoodsFromId(id: Int): Flow<FoodCache?>

    suspend fun clearTable()
}