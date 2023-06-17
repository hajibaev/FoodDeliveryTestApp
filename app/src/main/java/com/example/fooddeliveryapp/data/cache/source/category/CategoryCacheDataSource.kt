package com.example.fooddeliveryapp.data.cache.source.category

import com.example.fooddeliveryapp.data.data.models.CategoryData
import kotlinx.coroutines.flow.Flow

interface CategoryCacheDataSource {

    fun fetchAllCategoryFromCacheObservable(): Flow<List<CategoryData>>

    suspend fun fetchAllCategoryFromCacheSingle(): List<CategoryData>

    suspend fun addNewCategoryToCache(category: CategoryData)

    suspend fun fetchCategoryFromId(id: Int): CategoryData

    suspend fun clearTable()
}