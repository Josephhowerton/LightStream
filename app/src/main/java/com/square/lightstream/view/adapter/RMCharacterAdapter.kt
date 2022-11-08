package com.square.lightstream.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.square.lightstream.character.RMCharacter
import com.square.lightstream.databinding.ItemCharacterBinding

class RMCharacterAdapter(private val listener: CharacterAdapterClickListener,
                         private val characters: MutableList<RMCharacter> = mutableListOf()) :
    Adapter<RMCharacterAdapter.CharacterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(listener, binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bindCharacter(characters[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(list: List<RMCharacter>) {
        characters.clear()
        characters.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = characters.size

    class CharacterViewHolder(private val listener: CharacterAdapterClickListener, private val binding: ItemCharacterBinding): ViewHolder(binding.root) {

        fun bindCharacter(rmCharacter: RMCharacter) {
            binding.listener = listener
            binding.character = rmCharacter
            Glide.with(binding.root)
                .load(rmCharacter.image)
                .into(binding.imageViewProfile)
        }
    }

    interface CharacterAdapterClickListener {
        fun onItemClick(character: RMCharacter)
    }
}