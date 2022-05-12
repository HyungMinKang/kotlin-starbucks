package com.codesquad.starbucks.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codesquad.starbucks.databinding.ItemPersonalRecommendBinding
import com.codesquad.starbucks.domain.model.PersoanlRecommendItem

class PersonalRecommendAdapter: RecyclerView.Adapter<PersonalRecommendAdapter.ViewHolder>()  {
    private var products= mutableListOf<PersoanlRecommendItem>()

    class ViewHolder(private val binding: ItemPersonalRecommendBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(product: PersoanlRecommendItem){
            binding.productTitle=product.productName
            binding.productImage=product.productImage
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(ItemPersonalRecommendBinding.inflate(inflater,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(products[position])
    }

    override fun getItemCount(): Int {
        return products.size
    }

    fun submitProducts(product:List<PersoanlRecommendItem>){
        products.addAll(product)
        notifyDataSetChanged()
    }
}