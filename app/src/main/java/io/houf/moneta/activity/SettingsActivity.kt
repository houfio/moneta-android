package io.houf.moneta.activity

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import io.houf.moneta.R
import io.houf.moneta.fragment.SettingsFragment

class SettingsActivity : FragmentActivity() {
    override fun onCreate(state: Bundle?) {
        super.onCreate(state)
        setContentView(R.layout.settings_activity)
        setActionBar(findViewById(R.id.topBar))

        actionBar?.setDisplayHomeAsUpEnabled(true)

        supportFragmentManager.beginTransaction()
            .replace(R.id.settings, SettingsFragment())
            .commit()
    }
}
