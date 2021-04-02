package io.houf.moneta.view

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import io.houf.moneta.R
import io.houf.moneta.Screen
import io.houf.moneta.activity.ListingActivity
import io.houf.moneta.activity.ListingData
import io.houf.moneta.util.openActivity
import io.houf.moneta.view.component.*
import io.houf.moneta.viewmodel.SearchViewModel

private const val PERMISSION_REQUEST_CODE = 10
private val PERMISSIONS_REQUIRED = arrayOf(Manifest.permission.RECORD_AUDIO)

@Composable
fun SearchView(viewModel: SearchViewModel = hiltNavGraphViewModel()) {
    val search by viewModel.search()
    val listings by viewModel.listings()
    val context = LocalContext.current

    TopBar(Screen.Search) {
        IconButton(onClick = {
            if (!hasPermissions(context)) {
                ActivityCompat.requestPermissions(
                    context as Activity,
                    PERMISSIONS_REQUIRED,
                    PERMISSION_REQUEST_CODE
                )
            }
        }) {
            Icon(Icons.Outlined.Refresh, stringResource(R.string.refresh))
        }
    }
    SearchField(
        value = search,
        setValue = { viewModel.setSearch(it) },
        modifier = Modifier.padding(bottom = 16.dp)
    )
    DividerLine()
    LazyColumn {
        items(listings) { listing ->
            RowEntry({
                openActivity(
                    context,
                    ListingActivity::class.java,
                    ListingData(listing)
                )
            }) {
                Text(listing.name)
                Spacer(Modifier.weight(1f))
                Pill(viewModel.change(listing))
            }
        }
    }
}

fun hasPermissions(context: Context) = PERMISSIONS_REQUIRED.all {
    ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
}
