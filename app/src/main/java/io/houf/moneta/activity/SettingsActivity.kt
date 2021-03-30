package io.houf.moneta.activity

import android.app.ActionBar
import io.houf.moneta.R
import io.houf.moneta.fragment.SettingsFragment

class SettingsActivity : FragmentActivity(SettingsFragment()) {
    override fun initializeBar(bar: ActionBar) {
        bar.setTitle(R.string.settings)
    }
}
