package com.superjob.android.di

import android.app.Application
import com.superjob.android.core.data.di.DataModule
import com.superjob.android.core.data.repository.employment.EmploymentRepository
import com.superjob.android.core.data.repository.favorites.FavoritesRepository
import com.superjob.android.core.data.repository.settings.SettingsRepository
import com.superjob.android.core.data.vacancy.VacancyRepository
import com.superjob.android.core.database.di.DaoModule
import com.superjob.android.core.database.di.DatabaseModule
import com.superjob.android.core.network.di.DataSourceModule
import com.superjob.android.core.network.di.NetworkModule
import com.superjob.android.feature.favorites.di.FavoritesDependencies
import com.superjob.android.feature.home.di.HomeDependencies
import com.superjob.android.feature.vacancy.di.VacancyDependencies
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@[Singleton Component(modules = [
    NetworkModule::class,
    DataSourceModule::class,
    DatabaseModule::class,
    DaoModule::class,
    DataModule::class,
])]
interface AppComponent:
    MainDependencies,
    HomeDependencies,
    FavoritesDependencies,
    VacancyDependencies
{
    override val employmentRepository: EmploymentRepository
    override val favoritesRepository: FavoritesRepository
    override val vacancyRepository: VacancyRepository
    override val settingsRepository: SettingsRepository

    @Component.Builder
    interface Builder {
        fun application(@BindsInstance application: Application): Builder
        fun build(): AppComponent
    }
}