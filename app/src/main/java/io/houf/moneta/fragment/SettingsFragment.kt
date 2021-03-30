package io.houf.moneta.fragment

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
            setDefaultValue(currencies.first())
            entries = currencies
            entryValues = currencies
        }

        findPreference<DropDownPreference>("range")?.apply {
            setDefaultValue(entryValues.first())
        }
    }
}
