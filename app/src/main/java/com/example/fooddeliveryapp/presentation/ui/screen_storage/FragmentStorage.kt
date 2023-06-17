package com.example.fooddeliveryapp.presentation.ui.screen_storage

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.fooddeliveryapp.databinding.FragmentStorageBinding
import com.example.fooddeliveryapp.presentation.adapter.StorageAdapter
import com.example.fooddeliveryapp.presentation.adapter.listeners.StorageItemClickListener
import com.example.fooddeliveryapp.presentation.base.BaseFragment
import com.example.fooddeliveryapp.presentation.models.FoodUi
import com.example.fooddeliveryapp.presentation.utils.extensions.hideView
import com.example.fooddeliveryapp.presentation.utils.extensions.launchWhenViewStarted
import com.example.fooddeliveryapp.presentation.utils.extensions.setOnDownEffectClickListener
import com.example.fooddeliveryapp.presentation.utils.extensions.showView
import kotlinx.coroutines.flow.filterNotNull
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentStorage :
    BaseFragment<FragmentStorageBinding, FragmentStorageViewModel>(FragmentStorageBinding::inflate),
    StorageItemClickListener {

    override val viewModel: FragmentStorageViewModel by viewModel()

    private val adapter by lazy {
        StorageAdapter(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showBottomNavigationView()
        setupViews()
        showToolbarLayout()
        observeRecourse()
        setOnClickListener()
    }

    private fun setOnClickListener() = with(binding()) {
        saveBtn.setOnDownEffectClickListener {
            findNavController().navigate(FragmentStorageDirections.actionScreenStorageToScreenHome())
        }
    }

    private fun setupViews() = with(binding()) {
        recyclerView.adapter = adapter
    }

    private fun observeRecourse() = with(viewModel) {
        launchWhenViewStarted {
            getAllDishes.filterNotNull().observe(::filterAdapter)
            totalPrice.observe {
                binding().priceBtn.text = "Оплатить $it ₽"
                binding().foodPrice.text = "$it ₽"
            }
            totalCount.observe { binding().foodCount.text = "$it товар на " }
        }
    }

    private fun filterAdapter(basket: List<FoodUi>) = with(binding()) {
        adapter.foodList = basket
        if (basket.isNotEmpty()) {
            emptyContainer.hideView()
            storage.showView()
            priceBtn.showView()
            food.showView()
        } else {
            emptyContainer.showView()
            storage.hideView()
            priceBtn.hideView()
            food.hideView()
        }
    }


    override fun observePrice(totalPrice: Int, isPlus: Boolean) {
        viewModel.observeTotalPrice(totalPrice, isPlus = isPlus)
    }

    override fun deleteFood(id: Int) {
        viewModel.deleteFoodFromStorage(id)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllPriceFromBasket()
    }

}