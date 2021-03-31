package io.houf.moneta.service

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.preference.PreferenceManager

class SettingsService(context: Context) : SharedPreferences.OnSharedPreferenceChangeListener {
    private var _currency = MutableLiveData("")
    val currency: LiveData<String> = _currency

    private var _range = MutableLiveData("")
    val range: LiveData<String> = _range

    private var _blur = MutableLiveData(false)
    val blur: LiveData<Boolean> = _blur

    init {
        val settings = PreferenceManager.getDefaultSharedPreferences(context)

        settings.registerOnSharedPreferenceChangeListener(this)
        readSettings(settings)
    }

    override fun onSharedPreferenceChanged(settings: SharedPreferences, key: String?) {
        if (key == null) {
            return
        }

        Log.d("moneta.settings", "Changed setting: $key")

        readSettings(settings)
    }

    private fun readSettings(settings: SharedPreferences) {
        _currency.value = settings.getString("currency", null) ?: "eur"
        _range.value = settings.getString("range", null) ?: "24h"
        _blur.value = settings.getBoolean("blur", false)
    }
}
