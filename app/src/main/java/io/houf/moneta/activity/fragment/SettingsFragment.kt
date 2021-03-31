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

        val currencies = data.currencies.toTypedArray()

        findPreference<ListPreference>("currency")?.apply {
            entries = currencies
            entryValues = currencies
            setDefaultValue(currencies.first())
        }

        findPreference<DropDownPreference>("range")?.apply {
            setDefaultValue(entryValues.first())
        }
    }
}
