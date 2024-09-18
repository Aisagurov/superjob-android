package com.superjob.android.core.data.repository.employment

import com.superjob.android.core.common.Result
import com.superjob.android.core.model.Employment
import com.superjob.android.core.model.Vacancy
import kotlinx.coroutines.flow.Flow

interface EmploymentRepository {

    fun getVacanciesFromDatabase(): Flow<Result.Success<List<Vacancy>>>

    fun getEmploymentFromNetwork(): Flow<Result<Employment>>
}