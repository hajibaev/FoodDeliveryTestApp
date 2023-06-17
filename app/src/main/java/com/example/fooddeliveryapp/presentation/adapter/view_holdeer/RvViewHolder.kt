package com.example.fooddeliveryapp.presentation.adapter.view_holdeer

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.presentation.models.CategoryUi
import com.example.fooddeliveryapp.presentation.models.FoodUi
import com.example.fooddeliveryapp.presentation.utils.extensions.getAttrColor
import com.example.fooddeliveryapp.presentation.utils.extensions.showRoundedImage

class RvViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val image = view.findViewById<ImageView>(R.id.poster)
    private val title = view.findViewById<TextView>(R.id.title)
    private val description = view.findViewById<TextView>(R.id.description)
    private val container = view.findViewById<CardView>(R.id.container)
    private val price = view.findViewById<TextView>(R.id.price)

    val backgroundPrimaryColor = itemView.context.getAttrColor(R.attr.card_primary_background)

    val backgroundGrayColor = itemView.context.getAttrColor(R.attr.card_background)

    val textBackgroundPrimaryColor = itemView.context.getAttrColor(R.attr.text_primary_background)

    val textBackgroundGrayColor = itemView.context.getAttrColor(R.attr.text_gray_background)

    fun bindTags(tag: String, selectedTag: Int) {
        title.text = tag
        if (selectedTag == bindingAdapterPosition) {
            container.setCardBackgroundColor(backgroundPrimaryColor)
            title.setTextColor(textBackgroundPrimaryColor)
        } else {
            container.setCardBackgroundColor(backgroundGrayColor)
            title.setTextColor(textBackgroundGrayColor)
        }
    }

    fun bindFood(food: FoodUi) {
        title.text = food.title
        description.text = food.description
        price.text = "от ${food.price} ₽"
        itemView.context.showRoundedImage(imageUrl = food.image_url, imageView = image)
    }


    fun bindCategoty(category: CategoryUi) {
        itemView.context.showRoundedImage(imageUrl = category.image, imageView = image)
    }

}
