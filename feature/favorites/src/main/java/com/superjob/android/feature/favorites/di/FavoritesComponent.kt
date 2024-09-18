package com.superjob.android.feature.favorites.di

import com.superjob.android.feature.favorites.FavoritesFragment
import dagger.Component

@[FavoritesScope Component(dependencies = [FavoritesDependencies::class])]
internal interface FavoritesComponent {

    fun inject(fragment: FavoritesFragment)

    @Component.Builder
    interface Builder {
        fun dependencies(dependencies: FavoritesDependencies): Builder
        fun build(): FavoritesComponent
    }
}