package com.example.fooddeliveryapp.presentation.ui.food_info_screen

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.databinding.FragmentFoodInfoBinding
import com.example.fooddeliveryapp.presentation.base.BaseFragment
import com.example.fooddeliveryapp.presentation.models.FoodUi
import com.example.fooddeliveryapp.presentation.utils.extensions.launchWhenViewStarted
import com.example.fooddeliveryapp.presentation.utils.extensions.makeToast
import com.example.fooddeliveryapp.presentation.utils.extensions.setOnDownEffectClickListener
import com.example.fooddeliveryapp.presentation.utils.extensions.showRoundedImage
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class FoodInfoFragment :
    BaseFragment<FragmentFoodInfoBinding, FoodInfoViewModel>(FragmentFoodInfoBinding::inflate) {

    private val foodId by lazy { FoodInfoFragmentArgs.fromBundle(requireArguments()).foodId }

    override val viewModel: FoodInfoViewModel by viewModel {
        parametersOf(foodId)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideBottomNavigationView()
        hideToolbarLayout()
        observeData()
    }

    private fun observeData() = with(viewModel) {
        launchWhenViewStarted {
            foods.observe { dishes ->
                observeResource(food = dishes)
                setOnClickListeners(food = dishes)
            }
        }
    }

    private fun observeResource(food: FoodUi) = with(binding()) {
        requireContext().showRoundedImage(imageUrl = food.image_url, imageView = poster)
        price.text = "${food.price} ₽"
        weight.text = "${food.weight} г"
        title.text = food.title
        description.text = food.description
        saveBtn.text = "В корзину за ${food.price} ₽"
    }

    private fun setOnClickListeners(food: FoodUi) = with(binding()) {
        saveBtn.setOnDownEffectClickListener {
            viewModel.saveFoodForBasket(food)
            requireContext().makeToast("Блюдо добавлено в корзину")
        }
        closeBtn.setOnDownEffectClickListener {
            viewModel.navigateBack()
        }
        basketBtn.setOnDownEffectClickListener {
            viewModel.navigateBack()
        }
    }
}