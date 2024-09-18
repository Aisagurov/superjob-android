package com.superjob.android

import android.app.Application
import com.superjob.android.di.AppComponent
import com.superjob.android.di.DaggerAppComponent
import com.superjob.android.di.MainDependencies
import com.superjob.android.di.MainProvider
import com.superjob.android.feature.favorites.di.FavoritesDependencies
import com.superjob.android.feature.favorites.di.FavoritesProvider
import com.superjob.android.feature.home.di.HomeDependencies
import com.superjob.android.feature.home.di.HomeProvider
import com.superjob.android.feature.vacancy.di.VacancyDependencies
import com.superjob.android.feature.vacancy.di.VacancyProvider

class SuperJobApplication:
    Application(),
    MainProvider,
    HomeProvider,
    FavoritesProvider,
    VacancyProvider
{
    private val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder().application(this).build()
    }

    override val mainDependencies: MainDependencies = appComponent
    override val homeDependencies: HomeDependencies = appComponent
    override val favoritesDependencies: FavoritesDependencies = appComponent
    override val vacancyDependencies: VacancyDependencies = appComponent
}