package com.codesquad.starbucks.ui.order.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codesquad.starbucks.databinding.ItemOrderCategoryBinding
import com.codesquad.starbucks.domain.model.Category

class CategoryAdapter(private val itemClick: (categoryName: String) -> Unit) :  RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    private var events= mutableListOf<Category>()

    class ViewHolder(private val binding: ItemOrderCategoryBinding ): RecyclerView.ViewHolder(binding.root){
        fun bind(item: Category, itemClick: (categoryName: String) -> Unit){
            binding.item= item
            binding.root.setOnClickListener {
                itemClick.invoke(item.koTitle)
            }
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(ItemOrderCategoryBinding.inflate(inflater,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(events[position], itemClick)
    }

    override fun getItemCount(): Int {
        return events.size
    }

    fun submitEvents(events:List<Category>){
        this.events.addAll(events)
        notifyDataSetChanged()
    }
}