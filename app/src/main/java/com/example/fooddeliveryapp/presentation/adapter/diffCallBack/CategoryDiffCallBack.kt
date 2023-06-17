package com.example.fooddeliveryapp.presentation.adapter.diffCallBack

import androidx.recyclerview.widget.DiffUtil
import com.example.fooddeliveryapp.presentation.models.CategoryUi

class CategoryDiffCallBack(
    private val oldList: List<CategoryUi>,
    private val newList: List<CategoryUi>,
) : DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition] == newList[newItemPosition]
}