package com.wilmer.fooddataandlist.data.cache

import java.util.concurrent.ConcurrentHashMap

class Cache<K, V>(private val maxSize: Int = 100) {

    private val cacheMap = ConcurrentHashMap<K, V>()

    @Synchronized
    fun put(key: K, value: V) {
        if (cacheMap.size >= maxSize) {
            val oldestKey = cacheMap.keys.iterator().next()
            cacheMap.remove(oldestKey)
        }
        cacheMap[key] = value
    }

    fun get(key: K): V? {
        return cacheMap[key]
    }

    fun remove(key: K) {
        cacheMap.remove(key)
    }

    fun clear() {
        cacheMap.clear()
    }

    fun contains(key: K): Boolean {
        return cacheMap.containsKey(key)
    }
}
