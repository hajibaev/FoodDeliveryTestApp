package com.example.fooddeliveryapp.data.data.repository

import com.example.fooddeliveryapp.data.cache.models.FoodCache
import com.example.fooddeliveryapp.data.cache.source.food.FoodCacheDataSource
import com.example.fooddeliveryapp.data.cloud.source.food.FoodDataSource
import com.example.fooddeliveryapp.data.cloud.takeSuccess
import com.example.fooddeliveryapp.data.data.models.FoodData
import com.example.fooddeliveryapp.domain.DispatchersProvider
import com.example.fooddeliveryapp.domain.Mapper
import com.example.fooddeliveryapp.domain.models.FoodDomain
import com.example.fooddeliveryapp.domain.repository.FoodRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class FoodRepositoryImpl(
    private val cacheDataSource: FoodCacheDataSource,
    private val source: FoodDataSource,
    private val dispatchersProvider: DispatchersProvider,
    private val mapFromFoodDataToDomain: Mapper<FoodData, FoodDomain>,
    private val mapFromFoodCacheToData: Mapper<FoodCache, FoodData>,
) : FoodRepository {

    override fun fetchAllFoods(): Flow<List<FoodDomain>> = flow {
        emit(cacheDataSource.fetchAllFoodsFromCacheSingle())
    }.flatMapLatest { handleFetchDishesInCache(it) }
        .map { foods -> foods.map(mapFromFoodDataToDomain::map) }
        .flowOn(dispatchersProvider.default())

    override fun fetchAllFoodsObservable(id: Int): Flow<FoodDomain> =
        cacheDataSource.fetchFoodsFromId(id = id).map { dishesFromCache ->
            if (dishesFromCache == null) source.fetchFoodsById(id = id)
                .takeSuccess() else mapFromFoodCacheToData.map(dishesFromCache)
        }.map { it ?: FoodData.unknown() }
            .map(mapFromFoodDataToDomain::map)
            .flowOn(dispatchersProvider.default())


    private fun handleFetchDishesInCache(
        cachedDishes: List<FoodData>,
    ) = if (cachedDishes.isEmpty())
        source.fetchFoods().onEach { dishes ->
            dishes.forEach { cacheDataSource.addNewFoodsToCache(it) }
        }
    else cacheDataSource.fetchAllFoodsFromCacheObservable()

}
