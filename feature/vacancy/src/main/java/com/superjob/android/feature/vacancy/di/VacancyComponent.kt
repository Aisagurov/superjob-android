package com.superjob.android.feature.vacancy.di

import com.superjob.android.feature.vacancy.VacancyFragment
import dagger.Component

@[VacancyScope Component(dependencies = [VacancyDependencies::class])]
internal interface VacancyComponent {

    fun inject(fragment: VacancyFragment)

    @Component.Builder
    interface Builder {
        fun dependencies(dependencies: VacancyDependencies): Builder
        fun build(): VacancyComponent
    }
}