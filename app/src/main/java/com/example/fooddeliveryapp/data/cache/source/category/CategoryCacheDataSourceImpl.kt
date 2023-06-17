package com.example.fooddeliveryapp.data.cache.source.category

import com.example.fooddeliveryapp.data.cache.db.CategoriesDao
import com.example.fooddeliveryapp.data.cache.models.CategoryCache
import com.example.fooddeliveryapp.data.data.models.CategoryData
import com.example.fooddeliveryapp.domain.DispatchersProvider
import com.example.fooddeliveryapp.domain.Mapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class CategoryCacheDataSourceImpl(
    private val dao: CategoriesDao,
    private val dispatchersProvider: DispatchersProvider,
    private val categoryCacheToDataMapper: Mapper<CategoryCache, CategoryData>,
    private val categoryDataToCacheMapper: Mapper<CategoryData, CategoryCache>
) : CategoryCacheDataSource {

    override fun fetchAllCategoryFromCacheObservable(): Flow<List<CategoryData>> =
        dao.fetchAllCategoryObservable()
            .flowOn(dispatchersProvider.io())
            .map { genres -> genres.map(categoryCacheToDataMapper::map) }
            .flowOn(dispatchersProvider.default())

    override suspend fun fetchAllCategoryFromCacheSingle(): List<CategoryData> {
        val cachedList = dao.fetchAllCategorySingle()
        return cachedList.map(categoryCacheToDataMapper::map)
    }

    override suspend fun addNewCategoryToCache(category: CategoryData) {
        dao.addNewCategory(categoryDataToCacheMapper.map(category))
    }

    override suspend fun fetchCategoryFromId(id: Int): CategoryData =
        categoryCacheToDataMapper.map(dao.fetchCategoryFromId(id = id))

    override suspend fun clearTable() {
        dao.clearTable()
    }
}