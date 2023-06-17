package com.example.fooddeliveryapp.presentation.di

import com.example.fooddeliveryapp.data.cache.mappers.MapFromCategoryCacheToData
import com.example.fooddeliveryapp.data.cache.mappers.MapFromCategoryDataToCache
import com.example.fooddeliveryapp.data.cache.mappers.MapFromFoodCacheToData
import com.example.fooddeliveryapp.data.cache.mappers.MapFromFoodDataToCache
import com.example.fooddeliveryapp.data.cache.mappers.MapFromFoodDataToFoodStorageCache
import com.example.fooddeliveryapp.data.cache.mappers.MapFromFoodStorageCacheToData
import com.example.fooddeliveryapp.data.cache.source.category.CategoryCacheDataSource
import com.example.fooddeliveryapp.data.cache.source.category.CategoryCacheDataSourceImpl
import com.example.fooddeliveryapp.data.cache.source.food.FoodCacheDataSource
import com.example.fooddeliveryapp.data.cache.source.food.FoodCacheDataSourceImpl
import com.example.fooddeliveryapp.data.cache.source.food_storage.FoodStorageCacheDataSource
import com.example.fooddeliveryapp.data.cache.source.food_storage.FoodStorageCacheDataSourceImpl
import com.example.fooddeliveryapp.data.cloud.mappers.MapFromCategoryCloudToData
import com.example.fooddeliveryapp.data.cloud.mappers.MapFromFoodCloudToData
import com.example.fooddeliveryapp.data.cloud.mappers.MapFromFoodResponseToCloud
import com.example.fooddeliveryapp.data.cloud.source.categories.CategoryDataSource
import com.example.fooddeliveryapp.data.cloud.source.categories.CategoryDataSourceImpl
import com.example.fooddeliveryapp.data.cloud.source.food.FoodDataSource
import com.example.fooddeliveryapp.data.cloud.source.food.FoodDataSourceImpl
import org.koin.dsl.module

val dataSourceModule = module {
    /**
     * CloudDataSource
     */

    single<CategoryDataSource> {
        CategoryDataSourceImpl(
            service = get(),
            mapCategoryCloudToData = MapFromCategoryCloudToData(),
            dispatchersProvider = get()
        )
    }

    single<FoodDataSource> {
        FoodDataSourceImpl(
            service = get(),
            dispatchersProvider = get(),
            responseHandler = get(),
            mapFromFoodCloudToData = MapFromFoodCloudToData(),
            mapFoodsCloud = get(),
            mapFromFoodResponseToCloud = MapFromFoodResponseToCloud()
        )
    }

    /**
     * CacheDataSource
     */

    single<CategoryCacheDataSource> {
        CategoryCacheDataSourceImpl(
            dao = get(),
            dispatchersProvider = get(),
            categoryCacheToDataMapper = MapFromCategoryCacheToData(),
            categoryDataToCacheMapper = MapFromCategoryDataToCache(),
        )
    }


    single<FoodCacheDataSource> {
        FoodCacheDataSourceImpl(
            dao = get(),
            dispatchersProvider = get(),
            mapFromFoodCacheToData = MapFromFoodCacheToData(),
            mapFromFoodDataToCache = MapFromFoodDataToCache()
        )
    }

    single<FoodStorageCacheDataSource> {
        FoodStorageCacheDataSourceImpl(
            dao = get(),
            dispatchers = get(),
            mapFromFoodStorageCacheToData = MapFromFoodStorageCacheToData(),
            mapFromFoodDataToCache = MapFromFoodDataToFoodStorageCache()
        )
    }

}