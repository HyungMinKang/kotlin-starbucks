package com.codesquad.starbucks.ui.order.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codesquad.starbucks.databinding.ItemOrderCategoryDetailBinding
import com.codesquad.starbucks.domain.model.CategoryItem

class CategoryItemAdapter(private val itemClick: (productCD: String, productPrice: String) -> Unit) : RecyclerView.Adapter<CategoryItemAdapter.ViewHolder>() {

    private var events = mutableListOf<CategoryItem>()

    class ViewHolder(private val binding: ItemOrderCategoryDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CategoryItem, itemClick: (productPrice: String, productCD: String) -> Unit) {
            binding.item = item
            binding.price = "${item.price}원"
            binding.root.setOnClickListener {
                itemClick.invoke(item.productCD, item.price.toString(),)
            }
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(ItemOrderCategoryDetailBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(events[position], itemClick)
    }

    override fun getItemCount(): Int {
        return events.size
    }

    fun submitEvents(events: List<CategoryItem>) {
        this.events.addAll(events)
        notifyDataSetChanged()
    }
}