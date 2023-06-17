package com.example.fooddeliveryapp.presentation.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.databinding.ItemBasketBinding
import com.example.fooddeliveryapp.presentation.adapter.diffCallBack.FoodDiffCallBack
import com.example.fooddeliveryapp.presentation.adapter.listeners.StorageItemClickListener
import com.example.fooddeliveryapp.presentation.models.FoodUi
import com.example.fooddeliveryapp.presentation.utils.extensions.setOnDownEffectClickListener
import com.example.fooddeliveryapp.presentation.utils.extensions.showRoundedImage

class StorageAdapter(private val listener: StorageItemClickListener) :
    RecyclerView.Adapter<StorageAdapter.StorageViewHolder>() {

    var foodList = listOf<FoodUi>()
        set(value) {
            val callback = FoodDiffCallBack(foodList, value)
            val diffResult = DiffUtil.calculateDiff(callback)
            diffResult.dispatchUpdatesTo(this)
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StorageViewHolder =
        StorageViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_basket, parent, false)
        )

    override fun onBindViewHolder(holder: StorageViewHolder, position: Int) {
        holder.setupViews(foodList[position])
        holder.setOnClickListeners(foodList[position])
    }

    override fun getItemCount() = foodList.size

    inner class StorageViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemBasketBinding.bind(view)

        fun setupViews(item: FoodUi) = with(binding) {
            itemView.context.showRoundedImage(imageUrl = item.image_url, imageView = poster)
            title.text = item.title
            count.text = item.count.toString()
            price.text = "${item.price} ₽"
            foodWeight.text = "${item.weight}г"
        }

        fun setOnClickListeners(item: FoodUi) = with(binding) {
            plusCount.setOnDownEffectClickListener {
                item.count += 1
                observePrice(item.price, item = item, count = item.count - 1)
            }

            minusCount.setOnDownEffectClickListener {
                item.count -= 1
                item.totalPrice = item.count * item.price
                observePrice(item.price, item = item, isPlus = false, count = item.count)
                if (item.count == 0) listener.deleteFood(item.foodId)
            }
        }

        private fun observePrice(price: Int, isPlus: Boolean = true, count: Int, item: FoodUi) =
            with(binding) {
                binding.count.text = item.count.toString()
                listener.observePrice(price, isPlus, count = count)
            }
    }
}
