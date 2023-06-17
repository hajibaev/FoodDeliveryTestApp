package com.example.fooddeliveryapp.data.cache.source.food_storage

import com.example.fooddeliveryapp.data.cache.db.FoodStorageDao
import com.example.fooddeliveryapp.data.cache.models.FoodStorageCache
import com.example.fooddeliveryapp.data.data.models.FoodData
import com.example.fooddeliveryapp.domain.DispatchersProvider
import com.example.fooddeliveryapp.domain.Mapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class FoodStorageCacheDataSourceImpl(
    private val dao: FoodStorageDao,
    private val dispatchers: DispatchersProvider,
    private val mapFromFoodDataToCache: Mapper<FoodData, FoodStorageCache>,
    private val mapFromFoodStorageCacheToData: Mapper<FoodStorageCache, FoodData>
) : FoodStorageCacheDataSource {


    override suspend fun save(dishes: FoodData) =
        withContext(Dispatchers.IO) {
            dao.saveFoodsForBasket(mapFromFoodDataToCache.map(dishes))
        }

    override suspend fun delete(dishesId: Int) =
        withContext(dispatchers.io()) {
            dao.deleteById(dishesId)
        }

    override fun getAlleForBasket(): Flow<List<FoodData>> =
        dao.getALlBasket()
            .flowOn(dispatchers.io())
            .map { dishes -> dishes.map(mapFromFoodStorageCacheToData::map) }
            .flowOn(dispatchers.default())

}
