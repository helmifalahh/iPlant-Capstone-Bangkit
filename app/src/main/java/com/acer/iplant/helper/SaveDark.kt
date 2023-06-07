package com.acer.iplant.helper

import android.content.Context
import android.content.SharedPreferences
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SaveDark private constructor(private val settingsDataStore: DataStore<Preferences>) {

    fun getThemeSetting(): Flow<Boolean> =
        settingsDataStore.data.map { preferences ->
            preferences[THEME_KEY] ?: false
        }

    suspend fun saveThemeSetting(isDarkModeActive: Boolean) {
        settingsDataStore.edit { preferences ->
            preferences[THEME_KEY] = isDarkModeActive
        }
    }

    companion object{
        private val THEME_KEY = booleanPreferencesKey("theme_setting")
        @Volatile
        private var INSTANCE: SaveDark? = null

        fun getInstance(database: DataStore<Preferences>): SaveDark{
            return INSTANCE ?: synchronized(this){
                val instance = SaveDark(database)
                INSTANCE = instance
                instance
            }
        }
    }
}