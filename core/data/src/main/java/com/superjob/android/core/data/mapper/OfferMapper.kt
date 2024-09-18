package com.superjob.android.core.data.mapper

import com.superjob.android.core.common.emptyIfNull
import com.superjob.android.core.model.Offer
import com.superjob.android.core.network.model.OfferNetwork

internal fun OfferNetwork.asExternalModel() = Offer(
    id = id.emptyIfNull(""),
    title = title.emptyIfNull(""),
    buttonText = button?.text.emptyIfNull(""),
    link = link.emptyIfNull(""),
)