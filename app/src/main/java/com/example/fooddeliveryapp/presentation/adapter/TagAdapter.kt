package com.example.fooddeliveryapp.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.presentation.adapter.listeners.TagsItemClickListener
import com.example.fooddeliveryapp.presentation.adapter.view_holdeer.RvViewHolder
import com.example.fooddeliveryapp.presentation.utils.extensions.setOnDownEffectClickListener

class TagAdapter(private val listener: TagsItemClickListener) :
    RecyclerView.Adapter<RvViewHolder>() {

    private var tagsList: HashSet<String> = HashSet()

    private var selectedTag: Int = 0


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_tags, parent, false)
        return RvViewHolder(view)
    }

    override fun onBindViewHolder(holder: RvViewHolder, position: Int) {
        val tagItem = tagsList.elementAt(position)
        holder.bindTags(tagItem, selectedTag)
        holder.itemView.setOnDownEffectClickListener {
            listener.itemClickListener(
                tag = tagItem,
                previousItem = selectedTag,
                selectedItem = position
            )
        }
    }

    override fun getItemCount() = tagsList.size

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: HashSet<String>?) {
        data?.let {
            this.tagsList = it
            notifyDataSetChanged()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setSelected(position: Int) {
        selectedTag = position
    }

}

