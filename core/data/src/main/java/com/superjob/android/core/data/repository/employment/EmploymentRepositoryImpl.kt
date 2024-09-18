package com.superjob.android.core.data.repository.employment

import com.superjob.android.core.model.Employment
import com.superjob.android.core.network.source.NetworkDataSource
import com.superjob.android.core.common.Result
import com.superjob.android.core.common.emptyIfNull
import com.superjob.android.core.data.mapper.asEntity
import com.superjob.android.core.data.mapper.asExternalModel
import com.superjob.android.core.database.dao.VacancyDao
import com.superjob.android.core.database.model.VacancyEntity
import com.superjob.android.core.model.Vacancy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class EmploymentRepositoryImpl @Inject constructor(
    private val networkDataSource: NetworkDataSource,
    private val dao: VacancyDao,
): EmploymentRepository {

    override fun getVacanciesFromDatabase(): Flow<Result.Success<List<Vacancy>>> {
        return dao.getVacancies().map { vacancies ->
            Result.Success(vacancies.map(VacancyEntity::asExternalModel))
        }
    }

    override fun getEmploymentFromNetwork(): Flow<Result<Employment>> = flow {
        when (val result = networkDataSource.getEmployment()) {
            is Result.Success -> {
                dao.insertVacancies(result.data.vacancies
                    ?.map { item -> item.asEntity(dao.getFavoritesVacanciesIds()) }
                    .emptyIfNull(emptyList()))
                emit(Result.Success(result.data.asExternalModel()))
            }
            is Result.Error -> {
                emit(Result.Error(result.message))
            }
        }
    }.flowOn(Dispatchers.IO)
}