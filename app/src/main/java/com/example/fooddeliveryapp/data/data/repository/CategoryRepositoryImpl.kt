package com.example.fooddeliveryapp.data.data.repository

import com.example.fooddeliveryapp.data.cache.source.category.CategoryCacheDataSource
import com.example.fooddeliveryapp.data.cloud.source.categories.CategoryDataSource
import com.example.fooddeliveryapp.data.data.models.CategoryData
import com.example.fooddeliveryapp.domain.DispatchersProvider
import com.example.fooddeliveryapp.domain.Mapper
import com.example.fooddeliveryapp.domain.models.CategoryDomain
import com.example.fooddeliveryapp.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class CategoryRepositoryImpl(
    private val cloudDataSource: CategoryDataSource,
    private val cacheDataSource: CategoryCacheDataSource,
    private val dispatchersProvider: DispatchersProvider,
    private val mapCategoryDataToDomain: Mapper<CategoryData, CategoryDomain>
) : CategoryRepository {

    override fun fetchCategories(): Flow<List<CategoryDomain>> = flow {
        emit(cacheDataSource.fetchAllCategoryFromCacheSingle())
    }.flatMapLatest { handleFetchCategoryInCache(it) }
        .map { category -> category.map(mapCategoryDataToDomain::map) }
        .flowOn(dispatchersProvider.default())

    private fun handleFetchCategoryInCache(
        cachedCategories: List<CategoryData>,
    ) = if (cachedCategories.isEmpty()) cloudDataSource.fetchCategories()
        .onEach { category -> category.forEach { cacheDataSource.addNewCategoryToCache(it) } }
    else cacheDataSource.fetchAllCategoryFromCacheObservable()

    override suspend fun fetchCategoriesFromCache(category_id: Int): CategoryDomain {
        return mapCategoryDataToDomain.map(cacheDataSource.fetchCategoryFromId(id = category_id))
    }

}