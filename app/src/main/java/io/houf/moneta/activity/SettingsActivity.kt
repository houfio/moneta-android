package io.houf.moneta.activity

import android.app.ActionBar
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import io.houf.moneta.R
import io.houf.moneta.fragment.SettingsFragment

data class SettingsData(val currencies: List<String>)

class SettingsActivity : FragmentActivity<SettingsData>() {
    override fun encode(bundle: Bundle, data: SettingsData) {
        bundle.putStringArrayList("currencies", ArrayList(data.currencies))
    }

    override fun decode(intent: Intent): SettingsData {
        return SettingsData(
            currencies = intent.getStringArrayListExtra("currencies")!!.toList()
        )
    }

    override fun initializeBar(bar: ActionBar, data: SettingsData) {
        bar.setTitle(R.string.settings)
    }

    override fun getFragment(data: SettingsData): Fragment {
        return SettingsFragment(data)
    }
}
