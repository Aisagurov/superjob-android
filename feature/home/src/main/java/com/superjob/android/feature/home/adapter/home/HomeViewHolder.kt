package com.superjob.android.feature.home.adapter.home

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.superjob.android.core.model.HomeItem
import com.superjob.android.core.ui.R
import com.superjob.android.core.ui.util.getDeclensionVacancies
import com.superjob.android.feature.home.adapter.offers.OffersAdapter
import com.superjob.android.feature.home.adapter.vacancies.VacanciesAdapter
import com.superjob.android.feature.home.adapter.offers.listener.OfferItemClickListener
import com.superjob.android.feature.home.adapter.vacancies.listener.VacanciesClickListener
import com.superjob.android.feature.home.adapter.vacancies.listener.VacancyFavoriteClickListener
import com.superjob.android.feature.home.adapter.vacancies.listener.VacancyItemClickListener
import com.superjob.android.feature.home.adapter.vacancies.listener.VacancyReactionClickListener
import com.superjob.android.feature.home.databinding.ItemHomeBinding

internal class HomeViewHolder(
    private val binding: ItemHomeBinding,
): RecyclerView.ViewHolder(binding.root) {
    @SuppressLint("SetTextI18n")
    fun bind(
        item: HomeItem,
        offerItemClickListener: OfferItemClickListener,
        vacanciesClickListener: VacanciesClickListener,
        vacancyItemClickListener: VacancyItemClickListener,
        vacancyFavoriteClickListener: VacancyFavoriteClickListener,
        vacancyReactionClickListener: VacancyReactionClickListener,
    ) = with(binding) {

        val context = root.context

        val offersAdapter = OffersAdapter(offerItemClickListener)
        offersRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        offersRecyclerView.adapter = offersAdapter
        offersAdapter.update(item.offers)

        titleTextView.text = item.title
        titleTextView.visibility = if (item.title != "") View.VISIBLE else View.GONE

        offersSizeTextView.text = item.vacanciesSize.toString() +
                " " + getDeclensionVacancies(context, item.vacanciesSize)
        offersSizeLayout.visibility = if (item.title != "") View.GONE else View.VISIBLE

        val vacanciesAdapter = VacanciesAdapter(
            vacancyItemClickListener,
            vacancyFavoriteClickListener,
            vacancyReactionClickListener,
        )
        vacanciesRecyclerView.layoutManager = LinearLayoutManager(context)
        vacanciesRecyclerView.adapter = vacanciesAdapter
        vacanciesAdapter.update(item.vacancies)

        moreVacanciesButton.setOnClickListener {
            vacanciesClickListener.showAllVacancies()
        }
        moreVacanciesButton.text =
            context.getString(R.string.more) + " " + (item.vacanciesSize - 3) + " " +
                    getDeclensionVacancies(context, item.vacanciesSize - 3)
        moreVacanciesButton.visibility = if (item.title != "") View.VISIBLE else View.GONE
    }
}