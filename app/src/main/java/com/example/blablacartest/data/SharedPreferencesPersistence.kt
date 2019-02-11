package com.example.blablacartest.data

import android.content.Context
import android.content.Context.MODE_PRIVATE

class SharedPreferencesPersistence(val context: Context) {

    companion object {
        const val DEFAULT_VALUE = ""
        const val BLABLACAR_PREFS = "PREFS"
    }

    fun putString(key: String, value: String) {
        val sharedPreferences = context.getSharedPreferences(BLABLACAR_PREFS, MODE_PRIVATE)
        sharedPreferences.edit()
            .putString(key, value)
            .apply()
    }

    fun getString(key: String): String {
        val sharedPreferences = context.getSharedPreferences(BLABLACAR_PREFS, MODE_PRIVATE)
        return sharedPreferences.getString(key, DEFAULT_VALUE)!!
    }

}