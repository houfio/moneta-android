package io.houf.moneta.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.FragmentActivity
import io.houf.moneta.R
import io.houf.moneta.fragment.SettingsFragment

class SettingsActivity : FragmentActivity(R.layout.settings_activity) {
    override fun onCreate(state: Bundle?) {
        super.onCreate(state)
        setActionBar(findViewById(R.id.topBar))

        actionBar?.setDisplayHomeAsUpEnabled(true)

        supportFragmentManager.beginTransaction()
            .replace(R.id.settings, SettingsFragment())
            .commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()

            return true
        }

        return super.onOptionsItemSelected(item)
    }
}
