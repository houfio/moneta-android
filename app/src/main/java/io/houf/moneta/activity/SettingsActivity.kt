package io.houf.moneta.activity

import android.app.ActionBar
import android.os.Parcelable
import androidx.fragment.app.Fragment
import io.houf.moneta.R
import io.houf.moneta.activity.fragment.SettingsFragment
import io.houf.moneta.model.CurrencyModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class SettingsData(val currencies: List<CurrencyModel>) : Parcelable

class SettingsActivity : FragmentActivity<SettingsData>() {
    override fun initializeBar(bar: ActionBar, data: SettingsData) {
        bar.setTitle(R.string.settings)
    }

    override fun getFragment(data: SettingsData): Fragment {
        return SettingsFragment(data)
    }
}
