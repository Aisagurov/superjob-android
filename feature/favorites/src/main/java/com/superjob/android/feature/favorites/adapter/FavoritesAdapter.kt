package com.superjob.android.feature.favorites.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.superjob.android.core.model.Vacancy
import com.superjob.android.core.ui.databinding.ItemVacanciesBinding
import com.superjob.android.feature.favorites.adapter.listener.VacancyFavoriteClickListener
import com.superjob.android.feature.favorites.adapter.listener.VacancyItemClickListener
import com.superjob.android.feature.favorites.adapter.listener.VacancyReactionClickListener

internal class FavoritesAdapter(
    private val vacancyItemClickListener: VacancyItemClickListener,
    private val vacancyFavoriteClickListener: VacancyFavoriteClickListener,
    private val vacancyReactionClickListener: VacancyReactionClickListener,
): RecyclerView.Adapter<FavoritesViewHolder>() {

    private val items = arrayListOf<Vacancy>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        return FavoritesViewHolder(
            ItemVacanciesBinding.inflate(
                LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        holder.bind(
            items[position],
            vacancyItemClickListener,
            vacancyFavoriteClickListener,
            vacancyReactionClickListener,
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun update(list: List<Vacancy>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }
}