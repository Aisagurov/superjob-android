package com.superjob.android.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.superjob.android.core.database.converters.Converters
import com.superjob.android.core.database.dao.VacancyDao
import com.superjob.android.core.database.model.VacancyEntity

@Database(entities = [VacancyEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
internal abstract class SuperJobDatabase: RoomDatabase() {
    abstract fun vacancyDao(): VacancyDao
}