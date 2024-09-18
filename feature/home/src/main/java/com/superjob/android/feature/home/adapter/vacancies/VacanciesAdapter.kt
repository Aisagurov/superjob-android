package com.superjob.android.feature.home.adapter.vacancies

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.superjob.android.core.model.Vacancy
import com.superjob.android.core.ui.databinding.ItemVacanciesBinding
import com.superjob.android.feature.home.adapter.vacancies.listener.VacancyFavoriteClickListener
import com.superjob.android.feature.home.adapter.vacancies.listener.VacancyItemClickListener
import com.superjob.android.feature.home.adapter.vacancies.listener.VacancyReactionClickListener

internal class VacanciesAdapter(
    private val vacancyItemClickListener: VacancyItemClickListener,
    private val vacancyFavoriteClickListener: VacancyFavoriteClickListener,
    private val vacancyReactionClickListener: VacancyReactionClickListener,
): RecyclerView.Adapter<VacanciesViewHolder>() {

    private val items = arrayListOf<Vacancy>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VacanciesViewHolder {
        return VacanciesViewHolder(
            ItemVacanciesBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: VacanciesViewHolder, position: Int) {
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