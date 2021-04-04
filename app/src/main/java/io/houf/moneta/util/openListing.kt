package io.houf.moneta.util

import android.content.Context
import android.widget.Toast
import io.houf.moneta.R
import io.houf.moneta.activity.ListingActivity
import io.houf.moneta.activity.ListingData
import io.houf.moneta.service.ApiService

fun openListing(context: Context, api: ApiService, slug: String) {
    val listing = api.listings.value?.find { it.slug == slug }

    if (listing != null) {
        openActivity(
            context,
            ListingActivity::class.java,
            ListingData(listing)
        )
    } else {
        Toast.makeText(context, R.string.unknown_listing, Toast.LENGTH_SHORT).show()
    }
}
