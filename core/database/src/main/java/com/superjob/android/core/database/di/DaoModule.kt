package com.superjob.android.core.database.di

import com.superjob.android.core.database.SuperJobDatabase
import com.superjob.android.core.database.dao.VacancyDao
import dagger.Module
import dagger.Provides

@Module
class DaoModule {
    @Provides
    internal fun provideVacancyDao(database: SuperJobDatabase): VacancyDao {
        return database.vacancyDao()
    }
}