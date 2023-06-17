package com.example.fooddeliveryapp.presentation.di

import com.example.fooddeliveryapp.data.cloud.mappers.FoodCloudDataMapper
import com.example.fooddeliveryapp.data.cloud.mappers.FoodCloudDataMapperImpl
import org.koin.dsl.module

val mapperModule = module {

    single<FoodCloudDataMapper> {
        FoodCloudDataMapperImpl()
    }

}