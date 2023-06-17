package com.example.fooddeliveryapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.presentation.adapter.diffCallBack.CategoryDiffCallBack
import com.example.fooddeliveryapp.presentation.adapter.view_holdeer.RvViewHolder
import com.example.fooddeliveryapp.presentation.models.CategoryUi

class CategoryAdapter : RecyclerView.Adapter<RvViewHolder>() {

    var categoryList = listOf<CategoryUi>()
        set(value) {
            val callback = CategoryDiffCallBack(categoryList, value)
            val diffResult = DiffUtil.calculateDiff(callback)
            diffResult.dispatchUpdatesTo(this)
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvViewHolder =
        RvViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_categories, parent, false))

    override fun onBindViewHolder(holder: RvViewHolder, position: Int) {
        holder.bindCategoty(categoryList[position])
    }

    override fun getItemCount() = categoryList.size
}