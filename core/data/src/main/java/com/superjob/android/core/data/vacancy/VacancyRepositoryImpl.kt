package com.superjob.android.core.data.vacancy

import com.superjob.android.core.data.mapper.asExternalModel
import com.superjob.android.core.database.dao.VacancyDao
import com.superjob.android.core.database.model.VacancyEntity
import com.superjob.android.core.model.Vacancy
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class VacancyRepositoryImpl @Inject constructor(
    private val dao: VacancyDao,
): VacancyRepository {
    override fun getVacancy(id: String): Flow<Vacancy> =
        dao.getVacancy(id).map(VacancyEntity::asExternalModel)
}