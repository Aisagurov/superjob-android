package com.superjob.android.feature.vacancy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.superjob.android.core.data.repository.favorites.FavoritesRepository
import com.superjob.android.core.data.vacancy.VacancyRepository
import com.superjob.android.feature.vacancy.di.VacancyScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@VacancyScope
internal class VacancyViewModel @Inject constructor(
    private val vacancyRepository: VacancyRepository,
    private val favoritesRepository: FavoritesRepository,
): ViewModel() {

    fun getVacancy(id: String): StateFlow<VacancyUiState> =
        vacancyRepository.getVacancy(id)
            .map(VacancyUiState::Success)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000L),
                initialValue = VacancyUiState.Loading
            )

    fun updateFavoriteVacancy(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            favoritesRepository.updateFavoriteVacancy(id)
        }
    }

    class Factory @Inject constructor(
        private val vacancyRepository: Provider<VacancyRepository>,
        private val favoritesRepository: Provider<FavoritesRepository>,
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            require(modelClass == VacancyViewModel::class.java)
            return VacancyViewModel(
                vacancyRepository = vacancyRepository.get(),
                favoritesRepository = favoritesRepository.get()
            ) as T
        }
    }
}