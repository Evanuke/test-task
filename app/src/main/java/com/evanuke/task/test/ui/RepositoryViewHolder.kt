package com.evanuke.task.test.ui

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.evanuke.task.test.databinding.ItemSearchRepositoryBinding

class RepositoryViewHolder(
    private val binding: ItemSearchRepositoryBinding,
    private val clickListener: (String) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: RepositoryUiModel) = with(binding) {
        name.text = item.name
        description.apply {
            if (!item.description.isNullOrEmpty()) {
                isVisible = true
                text = item.description
            }
        }
        language.apply {
            if (!item.description.isNullOrEmpty()) {
                isVisible = true
                text = item.language
            }
        }
        starsValue.text = item.stars.toString()

        root.setOnClickListener { clickListener(item.url) }
    }
}
