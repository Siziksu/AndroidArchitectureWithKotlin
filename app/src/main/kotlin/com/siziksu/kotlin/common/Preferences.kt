package com.siziksu.kotlin.common

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

object Preferences {

    private var context: Context? = null

    fun init(context: Context) {
        this.context = context
    }

    private val preferences: SharedPreferences
        get() = PreferenceManager.getDefaultSharedPreferences(context)

    inline fun SharedPreferences.edit(func: SharedPreferences.Editor.() -> Unit) {
        val editor = edit()
        editor.func()
        editor.apply()
    }

    fun registerOnSharedPreferenceChangeListener(listener: SharedPreferences.OnSharedPreferenceChangeListener) {
        preferences.registerOnSharedPreferenceChangeListener(listener)
    }

    fun unregisterOnSharedPreferenceChangeListener(
            listener: SharedPreferences.OnSharedPreferenceChangeListener) {
        preferences.unregisterOnSharedPreferenceChangeListener(listener)
    }

    fun setString(key: String, value: String) {
        preferences.edit { putString(key, value) }
    }

    fun getString(key: String, defaultValue: String): String {
        return preferences.getString(key, defaultValue)
    }

    fun setBoolean(key: String, value: Boolean) {
        preferences.edit { putBoolean(key, value) }
    }

    fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return preferences.getBoolean(key, defaultValue)
    }

    fun setInt(key: String, value: Int) {
        preferences.edit { putInt(key, value) }
    }

    fun getInt(key: String, defaultValue: Int): Int {
        return preferences.getInt(key, defaultValue)
    }

    fun setLong(key: String, value: Long) {
        preferences.edit { putLong(key, value) }
    }

    fun getLong(key: String, defaultValue: Long): Long {
        return preferences.getLong(key, defaultValue)
    }

    fun setFloat(key: String, value: Float) {
        preferences.edit().putFloat(key, value).apply()
    }

    fun getFloat(key: String, defaultValue: Float): Float {
        return preferences.getFloat(key, defaultValue)
    }

    fun deleteKey(key: String) {
        if (preferences.contains(key)) {
            preferences.edit { remove(key) }
        }
    }
}