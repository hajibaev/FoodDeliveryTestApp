package com.example.fooddeliveryapp.domain.repository

import com.example.fooddeliveryapp.domain.models.FoodDomain
import kotlinx.coroutines.flow.Flow

interface FoodRepository {

    fun fetchAllFoods(): Flow<List<FoodDomain>>

    fun fetchAllFoodsObservable(id: Int): Flow<FoodDomain>


}