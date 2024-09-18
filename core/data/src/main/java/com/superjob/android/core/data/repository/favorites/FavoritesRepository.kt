package com.superjob.android.core.data.repository.favorites

import com.superjob.android.core.common.Result
import com.superjob.android.core.model.Vacancy
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {

    fun getFavoritesVacancies(): Flow<Result.Success<List<Vacancy>>>

    suspend fun updateFavoriteVacancy(id: String): Vacancy?
}