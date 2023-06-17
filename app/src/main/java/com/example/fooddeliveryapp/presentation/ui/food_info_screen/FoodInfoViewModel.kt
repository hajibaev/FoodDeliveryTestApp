package com.example.fooddeliveryapp.presentation.ui.food_info_screen

import androidx.lifecycle.viewModelScope
import com.example.fooddeliveryapp.domain.Mapper
import com.example.fooddeliveryapp.domain.models.FoodDomain
import com.example.fooddeliveryapp.domain.use_case.FoodItemsUseCase
import com.example.fooddeliveryapp.domain.use_case.SaveFoodForBasketUseCase
import com.example.fooddeliveryapp.presentation.base.BaseViewModel
import com.example.fooddeliveryapp.presentation.models.FoodUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class FoodInfoViewModel(
    id: String,
    private val saveFoodForBasketUseCase: SaveFoodForBasketUseCase,
    private val foodItemsUseCase: FoodItemsUseCase,
    private val saveMapper: Mapper<FoodUi, FoodDomain>,
    private val mapFromFoodsDomainToUi: Mapper<FoodDomain, FoodUi>
) : BaseViewModel() {

    private val foodIdFlow = MutableStateFlow(id)

    val foods =
        foodIdFlow.flatMapLatest(foodItemsUseCase::invoke)
            .map(mapFromFoodsDomainToUi::map)


    fun saveFoodForBasket(food: FoodUi) = viewModelScope.launch {
        saveFoodForBasketUseCase.invoke(saveMapper.map(food))
    }


}