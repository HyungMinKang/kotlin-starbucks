package com.codesquad.starbucks.ui.order

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codesquad.starbucks.databinding.ItemOrderCategoryDetailBinding
import com.codesquad.starbucks.domain.model.CategoryItem

class CategoryItemAdapter() :  RecyclerView.Adapter<CategoryItemAdapter.ViewHolder>() {

    private var events= mutableListOf<CategoryItem>()

    class ViewHolder(private val binding: ItemOrderCategoryDetailBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: CategoryItem){
            binding.item= item
            binding.price= "${item.price}원"
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(ItemOrderCategoryDetailBinding.inflate(inflater,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(events[position])
    }

    override fun getItemCount(): Int {
        return events.size
    }

    fun submitEvents(events:List<CategoryItem>){
        this.events.addAll(events)
        notifyDataSetChanged()
    }
}