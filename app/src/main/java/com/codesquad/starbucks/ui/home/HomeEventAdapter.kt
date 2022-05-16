package com.codesquad.starbucks.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codesquad.starbucks.databinding.ItemHomeEventBinding
import com.codesquad.starbucks.databinding.ItemPersonalRecommendBinding
import com.codesquad.starbucks.domain.model.HomeEvent
import com.codesquad.starbucks.domain.model.PersoanlRecommendItem

class HomeEventAdapter: RecyclerView.Adapter<HomeEventAdapter.ViewHolder>() {

    private var events= mutableListOf<HomeEvent>()

    class ViewHolder(private val binding: ItemHomeEventBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(event: HomeEvent){
            binding.event= event
            println(event.imageUri)
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(ItemHomeEventBinding.inflate(inflater,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(events[position])
    }

    override fun getItemCount(): Int {
        return events.size
    }

    fun submitProducts(events:List<HomeEvent>){
        this.events.addAll(events)
        notifyDataSetChanged()
    }
}