package com.fernandopretell.retokonfio.core.preferences

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore by preferencesDataStore("app_preferences")

@Singleton
class PreferencesDataStore @Inject constructor(private val context: Context) {

    suspend fun getLong(key: String): Long? {
        val prefKey = longPreferencesKey(key)
        val prefs = context.dataStore.data.first()
        return prefs[prefKey]
    }

    suspend fun putLong(key: String, value: Long) {
        val prefKey = longPreferencesKey(key)
        context.dataStore.edit { prefs ->
            prefs[prefKey] = value
        }
    }
}
