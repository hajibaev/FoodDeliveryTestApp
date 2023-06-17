package com.example.fooddeliveryapp.presentation.di

import com.example.fooddeliveryapp.presentation.mappers.MapFromCategoryDomainToUiModel
import com.example.fooddeliveryapp.presentation.mappers.MapFromFoodDomainToUi
import com.example.fooddeliveryapp.presentation.mappers.MapFromFoodUiToDomain
import com.example.fooddeliveryapp.presentation.ui.food_info_screen.FoodInfoViewModel
import com.example.fooddeliveryapp.presentation.ui.screen_home.FragmentHomeScreenViewModel
import com.example.fooddeliveryapp.presentation.ui.screen_profile.FragmentProfileViewModel
import com.example.fooddeliveryapp.presentation.ui.screen_storage.FragmentStorageViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModel = module {

    viewModel {
        FragmentHomeScreenViewModel(
            categoryUseCase = get(),
            foodItemsUseCase = get(),
            allFoodsItemFilteredMapper = get(),
            dispatchersProvider = get(),
            resourceProvider = get(),
            router = get(),
            mapFromCategoryDomainToUi = MapFromCategoryDomainToUiModel(),
        )
    }

    viewModel {
        FragmentProfileViewModel()
    }

    viewModel {
        FragmentStorageViewModel(
            fetchAllBasketScreenItemsUseCase = get(),
            dispatchersProvider = get(),
            mapFromDishesDomainToUi = MapFromFoodDomainToUi(),
            resourceProvider = get(),
        )
    }

    viewModel { (id: String) ->
        FoodInfoViewModel(
            id = id,
            saveFoodForBasketUseCase = get(),
            foodItemsUseCase = get(),
            saveMapper = MapFromFoodUiToDomain(),
            mapFromFoodsDomainToUi = MapFromFoodDomainToUi()
        )
    }

}