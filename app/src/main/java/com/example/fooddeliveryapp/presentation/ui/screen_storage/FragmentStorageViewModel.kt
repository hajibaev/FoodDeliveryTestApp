package com.example.fooddeliveryapp.presentation.ui.screen_storage

import androidx.lifecycle.viewModelScope
import com.example.fooddeliveryapp.domain.DispatchersProvider
import com.example.fooddeliveryapp.domain.Mapper
import com.example.fooddeliveryapp.domain.models.FoodDomain
import com.example.fooddeliveryapp.domain.use_case.FetchAllBasketScreenItemsUseCase
import com.example.fooddeliveryapp.presentation.base.BaseResourceProvider
import com.example.fooddeliveryapp.presentation.base.BaseViewModel
import com.example.fooddeliveryapp.presentation.models.FoodUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FragmentStorageViewModel(
    private val fetchAllBasketScreenItemsUseCase: FetchAllBasketScreenItemsUseCase,
    dispatchersProvider: DispatchersProvider,
    private val mapFromDishesDomainToUi: Mapper<FoodDomain, FoodUi>,
    private val resourceProvider: BaseResourceProvider
) : BaseViewModel() {

    private val _totalPrice = MutableStateFlow(0)
    val totalPrice get() = _totalPrice.asStateFlow()

    private val _totalCount = MutableStateFlow(0)
    val totalCount get() = _totalCount.asStateFlow()

    val getAllDishes = fetchAllBasketScreenItemsUseCase.invoke()
        .flowOn(dispatchersProvider.io())
        .map { list -> list.map(mapFromDishesDomainToUi::map) }
        .catch { exception: Throwable ->
            emitToErrorMessageFlow(resourceProvider.fetchIdErrorMessage(exception))
        }.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())


    fun getAllPriceFromBasket() = getAllDishes.value.forEach {
        _totalPrice.value += it.price
        _totalCount.value += it.count
    }

    fun observeTotalPrice(total: Int, isPlus: Boolean, count: Int) {
        if (isPlus) {
            _totalPrice.value += total
            _totalCount.value += count
        } else if (!isPlus) {
            _totalPrice.value -= total
            _totalCount.value -= count
        }
    }

    fun deleteFoodFromStorage(id: Int) = viewModelScope.launch {
        fetchAllBasketScreenItemsUseCase.invoke(id)
        _totalCount.value -= 1
    }

}