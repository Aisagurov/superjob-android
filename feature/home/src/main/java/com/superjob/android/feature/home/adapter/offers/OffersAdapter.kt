package com.superjob.android.feature.home.adapter.offers

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.superjob.android.core.model.Offer
import com.superjob.android.feature.home.adapter.offers.listener.OfferItemClickListener
import com.superjob.android.feature.home.databinding.ItemOffersBinding

internal class OffersAdapter(
    private val clickListener: OfferItemClickListener,
): RecyclerView.Adapter<OffersViewHolder>() {

    private val items = arrayListOf<Offer>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OffersViewHolder {
        return OffersViewHolder(
            ItemOffersBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: OffersViewHolder, position: Int) {
        holder.bind(items[position], clickListener)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun update(list: List<Offer>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }
}