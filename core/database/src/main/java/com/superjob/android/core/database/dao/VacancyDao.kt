package com.superjob.android.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.superjob.android.core.database.model.VacancyEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface VacancyDao {
    @Query("SELECT * FROM vacancy")
    fun getVacancies(): Flow<List<VacancyEntity>>

    @Query("SELECT * FROM vacancy WHERE id = :id")
    fun getVacancy(id: String): Flow<VacancyEntity>

    @Query("SELECT * FROM vacancy WHERE id = :id")
    suspend fun vacancy(id: String): VacancyEntity?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertVacancies(items: List<VacancyEntity>)

    @Update
    suspend fun updateFavoriteVacancy(item: VacancyEntity): Int

    @Query("SELECT * FROM vacancy WHERE isSelected = 1")
    fun getFavoritesVacancies(): Flow<List<VacancyEntity>>

    @Query("SELECT id FROM vacancy WHERE isSelected = 1")
    suspend fun getFavoritesVacanciesIds(): List<String>
}