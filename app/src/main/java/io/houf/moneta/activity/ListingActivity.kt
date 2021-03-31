package io.houf.moneta.activity

import android.app.ActionBar
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import io.houf.moneta.fragment.ComposeFragment
import io.houf.moneta.view.ListingView

data class ListingData(val id: Int, var title: String)

class ListingActivity : FragmentActivity<ListingData>() {
    override fun encode(bundle: Bundle, data: ListingData) {
        bundle.putInt("id", data.id)
        bundle.putString("title", data.title)
    }

    override fun decode(intent: Intent): ListingData {
        return ListingData(
            id = intent.getIntExtra("id", 1),
            title = intent.getStringExtra("title") ?: ""
        )
    }

    override fun initializeBar(bar: ActionBar, data: ListingData) {
        bar.title = data.title
    }

    override fun getFragment(data: ListingData): Fragment {
        return ComposeFragment { ListingView(data) }
    }
}
