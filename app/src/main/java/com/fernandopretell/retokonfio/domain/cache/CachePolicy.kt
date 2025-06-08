package com.fernandopretell.retokonfio.domain.cache

interface CachePolicy {
    suspend fun shouldForceRefresh(userRequestedRefresh: Boolean): Boolean
    suspend fun updateTimestamp()
}
