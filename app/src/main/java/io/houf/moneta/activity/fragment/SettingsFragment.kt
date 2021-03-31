package io.houf.moneta.activity.fragment

import android.os.Bundle
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat
import io.houf.moneta.R
import io.houf.moneta.activity.SettingsData

class SettingsFragment(private val data: SettingsData) : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)

        findPreference<ListPreference>("currency")?.apply {
            entries = data.currencies.map { it.name }.toTypedArray()
            entryValues = data.currencies.map { it.symbol }.toTypedArray()
            setDefaultValue("EUR")
        }
    }
}
