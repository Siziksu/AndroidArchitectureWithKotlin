package com.siziksu.kotlin.data

import android.text.TextUtils

import com.siziksu.kotlin.common.Preferences

abstract class Data {

    private val CACHE_TIME_SUFFIX = "_time"

    protected fun getCache(cacheKey: String): String {
        return Preferences.getString(cacheKey, "")
    }

    protected fun setCache(cacheKey: String, cache: String) {
        Preferences.setString(cacheKey, cache)
        Preferences.setLong(cacheKey + CACHE_TIME_SUFFIX, System.currentTimeMillis())
    }

    protected fun cacheExists(cache: String): Boolean {
        return !TextUtils.isEmpty(cache)
    }

    protected fun cacheIsNotExpired(cacheKey: String, cacheExpiryTime: Long): Boolean {
        val cacheTime = Preferences.getLong(cacheKey + CACHE_TIME_SUFFIX, System.currentTimeMillis())
        return cacheTime + cacheExpiryTime >= System.currentTimeMillis()
    }

    protected fun isCacheValid(cache: String, cacheKey: String, cacheExpiryTime: Long): Boolean {
        return cacheExists(cache) && cacheIsNotExpired(cacheKey, cacheExpiryTime)
    }
}
