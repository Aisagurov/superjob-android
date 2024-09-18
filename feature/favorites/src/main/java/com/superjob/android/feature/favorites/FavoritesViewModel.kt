package com.superjob.android.feature.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.superjob.android.core.data.repository.favorites.FavoritesRepository
import com.superjob.android.feature.favorites.di.FavoritesScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@FavoritesScope
internal class FavoritesViewModel @Inject constructor(
    private val repository: FavoritesRepository
): ViewModel() {
    val uiState: StateFlow<FavoritesUiState> =
        repository.getFavoritesVacancies()
            .map { successResult ->
                if (successResult.data.isNotEmpty()) {
                    FavoritesUiState.Success(successResult.data)
                } else {
                    FavoritesUiState.Empty
                }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000L),
                initialValue = FavoritesUiState.Empty
            )

    fun updateFavoriteVacancy(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateFavoriteVacancy(id)
        }
    }

    class Factory @Inject constructor(
        private val repository: Provider<FavoritesRepository>,
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            require(modelClass == FavoritesViewModel::class.java)
            return FavoritesViewModel(
                repository = repository.get()
            ) as T
        }
    }
}