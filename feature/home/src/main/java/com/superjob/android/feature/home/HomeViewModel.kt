package com.superjob.android.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.superjob.android.core.common.Result
import com.superjob.android.core.data.repository.favorites.FavoritesRepository
import com.superjob.android.core.data.repository.settings.SettingsRepository
import com.superjob.android.feature.home.di.HomeScope
import com.superjob.android.feature.home.usecase.GetEmploymentUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@HomeScope
internal class HomeViewModel @Inject constructor(
    private val getEmploymentUseCase: GetEmploymentUseCase,
    private val favoritesVacancyRepository: FavoritesRepository,
    private val settingsRepository: SettingsRepository,
): ViewModel() {

    val uiState: StateFlow<HomeUiState> =
        settingsRepository.isAllVacancies()
            .flatMapLatest { isAllVacancies ->
                getEmploymentUseCase()
                    .map { employmentResult ->
                        when (employmentResult) {
                            is Result.Success -> {
                                HomeUiState.Success(isAllVacancies, employmentResult.data)
                            }
                            is Result.Error -> {
                                HomeUiState.Error(employmentResult.message)
                            }
                        }
                    }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000L),
                initialValue = HomeUiState.Loading
            )

    fun updateFavoriteVacancy(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            favoritesVacancyRepository.updateFavoriteVacancy(id)
        }
    }

    fun setAllVacanciesConfig(isAllVacancies: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            settingsRepository.setAllVacanciesConfig(isAllVacancies)
        }
    }

    class Factory @Inject constructor(
        private val getEmploymentUseCase: Provider<GetEmploymentUseCase>,
        private val favoritesRepository: Provider<FavoritesRepository>,
        private val settingsRepository: Provider<SettingsRepository>,
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            require(modelClass == HomeViewModel::class.java)
            return HomeViewModel(
                getEmploymentUseCase = getEmploymentUseCase.get(),
                settingsRepository = settingsRepository.get(),
                favoritesVacancyRepository = favoritesRepository.get()
            ) as T
        }
    }
}