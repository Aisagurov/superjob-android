package com.superjob.android.core.data.repository.settings

import kotlinx.coroutines.flow.Flow

interface SettingsRepository {

    fun isAllVacancies(): Flow<Boolean>

    suspend fun setAllVacanciesConfig(isAllVacancies: Boolean)
}