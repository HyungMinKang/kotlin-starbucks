package com.codesquad.starbucks.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codesquad.starbucks.databinding.ItemNowRecommendBinding
import com.codesquad.starbucks.domain.model.NowRecommendItem

class NowRecommendAdapter: RecyclerView.Adapter<NowRecommendAdapter.ViewHolder>()  {
    private var products= mutableListOf<NowRecommendItem>()

    class ViewHolder(private val binding: ItemNowRecommendBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(product: NowRecommendItem){
            binding.productTitle=product.productName
            binding.productImage=product.productImage
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(ItemNowRecommendBinding.inflate(inflater,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(products[position])
    }

    override fun getItemCount(): Int {
        return products.size
    }


    fun submitProducts(product:List<NowRecommendItem>){
        products.addAll(product)
        notifyDataSetChanged()
    }
}