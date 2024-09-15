package com.wilmer.fooddataandlist.data.cache

import java.util.Timer
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.TimeUnit
import kotlin.concurrent.fixedRateTimer
import kotlin.concurrent.schedule

class Cache<K, V>(private val maxSize: Int = 100, private val expireAfterMillis: Long = TimeUnit.HOURS.toMillis(24)) {

    private val cacheMap = ConcurrentHashMap<K, CacheItem<V>>()
    private val cleaner = fixedRateTimer("cache-cleaner", daemon = true, period = expireAfterMillis) {
        cleanUp()
    }

    private data class CacheItem<V>(val value: V, val creationTime: Long)

    @Synchronized
    fun put(key: K, value: V) {
        if (cacheMap.size >= maxSize) {
            val oldestKey = cacheMap.keys.iterator().next()
            cacheMap.remove(oldestKey)
        }
        cacheMap[key] = CacheItem(value, System.currentTimeMillis())
    }

    fun get(key: K): V? {
        val item = cacheMap[key]
        return if (item != null && !isExpired(item)) {
            item.value
        } else {
            cacheMap.remove(key) // Clean up expired item
            null
        }
    }

    fun remove(key: K) {
        cacheMap.remove(key)
    }

    fun clear() {
        cacheMap.clear()
    }

    private fun isExpired(item: CacheItem<V>): Boolean {
        return System.currentTimeMillis() - item.creationTime > expireAfterMillis
    }

    private fun cleanUp() {
        val now = System.currentTimeMillis()
        cacheMap.entries.removeIf { now - it.value.creationTime > expireAfterMillis }
    }
}
