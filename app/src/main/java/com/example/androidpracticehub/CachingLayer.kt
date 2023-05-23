package com.example.androidpracticehub

import android.content.Context
import android.content.SharedPreferences

interface CachingLayer {
    fun getEmail(): String
    fun getPassword(): String
    fun getName(): String
    fun getLoggedIn(): Boolean
    fun setName(value: String): SharedPreferences.Editor
    fun setEmail(value: String): SharedPreferences.Editor
    fun setPassword(value: String): SharedPreferences.Editor
    fun setLoggedIn(value: Boolean): SharedPreferences.Editor
}


class CachingLayerImpl(context: Context) : CachingLayer {

    private val prefs: SharedPreferences =
        context.getSharedPreferences(Constants.SHARED_PREFS_NAME, Context.MODE_PRIVATE)

    override fun getEmail(): String =
        prefs.getString(Constants.PREFS_EMAIL_KEY, null).orEmpty()

    override fun getPassword(): String =
        prefs.getString(Constants.PREFS_PASSWORD_KEY, null).orEmpty()

    override fun getName(): String =
        prefs.getString(Constants.PREFS_FIRST_NAME_KEY, null).orEmpty()

    override fun getLoggedIn(): Boolean =
        prefs.getBoolean(Constants.PREFS_LOGIN_KEY, false)

    override fun setEmail(value: String): SharedPreferences.Editor =
        prefs.edit().apply {
            putString(Constants.PREFS_EMAIL_KEY, value)
            apply()
        }

    override fun setPassword(value: String): SharedPreferences.Editor =
        prefs.edit().apply {
            putString(Constants.PREFS_PASSWORD_KEY, value)
            apply()
        }

    override fun setName(value: String): SharedPreferences.Editor =
        prefs.edit().apply {
            putString(Constants.PREFS_FIRST_NAME_KEY, value)
            apply()
        }

    override fun setLoggedIn(value: Boolean): SharedPreferences.Editor =
        prefs.edit().apply {
            putBoolean(Constants.PREFS_LOGIN_KEY, value)
            apply()
        }
}

object Constants {
    const val SHARED_PREFS_NAME = "prefs"
    const val PREFS_FIRST_NAME_KEY = "firstName"
    const val PREFS_LAST_NAME_KEY = "lastName"
    const val PREFS_EMAIL_KEY = "email"
    const val PREFS_PASSWORD_KEY = "password"
    const val PREFS_LOGIN_KEY = "login"
}