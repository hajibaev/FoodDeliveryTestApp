package com.example.fooddeliveryapp.presentation.ui.screen_home

import android.os.Bundle
import android.view.View
import com.example.fooddeliveryapp.databinding.FragmentHomeScreenBinding
import com.example.fooddeliveryapp.presentation.adapter.CategoryAdapter
import com.example.fooddeliveryapp.presentation.adapter.FoodAdapter
import com.example.fooddeliveryapp.presentation.adapter.TagAdapter
import com.example.fooddeliveryapp.presentation.adapter.listeners.FoodItemClickListener
import com.example.fooddeliveryapp.presentation.adapter.listeners.TagsItemClickListener
import com.example.fooddeliveryapp.presentation.base.BaseFragment
import com.example.fooddeliveryapp.presentation.utils.extensions.launchWhenViewStarted
import kotlinx.coroutines.flow.filterNotNull
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentHomeScreen :
    BaseFragment<FragmentHomeScreenBinding, FragmentHomeScreenViewModel>(FragmentHomeScreenBinding::inflate),
    TagsItemClickListener, FoodItemClickListener {


    override val viewModel: FragmentHomeScreenViewModel by viewModel()


    private val categoryAdapter by lazy { CategoryAdapter() }
    private val tagAdapter by lazy { TagAdapter(this) }
    private val foodAdapter by lazy { FoodAdapter(this) }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        showBottomNavigationView()
        showToolbarLayout()
        observeRecourse()
    }

    private fun setupViews() = with(binding()) {
        foodRecyclerView.adapter = foodAdapter
        tagRecyclerView.adapter = tagAdapter
        categoryRecyclerView.adapter = categoryAdapter
    }

    private fun observeRecourse() = with(viewModel) {
        launchWhenViewStarted {
            foods.observe { foodAdapter.foodList = it }
            all_tags.filterNotNull().observe { tagAdapter.setData(it) }
            tagsFlow.filterNotNull().observe { tagAdapter.setSelected(0) }
            getCategory.filterNotNull().observe { categoryAdapter.categoryList = it }
        }
    }


    override fun foodItemOnClick(id: Int) {
        viewModel.navigateToFoodInfoFragment(id)
    }

    override fun itemClickListener(tag: String, previousItem: Int, selectedItem: Int) {
        viewModel.tagsClick(tag)
        tagAdapter.setSelected(selectedItem)
        tagAdapter.notifyItemChanged(previousItem)
        tagAdapter.notifyItemChanged(selectedItem)
    }
}