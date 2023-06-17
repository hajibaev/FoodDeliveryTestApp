package com.example.fooddeliveryapp.presentation.ui.screen_home

import androidx.lifecycle.viewModelScope
import com.example.fooddeliveryapp.domain.DispatchersProvider
import com.example.fooddeliveryapp.domain.Mapper
import com.example.fooddeliveryapp.domain.models.CategoryDomain
import com.example.fooddeliveryapp.domain.models.FoodDomain
import com.example.fooddeliveryapp.domain.use_case.CategoryUseCase
import com.example.fooddeliveryapp.domain.use_case.FoodItemsUseCase
import com.example.fooddeliveryapp.presentation.base.BaseResourceProvider
import com.example.fooddeliveryapp.presentation.base.BaseViewModel
import com.example.fooddeliveryapp.presentation.models.CategoryUi
import com.example.fooddeliveryapp.presentation.ui.screen_home.mappers.AllFoodsItemFilteredMapper
import com.example.fooddeliveryapp.presentation.ui.screen_home.router.HomeScreenRouter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.shareIn

class FragmentHomeScreenViewModel(
    categoryUseCase: CategoryUseCase,
    foodItemsUseCase: FoodItemsUseCase,
    private val allFoodsItemFilteredMapper: AllFoodsItemFilteredMapper,
    dispatchersProvider: DispatchersProvider,
    private val resourceProvider: BaseResourceProvider,
    private val router: HomeScreenRouter,
    private val mapFromCategoryDomainToUi: Mapper<CategoryDomain, CategoryUi>,
) : BaseViewModel() {

    private var tagsList = HashSet<String>()

    private val _tagsFlow = MutableStateFlow("")
    val tagsFlow get() = _tagsFlow.asStateFlow()

    private val _all_tags = createMutableSharedFlowAsSingleLiveEvent<HashSet<String>>()
    val all_tags get() = _all_tags.asSharedFlow()


    val foods = _tagsFlow.flatMapLatest { foodItemsUseCase.invoke() }
        .flowOn(dispatchersProvider.default())
        .map { items -> mapToAdapterModel(items, _tagsFlow.value) }
        .onEach { it.forEach { it.tags.forEach { setAllTags(it) } } }
        .catch { exception: Throwable ->
            emitToErrorMessageFlow(resourceProvider.fetchIdErrorMessage(exception))
        }


    val getCategory = categoryUseCase.invoke()
        .flowOn(dispatchersProvider.default())
        .map { categoryDomain -> categoryDomain.map(mapFromCategoryDomainToUi::map) }
        .catch { exception: Throwable ->
            emitToErrorMessageFlow(resourceProvider.fetchIdErrorMessage(exception))
        }.shareIn(viewModelScope, SharingStarted.Lazily, 1)


    private fun mapToAdapterModel(items: List<FoodDomain>, tags: String) =
        allFoodsItemFilteredMapper.map(
            items = items,
            filterTags = tags,
        )

    fun setAllTags(value: String) {
        tagsList.add(value)
        _all_tags.tryEmit(tagsList)
    }

    fun tagsClick(tag: String) {
        _tagsFlow.value = tag
    }

    fun navigateToFoodInfoFragment(id: Int) {
        navigate(router.navigateToFoodInfoFragment(id))
    }


}