package com.superjob.android.core.data.repository.settings

import com.superjob.android.core.datastore.SuperJobDataStore
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(
    private val dataStore: SuperJobDataStore,
): SettingsRepository {

    override fun isAllVacancies(): Flow<Boolean> {
        return dataStore.isAllVacancies
    }

    override suspend fun setAllVacanciesConfig(isAllVacancies: Boolean) {
        dataStore.setVacanciesConfig(isAllVacancies)
    }
}