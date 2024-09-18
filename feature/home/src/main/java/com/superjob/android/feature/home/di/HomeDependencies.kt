package com.superjob.android.feature.home.di

import com.superjob.android.core.data.repository.employment.EmploymentRepository
import com.superjob.android.core.data.repository.favorites.FavoritesRepository
import com.superjob.android.core.data.repository.settings.SettingsRepository

interface HomeDependencies {
    val employmentRepository: EmploymentRepository
    val favoritesRepository: FavoritesRepository
    val settingsRepository: SettingsRepository
}