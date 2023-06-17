package com.example.fooddeliveryapp.data.cloud.source.food

import com.example.fooddeliveryapp.data.base.ResponseHandler
import com.example.fooddeliveryapp.data.cloud.CloudDataRequestState
import com.example.fooddeliveryapp.data.cloud.mappers.FoodCloudDataMapper
import com.example.fooddeliveryapp.data.cloud.models.FoodCloud
import com.example.fooddeliveryapp.data.cloud.models.FoodResponseCloud
import com.example.fooddeliveryapp.data.cloud.service.FoodService
import com.example.fooddeliveryapp.data.data.models.FoodData
import com.example.fooddeliveryapp.domain.DispatchersProvider
import com.example.fooddeliveryapp.domain.Mapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class FoodDataSourceImpl(
    private val service: FoodService,
    private val responseHandler: ResponseHandler,
    private val dispatchersProvider: DispatchersProvider,
    private val mapFromFoodCloudToData: Mapper<FoodCloud, FoodData>,
    private val mapFoodsCloud: FoodCloudDataMapper,
    private val mapFromFoodResponseToCloud: Mapper<FoodResponseCloud, FoodCloud>
) : FoodDataSource {

    override fun fetchFoods(): Flow<List<FoodData>> = flow {
        emit(service.fetchAllFoods())
    }.flowOn(dispatchersProvider.io())
        .map { it.body() ?: FoodResponseCloud(emptyList()) }
        .map { it.foods }
        .map { food -> food.map(mapFromFoodCloudToData::map) }
        .flowOn(dispatchersProvider.default())

    override suspend fun fetchFoodsById(id: Int): CloudDataRequestState<FoodData> =
        responseHandler.safeApiCall { service.fetchAllFoods() }
            .map(mapFromFoodResponseToCloud)
            .map(mapFoodsCloud.map())


}