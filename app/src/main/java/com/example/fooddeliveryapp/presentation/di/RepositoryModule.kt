package com.example.fooddeliveryapp.presentation.di

import com.example.fooddeliveryapp.domain.repository.FoodStorageRepository
import com.example.fooddeliveryapp.data.cache.mappers.MapFromFoodCacheToData
import com.example.fooddeliveryapp.data.data.mappers.CategoryDataToDomainMapper
import com.example.fooddeliveryapp.data.data.mappers.MapFromFoodDataToDomain
import com.example.fooddeliveryapp.data.data.mappers.MapFromFoodDomainToData
import com.example.fooddeliveryapp.data.data.repository.CategoryRepositoryImpl
import com.example.fooddeliveryapp.data.data.repository.DishesStorageRepositoryImpl
import com.example.fooddeliveryapp.data.data.repository.FoodRepositoryImpl
import com.example.fooddeliveryapp.domain.repository.CategoryRepository
import com.example.fooddeliveryapp.domain.repository.FoodRepository
import org.koin.dsl.module

val repositoryModule = module {


    single<CategoryRepository> {
        CategoryRepositoryImpl(
            cloudDataSource = get(),
            cacheDataSource = get(),
            dispatchersProvider = get(),
            mapCategoryDataToDomain = CategoryDataToDomainMapper(),
        )
    }

    single<FoodRepository> {
        FoodRepositoryImpl(
            cacheDataSource = get(),
            source = get(),
            dispatchersProvider = get(),
            mapFromFoodDataToDomain = MapFromFoodDataToDomain(),
            mapFromFoodCacheToData = MapFromFoodCacheToData()
        )
    }

    single<FoodStorageRepository> {
        DishesStorageRepositoryImpl(
            source = get(),
            mapFromFoodDomainToData = MapFromFoodDomainToData(),
            mapFromFoodDataToDomain = MapFromFoodDataToDomain(),
        )
    }


}