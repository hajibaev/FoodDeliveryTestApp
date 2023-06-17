package com.example.fooddeliveryapp.domain.use_case

import com.example.fooddeliveryapp.domain.models.FoodDomain
import com.example.fooddeliveryapp.domain.repository.FoodStorageRepository

interface SaveFoodForBasketUseCase {

    suspend operator fun invoke(food: FoodDomain)

}

class SaveFoodForBasketUseCaseImpl(private val repository: FoodStorageRepository) :
    SaveFoodForBasketUseCase {

    override suspend fun invoke(food: FoodDomain) = repository.save(food)

}

