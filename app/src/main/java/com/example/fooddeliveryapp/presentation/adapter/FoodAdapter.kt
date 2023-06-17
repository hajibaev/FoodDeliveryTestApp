package com.example.fooddeliveryapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.presentation.adapter.diffCallBack.FoodDiffCallBack
import com.example.fooddeliveryapp.presentation.adapter.listeners.FoodItemClickListener
import com.example.fooddeliveryapp.presentation.adapter.view_holdeer.RvViewHolder
import com.example.fooddeliveryapp.presentation.models.FoodUi
import com.example.fooddeliveryapp.presentation.utils.extensions.setOnDownEffectClickListener

class FoodAdapter(private val listener: FoodItemClickListener) :
    RecyclerView.Adapter<RvViewHolder>() {

    var foodList = listOf<FoodUi>()
        set(value) {
            val callback = FoodDiffCallBack(foodList, value)
            val diffResult = DiffUtil.calculateDiff(callback)
            diffResult.dispatchUpdatesTo(this)
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvViewHolder =
        RvViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.food_item, parent, false))

    override fun onBindViewHolder(holder: RvViewHolder, position: Int) {
        holder.itemView.setOnDownEffectClickListener {
            listener.foodItemOnClick(foodList[position].foodId)
        }
        holder.bindFood(food = foodList[position])
    }

    override fun getItemCount() = foodList.size
}
