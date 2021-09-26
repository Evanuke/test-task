package com.evanuke.task.test.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.evanuke.task.test.databinding.ItemSearchRepositoryBinding

class RepositoryAdapter(
    private val clickListener: (String) -> Unit
) : ListAdapter<RepositoryUiModel, RepositoryViewHolder>(RepositoryDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val binding = ItemSearchRepositoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RepositoryViewHolder(binding, clickListener)
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    private class RepositoryDiffCallback : DiffUtil.ItemCallback<RepositoryUiModel>() {
        override fun areItemsTheSame(oldItem: RepositoryUiModel, newItem: RepositoryUiModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: RepositoryUiModel, newItem: RepositoryUiModel): Boolean {
            return oldItem == newItem
        }
    }
}
