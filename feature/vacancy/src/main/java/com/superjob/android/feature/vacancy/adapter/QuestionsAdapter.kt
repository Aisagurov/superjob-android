package com.superjob.android.feature.vacancy.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.superjob.android.feature.vacancy.databinding.ItemQuestionsBinding

internal class QuestionsAdapter: RecyclerView.Adapter<QuestionsViewHolder>() {

    private val items = arrayListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionsViewHolder {
        return QuestionsViewHolder(
            ItemQuestionsBinding.inflate(
                LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: QuestionsViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun update(list: List<String>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }
}