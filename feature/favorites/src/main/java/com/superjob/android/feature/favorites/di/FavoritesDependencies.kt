package com.superjob.android.feature.favorites.di

import com.superjob.android.core.data.repository.favorites.FavoritesRepository

interface FavoritesDependencies {
    val favoritesRepository: FavoritesRepository
}