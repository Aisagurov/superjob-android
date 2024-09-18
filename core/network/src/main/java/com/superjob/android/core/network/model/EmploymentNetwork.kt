package com.superjob.android.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EmploymentNetwork(
    @SerialName("offers") val offers: List<OfferNetwork>? = null,
    @SerialName("vacancies") val vacancies: List<VacancyNetwork>? = null,
)

@Serializable
data class OfferNetwork(
    @SerialName("id") val id: String? = null,
    @SerialName("title") val title: String? = null,
    @SerialName("button") val button: ButtonNetwork? = null,
    @SerialName("link") val link: String? = null,
)

@Serializable
data class ButtonNetwork(
    @SerialName("text") val text: String? = null,
)

@Serializable
data class VacancyNetwork(
    @SerialName("id") val id: String? = null,
    @SerialName("lookingNumber") val lookingNumber: Int? = null,
    @SerialName("title") val title: String? = null,
    @SerialName("address") val address: AddressNetwork? = null,
    @SerialName("company") val company: String? = null,
    @SerialName("experience") val experience: ExperienceNetwork? = null,
    @SerialName("publishedDate") val publishedDate: String? = null,
    @SerialName("isFavorite") val isFavorite: Boolean? = null,
    @SerialName("salary") val salary: SalaryNetwork? = null,
    @SerialName("schedules") val schedules: List<String>? = null,
    @SerialName("appliedNumber") val appliedNumber: Int? = null,
    @SerialName("description") val description: String? = null,
    @SerialName("responsibilities") val responsibilities: String? = null,
    @SerialName("questions") val questions: List<String>? = null,
)

@Serializable
data class AddressNetwork(
    @SerialName("town") val town: String? = null,
    @SerialName("street") val street: String? = null,
    @SerialName("house") val house: String? = null,
)

@Serializable
data class ExperienceNetwork(
    @SerialName("previewText") val previewText: String? = null,
    @SerialName("text") val text: String? = null,
)

@Serializable
data class SalaryNetwork(
    @SerialName ("short") val short: String? = null,
    @SerialName ("full") val full: String? = null,
)