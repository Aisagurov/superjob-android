package com.superjob.android.core.data.vacancy

import com.superjob.android.core.model.Vacancy
import kotlinx.coroutines.flow.Flow

interface VacancyRepository {
    fun getVacancy(id: String): Flow<Vacancy>
}