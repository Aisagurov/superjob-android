package com.superjob.android.core.data.mapper

import com.superjob.android.core.common.emptyIfNull
import com.superjob.android.core.model.Employment
import com.superjob.android.core.network.model.EmploymentNetwork
import com.superjob.android.core.network.model.OfferNetwork
import com.superjob.android.core.network.model.VacancyNetwork

internal fun EmploymentNetwork.asExternalModel() = Employment(
    offers = offers?.map(OfferNetwork::asExternalModel).emptyIfNull(emptyList()),
    vacancies = vacancies?.map(VacancyNetwork::asExternalModel).emptyIfNull(emptyList())
)