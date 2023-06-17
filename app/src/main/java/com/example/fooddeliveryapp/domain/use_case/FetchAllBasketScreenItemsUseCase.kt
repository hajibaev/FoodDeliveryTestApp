package com.example.fooddeliveryapp.domain.use_case

import com.example.fooddeliveryapp.domain.repository.FoodStorageRepository
import com.example.fooddeliveryapp.domain.models.FoodDomain
import kotlinx.coroutines.flow.Flow

interface FetchAllBasketScreenItemsUseCase {

    suspend fun invoke(dishes: FoodDomain)

    suspend fun invoke(dishesId: Int)

    fun invoke(): Flow<List<FoodDomain>>

}

class FetchAllBasketScreenItemsUseCaseImpl(private val repository: FoodStorageRepository) :
    FetchAllBasketScreenItemsUseCase {

    override suspend fun invoke(food: FoodDomain) = repository.save(food = food)

    override suspend fun invoke(dishesId: Int) = repository.delete(dishesId = dishesId)

    override fun invoke(): Flow<List<FoodDomain>> = repository.getStorageDishes()
}