package io.houf.moneta.service

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.preference.PreferenceManager

class SettingsService(private val context: Context) :
    SharedPreferences.OnSharedPreferenceChangeListener {
    private val settings
        get() = PreferenceManager.getDefaultSharedPreferences(context)

    private var _currency = MutableLiveData("")
    val currency: LiveData<String> = _currency

    private var _range = MutableLiveData("")
    val range: LiveData<String> = _range

    private var _blur = MutableLiveData(false)
    val blur: LiveData<Boolean> = _blur

    init {
        settings.registerOnSharedPreferenceChangeListener(this)
        readSettings()
    }

    override fun onSharedPreferenceChanged(settings: SharedPreferences, key: String?) {
        if (key == null) {
            return
        }

        Log.d("moneta.settings", "Changed setting: $key")

        readSettings()
    }

    private fun readSettings() {
        _currency.value = settings.getString("currency", null) ?: "eur"
        _range.value = settings.getString("range", null) ?: "24h"
        _blur.value = settings.getBoolean("blur", false)
    }
}
