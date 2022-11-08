package com.square.lightstream.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.square.lightstream.databinding.ItemSimpleBinding

class StringAdapter(private val listener: StringAdapterClickListener,
                    private val residents: MutableList<String> = mutableListOf()) :
    Adapter<StringAdapter.CharacterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = ItemSimpleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(listener, binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bindResidents(residents[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(list: List<String>) {
        residents.clear()
        residents.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = residents.size

    class CharacterViewHolder(private val listener: StringAdapterClickListener, private val binding: ItemSimpleBinding): ViewHolder(binding.root) {

        fun bindResidents(residents: String) {
            binding.listener = listener
            binding.residents = residents
        }
    }

    interface StringAdapterClickListener {
        fun onItemClick(residents: String)
    }
}