package com.square.lightstream.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.square.lightstream.databinding.ItemLocationBinding
import com.square.lightstream.location.Location

class LocationAdapter(private val listener: LocationAdapterClickListener,
                      private val locations: MutableList<Location> = mutableListOf()):
    RecyclerView.Adapter<LocationAdapter.LocationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val binding = ItemLocationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LocationViewHolder(listener, binding)
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        holder.bindLocation(locations[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(list: List<Location>) {
        locations.clear()
        locations.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = locations.size

    class LocationViewHolder(private val listener: LocationAdapterClickListener,
                             private val binding: ItemLocationBinding): RecyclerView.ViewHolder(binding.root) {


        fun bindLocation(location: Location) {
            binding.listener = listener
            binding.location = location
        }
    }

    interface LocationAdapterClickListener {
        fun onItemClick(location: Location)
    }
}