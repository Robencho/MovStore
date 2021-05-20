package com.rubio.movstore.utils

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

class PreferencesHelper @Inject constructor(context: Context) {
    private val preferences: SharedPreferences

    init {
        preferences = context.getSharedPreferences(PREF_NAME, MODE_PRIVATE)
    }

    var stateSession: Boolean
        get() = preferences.getBoolean(KEY_STATE_SESSION, false)
        set(value) {
            preferences.edit().run {
                putBoolean(KEY_STATE_SESSION, value)
                apply()
            }
        }

    var prefUserName: String
        get() = preferences.getString(KEY_USER_NAME, "")!!
        set(value) {
            preferences.edit().run {
                putString(KEY_USER_NAME, value)
                apply()
            }
        }

    var prefUserPassword: String
        get() = preferences.getString(KEY_USER_PASSWORD, "")!!
        set(value) {
            preferences.edit().run {
                putString(KEY_USER_PASSWORD, value)
                apply()
            }
        }

    companion object {
        const val PREF_NAME = "preferencesKey"
        private const val MODE_PRIVATE: Int = 0
        private const val KEY_STATE_SESSION = "key_help"

        private const val KEY_USER_NAME: String = "user_name"
        private const val KEY_USER_PASSWORD = "user_password"
    }
}