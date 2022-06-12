package com.codesquad.starbucks.ui.whatNew

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codesquad.starbucks.databinding.ItemWhatNewEventBinding
import com.codesquad.starbucks.domain.model.WhatNewEvent

class WhatNewAdapter:  RecyclerView.Adapter<WhatNewAdapter.ViewHolder>() {

    private var events= mutableListOf<WhatNewEvent>()

    class ViewHolder(private val binding: ItemWhatNewEventBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(event: WhatNewEvent){
            binding.event= event
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(ItemWhatNewEventBinding.inflate(inflater,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(events[position])
    }

    override fun getItemCount(): Int {
        return events.size
    }

    fun submitEvents(events:List<WhatNewEvent>){
        this.events.addAll(events)
        notifyDataSetChanged()
    }
}