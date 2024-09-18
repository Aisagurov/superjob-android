package com.superjob.android.di

import com.superjob.android.ui.MainActivity
import dagger.Component

@[MainScope Component(dependencies = [MainDependencies::class])]
interface MainComponent {

    fun inject(activity: MainActivity)

    @Component.Builder
    interface Builder {
        fun dependencies(dependencies: MainDependencies): Builder
        fun build(): MainComponent
    }
}