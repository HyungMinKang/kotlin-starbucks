package com.codesquad.starbucks.ui.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codesquad.starbucks.databinding.ItemFavoriteBinding
import com.codesquad.starbucks.room.FavoriteEntity

class FavoriteAdapter (private val itemClick: ( item:FavoriteEntity,) -> Unit) :  RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    private var favorites= mutableListOf<FavoriteEntity>()

    class ViewHolder(private val binding: ItemFavoriteBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: FavoriteEntity, itemClick: ( item:FavoriteEntity,) -> Unit){
            binding.item= item
            binding.btnFavorite.setOnClickListener {
                itemClick.invoke(item)
            }
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(ItemFavoriteBinding.inflate(inflater,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(favorites[position], itemClick)
    }

    override fun getItemCount(): Int {
        return favorites.size
    }

    fun submitEvents(favorites:List<FavoriteEntity>){
        this.favorites= mutableListOf<FavoriteEntity>()
        this.favorites.addAll(favorites)
        notifyDataSetChanged()
    }
}