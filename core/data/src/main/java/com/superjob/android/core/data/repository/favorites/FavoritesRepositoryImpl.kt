package com.superjob.android.core.data.repository.favorites

import com.superjob.android.core.common.Result
import com.superjob.android.core.data.mapper.asExternalModel
import com.superjob.android.core.database.dao.VacancyDao
import com.superjob.android.core.database.model.VacancyEntity
import com.superjob.android.core.model.Vacancy
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavoritesRepositoryImpl @Inject constructor(
    private val dao: VacancyDao,
): FavoritesRepository {

    override fun getFavoritesVacancies(): Flow<Result.Success<List<Vacancy>>> {
        return dao.getFavoritesVacancies().map { vacancies ->
            Result.Success(vacancies.map(VacancyEntity::asExternalModel))
        }
    }

    override suspend fun updateFavoriteVacancy(id: String): Vacancy? {
        dao.vacancy(id)?.let {
            val entity = VacancyEntity(
                id = it.id,
                lookingNumber = it.lookingNumber,
                title = it.title,
                addressTown = it.addressTown,
                addressStreet = it.addressStreet,
                addressHouse = it.addressHouse,
                company = it.company,
                experiencePreviewText = it.experiencePreviewText,
                experienceText = it.experienceText,
                publishedDate = it.publishedDate,
                salaryShort = it.salaryShort,
                salaryFull = it.salaryFull,
                schedules = it.schedules,
                appliedNumber = it.appliedNumber,
                description = it.description,
                responsibilities = it.responsibilities,
                questions = it.questions,
                isSelected = it.isSelected.not()
            )
            if (dao.updateFavoriteVacancy(entity) > 0) {
                return entity.asExternalModel()
            }
        }
        return null
    }
}