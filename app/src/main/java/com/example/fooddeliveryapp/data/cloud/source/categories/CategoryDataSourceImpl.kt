package com.example.fooddeliveryapp.data.cloud.source.categories

import com.example.fooddeliveryapp.data.cloud.models.FoodCategoryCloud
import com.example.fooddeliveryapp.data.cloud.models.FoodCategoryResponseCloud
import com.example.fooddeliveryapp.data.cloud.service.CategotyService
import com.example.fooddeliveryapp.data.data.models.CategoryData
import com.example.fooddeliveryapp.domain.DispatchersProvider
import com.example.fooddeliveryapp.domain.Mapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map


class CategoryDataSourceImpl(
    private val service: CategotyService,
    private val mapCategoryCloudToData: Mapper<FoodCategoryCloud, CategoryData>,
    private val dispatchersProvider: DispatchersProvider
) : CategoryDataSource {

    override fun fetchCategories(): Flow<List<CategoryData>> = flow {
        emit(service.getFoodCategory())
    }.flowOn(dispatchersProvider.io())
        .map { it.body() ?: FoodCategoryResponseCloud(emptyList()) }
        .map { it.category }
        .map { category -> category.map(mapCategoryCloudToData::map) }
        .flowOn(dispatchersProvider.default())

}