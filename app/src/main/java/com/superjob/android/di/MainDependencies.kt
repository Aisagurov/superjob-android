package com.superjob.android.di

import com.superjob.android.core.data.repository.favorites.FavoritesRepository

interface MainDependencies {
    val favoritesRepository: FavoritesRepository
}