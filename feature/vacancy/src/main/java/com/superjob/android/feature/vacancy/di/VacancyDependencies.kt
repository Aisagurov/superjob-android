package com.superjob.android.feature.vacancy.di

import com.superjob.android.core.data.repository.favorites.FavoritesRepository
import com.superjob.android.core.data.vacancy.VacancyRepository

interface VacancyDependencies {
    val favoritesRepository: FavoritesRepository
    val vacancyRepository: VacancyRepository
}