package com.example.fooddeliveryapp.presentation.adapter.diffCallBack

import androidx.recyclerview.widget.DiffUtil
import com.example.fooddeliveryapp.presentation.models.FoodUi

class FoodDiffCallBack(
    private val oldList: List<FoodUi>,
    private val newList: List<FoodUi>,
) : DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].foodId == newList[newItemPosition].foodId

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition] == newList[newItemPosition]
}