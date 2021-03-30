package io.houf.moneta.fragment

import android.os.Bundle
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat

class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        val context = preferenceManager.context
        val screen = preferenceManager.createPreferenceScreen(context)

        val simplePreference = Preference(context).apply {
            key = "key"
            title = "Title"
            summary = "Summary"
        }

        screen.addPreference(simplePreference)

        preferenceScreen = screen
    }
}
