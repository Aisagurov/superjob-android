package com.superjob.android.feature.home.di

import com.superjob.android.feature.home.HomeFragment
import dagger.Component

@[HomeScope Component(dependencies = [HomeDependencies::class])]
internal interface HomeComponent {

    fun inject(fragment: HomeFragment)

    @Component.Builder
    interface Builder {
        fun dependencies(dependencies: HomeDependencies): Builder
        fun build(): HomeComponent
    }
}