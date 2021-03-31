package io.houf.moneta.view

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LocalContentColor
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import io.houf.moneta.activity.ListingActivity
import io.houf.moneta.activity.ListingData
import io.houf.moneta.component.Price
import io.houf.moneta.local.LocalQueue
import io.houf.moneta.util.ViewModelFactory
import io.houf.moneta.util.openActivity
import io.houf.moneta.viewmodel.PortfolioViewModel
import java.lang.ref.WeakReference

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PortfolioView() {
    val context = LocalContext.current
    val queue = LocalQueue.current
    val viewModel = viewModel<PortfolioViewModel>(
        factory = ViewModelFactory(WeakReference(context), queue)
    )

    Price(viewModel.value, viewModel.change)
    LazyVerticalGrid(
        cells = GridCells.Adaptive(minSize = 192.dp),
        contentPadding = PaddingValues(16.dp),
        modifier = Modifier.animateContentSize()
    ) {
        items(viewModel.listings) { listing ->
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(LocalContentColor.current.copy(alpha = 0.1f))
                    .clickable {
                        openActivity(
                            context,
                            ListingActivity::class.java,
                            ListingData(listing.id.toInt(), listing.name)
                        )
                    }
                    .padding(16.dp)
            ) {
                Text(listing.name)
                Text(
                    text = "${listing.id}",
                    fontSize = 22.sp,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }
}
