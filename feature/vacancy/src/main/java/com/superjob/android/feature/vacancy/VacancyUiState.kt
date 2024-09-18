package com.superjob.android.feature.vacancy

import com.superjob.android.core.model.Vacancy

internal sealed interface VacancyUiState {
    data class Success(val vacancy: Vacancy): VacancyUiState
    data object Loading: VacancyUiState
}