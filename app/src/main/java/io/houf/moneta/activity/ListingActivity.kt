package io.houf.moneta.activity

import android.app.ActionBar
import android.os.Parcelable
import androidx.fragment.app.Fragment
import io.houf.moneta.activity.fragment.ComposeFragment
import io.houf.moneta.model.ListingModel
import io.houf.moneta.view.ListingView
import kotlinx.parcelize.Parcelize

@Parcelize
data class ListingData(val listing: ListingModel) : Parcelable

class ListingActivity : FragmentActivity<ListingData>() {
    override fun initializeBar(bar: ActionBar, data: ListingData) {
        bar.title = data.listing.name
    }

    override fun getFragment(data: ListingData): Fragment {
        return ComposeFragment { ListingView(data) }
    }
}
