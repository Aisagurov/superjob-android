package com.superjob.android.feature.favorites

import com.superjob.android.core.model.Vacancy

internal sealed interface FavoritesUiState {
    data class Success(val vacancies: List<Vacancy>): FavoritesUiState
    data object Empty: FavoritesUiState
}