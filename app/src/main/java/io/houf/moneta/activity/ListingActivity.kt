package io.houf.moneta.activity

import android.app.ActionBar
import io.houf.moneta.R
import io.houf.moneta.fragment.ComposeFragment
import io.houf.moneta.view.ListingView

class ListingActivity : FragmentActivity(ComposeFragment { ListingView() }) {
    override fun initializeBar(bar: ActionBar) {
        bar.setTitle(R.string.listing)
    }
}
