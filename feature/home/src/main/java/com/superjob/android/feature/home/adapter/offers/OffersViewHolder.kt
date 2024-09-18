package com.superjob.android.feature.home.adapter.offers

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.superjob.android.core.model.Offer
import com.superjob.android.core.ui.R
import com.superjob.android.feature.home.adapter.offers.listener.OfferItemClickListener
import com.superjob.android.feature.home.databinding.ItemOffersBinding

internal class OffersViewHolder(
    private val binding: ItemOffersBinding,
): RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Offer, clickListener: OfferItemClickListener) = with(binding) {
        val linkImage = when (item.id) {
            "level_up_resume" -> R.drawable.ic_level_up_resume
            "temporary_job" -> R.drawable.ic_temporary_job
            else -> R.drawable.ic_near_vacancies
        }
        linkImageView.setImageResource(linkImage)
        linkImageView.visibility = if (item.id == "") View.INVISIBLE else View.VISIBLE
        titleTextView.text = item.title
        buttonTextView.text = item.buttonText
        root.setOnClickListener {
            clickListener.onOfferItemClick(item.link)
        }
    }
}