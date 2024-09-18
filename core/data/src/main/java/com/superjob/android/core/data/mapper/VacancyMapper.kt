package com.superjob.android.core.data.mapper

import com.superjob.android.core.common.emptyIfNull
import com.superjob.android.core.database.model.VacancyEntity
import com.superjob.android.core.model.Vacancy
import com.superjob.android.core.network.model.VacancyNetwork

internal fun VacancyNetwork.asEntity(items: List<String>) = VacancyEntity(
    id = id.emptyIfNull(""),
    lookingNumber = lookingNumber,
    title = title,
    addressTown = address?.town,
    addressStreet = address?.street,
    addressHouse = address?.house,
    company = company,
    experiencePreviewText = experience?.previewText,
    experienceText = experience?.text,
    publishedDate = publishedDate,
    salaryShort = salary?.short,
    salaryFull = salary?.full,
    schedules = schedules,
    appliedNumber = appliedNumber,
    description = description,
    responsibilities = responsibilities,
    questions = questions,
    isSelected = items.contains(id),
)

internal fun VacancyEntity.asExternalModel() = Vacancy(
    id = id,
    lookingNumber = lookingNumber.emptyIfNull(0),
    title = title.emptyIfNull(""),
    addressTown = addressTown.emptyIfNull(""),
    addressStreet = addressStreet.emptyIfNull(""),
    addressHouse = addressHouse.emptyIfNull(""),
    company = company.emptyIfNull(""),
    experiencePreviewText = experiencePreviewText.emptyIfNull(""),
    experienceText = experienceText.emptyIfNull(""),
    publishedDate = publishedDate.emptyIfNull(""),
    salaryShort = salaryShort.emptyIfNull(""),
    salaryFull = salaryFull.emptyIfNull(""),
    schedules = schedules.emptyIfNull(emptyList()),
    appliedNumber = appliedNumber.emptyIfNull(0),
    description = description.emptyIfNull(""),
    responsibilities = responsibilities.emptyIfNull(""),
    questions = questions.emptyIfNull(emptyList()),
    isSelected = isSelected,
)

internal fun VacancyNetwork.asExternalModel() = Vacancy(
    id = id.emptyIfNull(""),
    lookingNumber = lookingNumber.emptyIfNull(0),
    title = title.emptyIfNull(""),
    addressTown = address?.town.emptyIfNull(""),
    addressStreet = address?.street.emptyIfNull(""),
    addressHouse = address?.house.emptyIfNull(""),
    company = company.emptyIfNull(""),
    experiencePreviewText = experience?.previewText.emptyIfNull(""),
    experienceText = experience?.text.emptyIfNull(""),
    publishedDate = publishedDate.emptyIfNull(""),
    salaryShort = salary?.short.emptyIfNull(""),
    salaryFull = salary?.full.emptyIfNull(""),
    schedules = schedules.emptyIfNull(emptyList()),
    appliedNumber = appliedNumber.emptyIfNull(0),
    description = description.emptyIfNull(""),
    responsibilities = responsibilities.emptyIfNull(""),
    questions = questions.emptyIfNull(emptyList()),
    isSelected = false,
)