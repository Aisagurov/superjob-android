package com.superjob.android.feature.favorites.adapter

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.superjob.android.core.model.Vacancy
import com.superjob.android.core.ui.R
import com.superjob.android.core.ui.databinding.ItemVacanciesBinding
import com.superjob.android.core.ui.util.createPublishedDate
import com.superjob.android.core.ui.util.getDeclensionHuman
import com.superjob.android.core.ui.util.showToast
import com.superjob.android.feature.favorites.adapter.listener.VacancyFavoriteClickListener
import com.superjob.android.feature.favorites.adapter.listener.VacancyItemClickListener
import com.superjob.android.feature.favorites.adapter.listener.VacancyReactionClickListener

internal class FavoritesViewHolder(
    private val binding: ItemVacanciesBinding,
): RecyclerView.ViewHolder(binding.root) {
    @SuppressLint("SetTextI18n")
    fun bind(
        item: Vacancy,
        vacancyItemClickListener: VacancyItemClickListener,
        vacancyFavoriteClickListener: VacancyFavoriteClickListener,
        vacancyReactionClickListener: VacancyReactionClickListener,
    ) = with(binding) {

        val context = root.context

        lookingNumberTextView.text = if (item.lookingNumber != 0) {
            context.getString(R.string.he_is_looking_at_it_now) + " " +
                    item.lookingNumber + " " + getDeclensionHuman(context, item.lookingNumber)
        } else ""

        favoriteImageView.setImageResource(
            if (item.isSelected) {
                R.drawable.ic_favorite
            } else {
                R.drawable.ic_favorite_border
            }
        )

        favoriteImageView.setOnClickListener {
            vacancyFavoriteClickListener.onFavoriteVacancyClick(item.id)
            if (item.isSelected) {
                context.showToast(context.getString(R.string.vacancy_removed_from_favorites))
            } else {
                context.showToast(context.getString(R.string.vacancy_added_to_favorites))
            }
        }

        titleTextView.text = item.title

        salaryTextView.text = item.salaryShort

        salaryTextView.visibility = if (item.salaryShort != "") View.VISIBLE else View.GONE

        addressTownTextView.text = item.addressTown

        companyTextView.text = item.company

        experienceTextView.text = item.experiencePreviewText

        publishedDateTextView.text = createPublishedDate(context, item.publishedDate)

        reactionButton.setOnClickListener {
            vacancyReactionClickListener.onVacancyReactionClick(item.title)
        }

        root.setOnClickListener {
            vacancyItemClickListener.onVacancyItemClick(item.id)
        }
    }
}