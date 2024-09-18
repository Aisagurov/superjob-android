package com.superjob.android.feature.home.usecase

import com.superjob.android.core.data.repository.employment.EmploymentRepository
import com.superjob.android.core.model.Employment
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import com.superjob.android.core.common.Result
import javax.inject.Inject

class GetEmploymentUseCase @Inject constructor(
    private val repository: EmploymentRepository,
) {
    operator fun invoke(): Flow<Result<Employment>> {
        return combine(
            repository.getVacanciesFromDatabase(),
            repository.getEmploymentFromNetwork(),
        ) { favoritesVacanciesResult, employmentResult ->
            when (employmentResult) {
                is Result.Success -> {
                    Result.Success(
                        Employment(
                            offers = employmentResult.data.offers,
                            vacancies = favoritesVacanciesResult.data
                        )
                    )
                }
                is Result.Error -> {
                    Result.Error(employmentResult.message)
                }
            }
        }
    }
}