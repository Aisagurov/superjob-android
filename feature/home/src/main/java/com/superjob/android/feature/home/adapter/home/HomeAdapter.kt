package com.superjob.android.feature.home.adapter.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.superjob.android.core.model.HomeItem
import com.superjob.android.feature.home.adapter.offers.listener.OfferItemClickListener
import com.superjob.android.feature.home.adapter.vacancies.listener.VacanciesClickListener
import com.superjob.android.feature.home.adapter.vacancies.listener.VacancyFavoriteClickListener
import com.superjob.android.feature.home.adapter.vacancies.listener.VacancyItemClickListener
import com.superjob.android.feature.home.adapter.vacancies.listener.VacancyReactionClickListener
import com.superjob.android.feature.home.databinding.ItemHomeBinding

internal class HomeAdapter(
    private val offerItemClickListener: OfferItemClickListener,
    private val vacanciesClickListener: VacanciesClickListener,
    private val vacancyItemClickListener: VacancyItemClickListener,
    private val vacancyFavoriteClickListener: VacancyFavoriteClickListener,
    private val vacancyReactionClickListener: VacancyReactionClickListener,
): RecyclerView.Adapter<HomeViewHolder>() {

    private var items = arrayListOf<HomeItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(
            ItemHomeBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(
            items[position],
            offerItemClickListener,
            vacanciesClickListener,
            vacancyItemClickListener,
            vacancyFavoriteClickListener,
            vacancyReactionClickListener,
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun update(list: List<HomeItem>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }
}