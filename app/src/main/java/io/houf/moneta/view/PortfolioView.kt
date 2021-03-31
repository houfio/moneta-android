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
import io.houf.moneta.activity.ListingActivity
import io.houf.moneta.activity.ListingData
import io.houf.moneta.component.Price
import io.houf.moneta.util.openActivity
import kotlin.random.Random

val listings = List(21) { i -> i }
val amounts = List(listings.size) { Random.nextInt(1, 100) }

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PortfolioView() {
    Price(1000.0, -25.0)
    LazyVerticalGrid(
        cells = GridCells.Adaptive(minSize = 192.dp),
        contentPadding = PaddingValues(16.dp),
        modifier = Modifier.animateContentSize()
    ) {
        items(listings) { listing ->
            val name = "Coin ${listing + 1}"
            val context = LocalContext.current

            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(LocalContentColor.current.copy(alpha = 0.1f))
                    .clickable {
                        openActivity(
                            context,
                            ListingActivity::class.java,
                            ListingData(listing, name)
                        )
                    }
                    .padding(16.dp)
            ) {
                Text(name)
                Text(
                    text = "${amounts[listing]}",
                    fontSize = 22.sp,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }
}
