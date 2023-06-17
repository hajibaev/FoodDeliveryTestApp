package com.example.fooddeliveryapp.data.cache.source.food

import com.example.fooddeliveryapp.data.cache.db.FoodDao
import com.example.fooddeliveryapp.data.cache.models.FoodCache
import com.example.fooddeliveryapp.data.data.models.FoodData
import com.example.fooddeliveryapp.domain.DispatchersProvider
import com.example.fooddeliveryapp.domain.Mapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class FoodCacheDataSourceImpl(
    private val dao: FoodDao,
    private val dispatchersProvider: DispatchersProvider,
    private val mapFromFoodCacheToData: Mapper<FoodCache, FoodData>,
    private val mapFromFoodDataToCache: Mapper<FoodData, FoodCache>,
) : FoodCacheDataSource {

    override fun fetchAllFoodsFromCacheObservable(): Flow<List<FoodData>> =
        dao.fetchAllDishesObservable()
            .flowOn(dispatchersProvider.io())
            .map { dishes -> dishes.map(mapFromFoodCacheToData::map) }
            .flowOn(dispatchersProvider.default())

    override suspend fun fetchAllFoodsFromCacheSingle(): List<FoodData> {
        val cachedList = dao.fetchAllDishesSingle()
        return cachedList.map(mapFromFoodCacheToData::map)
    }

    override suspend fun addNewFoodsToCache(dishes: FoodData) =
        dao.addNewDishes(mapFromFoodDataToCache.map(dishes))


    override fun fetchFoodsFromId(id: Int): Flow<FoodCache> =
        dao.fetchDishesFromId(id)
            .flowOn(dispatchersProvider.io())

    override suspend fun clearTable() = dao.clearTable()
}