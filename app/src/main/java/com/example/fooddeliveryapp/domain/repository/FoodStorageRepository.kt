package com.example.fooddeliveryapp.domain.repository

import com.example.fooddeliveryapp.domain.models.FoodDomain
import kotlinx.coroutines.flow.Flow

interface FoodStorageRepository {

    suspend fun save(food: FoodDomain)

    suspend fun delete(dishesId: Int)

    fun getStorageDishes(): Flow<List<FoodDomain>>

}