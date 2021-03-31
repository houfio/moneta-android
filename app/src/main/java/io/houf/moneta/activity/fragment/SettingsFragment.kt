package io.houf.moneta.activity.fragment

import android.os.Bundle
import androidx.preference.DropDownPreference
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat
import io.houf.moneta.R
import io.houf.moneta.activity.SettingsData

class SettingsFragment(private val data: SettingsData) : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)

        val keys = data.currencies.map { it.symbol }.toTypedArray()

        findPreference<ListPreference>("currency")?.apply {
            entries = data.currencies.map { it.name }.toTypedArray()
            entryValues = keys
            setDefaultValue(keys.first())
        }

        findPreference<DropDownPreference>("range")?.apply {
            setDefaultValue(entryValues.first())
        }
    }
}
