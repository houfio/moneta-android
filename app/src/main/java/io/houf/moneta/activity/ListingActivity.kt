package io.houf.moneta.activity

import android.app.ActionBar
import android.os.Parcelable
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import io.houf.moneta.activity.fragment.ComposeFragment
import io.houf.moneta.model.ListingModel
import io.houf.moneta.view.ListingView
import io.houf.moneta.viewmodel.ListingViewModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class ListingData(val listing: ListingModel) : Parcelable

@AndroidEntryPoint
class ListingActivity : FragmentActivity<ListingData>() {
    override fun initializeBar(bar: ActionBar, data: ListingData) {
        bar.title = data.listing.name
    }

    override fun getFragment(data: ListingData): Fragment {
        val viewModel by viewModels<ListingViewModel>()

        viewModel.initialize(data.listing)

        return ComposeFragment { ListingView(data, viewModel) }
    }
}
