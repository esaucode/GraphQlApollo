package com.esaudev.graphqlapollo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.esaudev.graphqlapollo.CharactersListQuery
import com.esaudev.graphqlapollo.databinding.ItemCharacterBinding

class CharactersListAdapter(
): ListAdapter<CharactersListQuery.Result, BaseListViewHolder<*>>(DiffUtilCallback)  {

    private object DiffUtilCallback : DiffUtil.ItemCallback<CharactersListQuery.Result>() {
        override fun areItemsTheSame(oldItem: CharactersListQuery.Result, newItem: CharactersListQuery.Result): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: CharactersListQuery.Result, newItem: CharactersListQuery.Result): Boolean = oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseListViewHolder<*> {
        val itemBinding = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BindViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: BaseListViewHolder<*>, position: Int) {
        when (holder) {
            is BindViewHolder -> holder.bind(getItem(position), position)
        }
    }

    inner class BindViewHolder(private val binding: ItemCharacterBinding) : BaseListViewHolder<CharactersListQuery.Result>(binding.root) {

        override fun bind(item: CharactersListQuery.Result, position: Int) = with(binding) {

            characterId.text = item.id
            characterName.text = item.name
            characterSpecie.text = item.species
        }
    }
}