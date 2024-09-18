package com.superjob.android.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.superjob.android.core.data.repository.favorites.FavoritesRepository
import com.superjob.android.core.model.Vacancy
import com.superjob.android.di.MainScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject
import javax.inject.Provider

@MainScope
class MainViewModel @Inject constructor(private val repository: FavoritesRepository): ViewModel() {

    fun getFavoritesVacancies(): StateFlow<List<Vacancy>> =
        repository.getFavoritesVacancies()
            .map { successResult -> successResult.data }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000L),
                initialValue = emptyList()
            )

    class Factory @Inject constructor(
        private val repository: Provider<FavoritesRepository>,
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            require(modelClass == MainViewModel::class.java)
            return MainViewModel(
                repository = repository.get()
            ) as T
        }
    }
}