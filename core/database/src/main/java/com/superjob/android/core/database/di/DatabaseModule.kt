package com.superjob.android.core.database.di

import android.app.Application
import androidx.room.Room
import com.superjob.android.core.common.DATABASE_NAME
import com.superjob.android.core.database.SuperJobDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DatabaseModule {
    @Singleton
    @Provides
    internal fun provideDatabase(application: Application): SuperJobDatabase {
        return Room.databaseBuilder(
            application,
            SuperJobDatabase::class.java,
            DATABASE_NAME
        ).build()
    }
}