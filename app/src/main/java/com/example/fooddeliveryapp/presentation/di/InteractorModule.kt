package com.example.fooddeliveryapp.presentation.di

import com.example.fooddeliveryapp.domain.use_case.CategoryUseCase
import com.example.fooddeliveryapp.domain.use_case.CategoryUseCaseImpl
import com.example.fooddeliveryapp.domain.use_case.FetchAllBasketScreenItemsUseCase
import com.example.fooddeliveryapp.domain.use_case.FetchAllBasketScreenItemsUseCaseImpl
import com.example.fooddeliveryapp.domain.use_case.FoodItemsUseCase
import com.example.fooddeliveryapp.domain.use_case.FoodItemsUseCaseImpl
import com.example.fooddeliveryapp.domain.use_case.SaveFoodForBasketUseCase
import com.example.fooddeliveryapp.domain.use_case.SaveFoodForBasketUseCaseImpl
import com.example.fooddeliveryapp.presentation.mappers.MapFromFoodDomainToUi
import com.example.fooddeliveryapp.presentation.ui.screen_home.mappers.AllFoodsItemFilteredMapper
import com.example.fooddeliveryapp.presentation.ui.screen_home.mappers.AllFoodsItemFilteredMapperImpl
import org.koin.dsl.module

val interactorModule = module {

    factory<CategoryUseCase> {
        CategoryUseCaseImpl(repository = get())
    }

    factory<FetchAllBasketScreenItemsUseCase> {
        FetchAllBasketScreenItemsUseCaseImpl(repository = get())
    }

    factory<FoodItemsUseCase> {
        FoodItemsUseCaseImpl(repository = get())
    }

    factory<SaveFoodForBasketUseCase> {
        SaveFoodForBasketUseCaseImpl(repository = get())
    }

    factory<AllFoodsItemFilteredMapper> {
        AllFoodsItemFilteredMapperImpl(mapFromDishesDomainToUi = MapFromFoodDomainToUi())
    }

}