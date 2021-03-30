package io.houf.moneta.activity

import android.app.ActionBar
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import io.houf.moneta.R
import io.houf.moneta.fragment.SettingsFragment

class SettingsData

class SettingsActivity : FragmentActivity<SettingsData>() {
    override fun encode(bundle: Bundle, data: SettingsData) {}

    override fun decode(intent: Intent): SettingsData {
        return SettingsData()
    }

    override fun initializeBar(bar: ActionBar, data: SettingsData) {
        bar.setTitle(R.string.settings)
    }

    override fun getFragment(data: SettingsData): Fragment {
        return SettingsFragment()
    }
}
