package com.example.fooddeliveryapp.data.data.repository

import com.example.fooddeliveryapp.data.cache.source.food_storage.FoodStorageCacheDataSource
import com.example.fooddeliveryapp.domain.repository.FoodStorageRepository
import com.example.fooddeliveryapp.data.data.models.FoodData
import com.example.fooddeliveryapp.domain.Mapper
import com.example.fooddeliveryapp.domain.models.FoodDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class DishesStorageRepositoryImpl(
    private val source: FoodStorageCacheDataSource,
    private val mapFromFoodDomainToData: Mapper<FoodDomain, FoodData>,
    private val mapFromFoodDataToDomain: Mapper<FoodData, FoodDomain>
) : FoodStorageRepository {

    override suspend fun save(food: FoodDomain) =
        withContext(Dispatchers.IO) {
            source.save(mapFromFoodDomainToData.map(food))
        }

    override suspend fun delete(dishesId: Int) = source.delete(dishesId)

    override fun getStorageDishes(): Flow<List<FoodDomain>> =
        source.getAlleForBasket().map { dishes ->
            dishes.map(mapFromFoodDataToDomain::map)
        }

}