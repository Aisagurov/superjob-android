package com.superjob.android.core.data.di

import com.superjob.android.core.data.repository.employment.EmploymentRepository
import com.superjob.android.core.data.repository.employment.EmploymentRepositoryImpl
import com.superjob.android.core.data.repository.favorites.FavoritesRepository
import com.superjob.android.core.data.repository.favorites.FavoritesRepositoryImpl
import com.superjob.android.core.data.repository.settings.SettingsRepository
import com.superjob.android.core.data.repository.settings.SettingsRepositoryImpl
import com.superjob.android.core.data.vacancy.VacancyRepository
import com.superjob.android.core.data.vacancy.VacancyRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class DataModule {
    @Binds
    internal abstract fun bindEmploymentRepository(
        repository: EmploymentRepositoryImpl
    ): EmploymentRepository

    @Binds
    internal abstract fun bindFavoritesRepository(
        repository: FavoritesRepositoryImpl
    ): FavoritesRepository

    @Binds
    internal abstract fun bindVacancyRepository(
        repository: VacancyRepositoryImpl
    ): VacancyRepository

    @Binds
    internal abstract fun bindSettingsRepository(
        repository: SettingsRepositoryImpl
    ): SettingsRepository
}