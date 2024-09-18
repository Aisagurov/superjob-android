package com.superjob.android.feature.home

import com.superjob.android.core.model.Employment

internal sealed interface HomeUiState {
    data class Success(val isAllVacancies: Boolean, val employment: Employment): HomeUiState
    data class Error(val message: String): HomeUiState
    data object Loading: HomeUiState
}