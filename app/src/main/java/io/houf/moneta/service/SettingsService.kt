package io.houf.moneta.service

import android.content.Context
import androidx.preference.PreferenceManager

class SettingsService(private val context: Context) {
    private val settings
        get() = PreferenceManager.getDefaultSharedPreferences(context)

    val currency
        get() = settings.getString("currency", null) ?: "EUR"

    val range
        get() = settings.getString("range", null) ?: "24h"

    val blur
        get() = settings.getBoolean("blur", false)
}
