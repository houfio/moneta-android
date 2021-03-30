package io.houf.moneta.activity

import android.app.ActionBar
import io.houf.moneta.R
import io.houf.moneta.fragment.ListingFragment

class ListingActivity : FragmentActivity(ListingFragment()) {
    override fun initializeBar(bar: ActionBar) {
        bar.setTitle(R.string.listing)
    }
}
