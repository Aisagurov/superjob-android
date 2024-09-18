package com.superjob.android.core.datastore

import android.app.Application
import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.superjob.android.core.common.DATA_STORE_NAME
import com.superjob.android.core.common.emptyIfNull
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore by preferencesDataStore(DATA_STORE_NAME)

@Singleton
class SuperJobDataStore @Inject constructor(application: Application) {

    private val settingsDataStore = application.dataStore

    private val isAllVacanciesKey = booleanPreferencesKey("isAllVacanciesKey")

    val isAllVacancies: Flow<Boolean> = settingsDataStore.data.map { preferences ->
        preferences[isAllVacanciesKey].emptyIfNull(false)
    }

    suspend fun setVacanciesConfig(isAllVacancies: Boolean) {
        settingsDataStore.edit { settings ->
            settings[isAllVacanciesKey] = isAllVacancies
        }
    }
}