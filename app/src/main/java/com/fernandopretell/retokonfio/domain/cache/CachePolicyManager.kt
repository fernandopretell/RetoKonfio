package com.fernandopretell.retokonfio.domain.cache

import com.fernandopretell.retokonfio.core.network.NetworkMonitor
import com.fernandopretell.retokonfio.core.preferences.PreferencesDataStore
import javax.inject.Inject

class CachePolicyManager @Inject constructor(
    private val preferences: PreferencesDataStore,
    private val networkMonitor: NetworkMonitor
): CachePolicy {
    companion object {
        private const val CACHE_TIMEOUT = 60 * 60 * 1000L
        private const val KEY_LAST_UPDATED = "dogs_last_updated"
    }

    override suspend fun shouldForceRefresh(userRequestedRefresh: Boolean): Boolean {
        val lastUpdated = preferences.getLong(KEY_LAST_UPDATED)
        val now = System.currentTimeMillis()

        return userRequestedRefresh ||
               lastUpdated == null ||
               now - lastUpdated > CACHE_TIMEOUT ||
               networkMonitor.wasOfflineAndNowOnline()
    }

    override suspend fun updateTimestamp() {
        preferences.putLong(KEY_LAST_UPDATED, System.currentTimeMillis())
    }
}
