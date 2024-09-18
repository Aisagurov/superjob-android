package com.superjob.android.feature.vacancy.adapter

import androidx.recyclerview.widget.RecyclerView
import com.superjob.android.feature.vacancy.databinding.ItemQuestionsBinding

internal class QuestionsViewHolder(
    private val binding: ItemQuestionsBinding,
): RecyclerView.ViewHolder(binding.root) {
    fun bind(item: String) = with(binding) {
        root.text = item
    }
}