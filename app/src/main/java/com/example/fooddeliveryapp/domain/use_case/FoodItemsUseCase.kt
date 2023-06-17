package com.example.fooddeliveryapp.domain.use_case

import com.example.fooddeliveryapp.domain.models.FoodDomain
import com.example.fooddeliveryapp.domain.repository.FoodRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn


interface FoodItemsUseCase {


    operator fun invoke(): Flow<List<FoodDomain>>

    operator fun invoke(id: String): Flow<FoodDomain>

}

class FoodItemsUseCaseImpl(private val repository: FoodRepository) :
    FoodItemsUseCase {

    override fun invoke(): Flow<List<FoodDomain>> = repository.fetchAllFoods()


    override fun invoke(id: String): Flow<FoodDomain> =
        repository.fetchAllFoodsObservable(id = id.toInt())
            .flowOn(Dispatchers.IO)

}
