package com.superjob.android.core.model

data class HomeItem(
    val offers: List<Offer>,
    val title: String,
    val vacancies: List<Vacancy>,
    val vacanciesSize: Int,
)