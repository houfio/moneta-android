package io.houf.moneta.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import io.houf.moneta.MonetaApp
import io.houf.moneta.R
import io.houf.moneta.service.ApiService
import io.houf.moneta.util.data.observeOnce
import io.houf.moneta.util.intent.openListing
import io.houf.moneta.view.theme.MonetaTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var api: ApiService

    override fun onCreate(state: Bundle?) {
        setTheme(R.style.Theme_Moneta)

        super.onCreate(state)

        setContent {
            MonetaTheme {
                MonetaApp()
            }
        }

        api.listings.observeOnce(this, {
            onNewIntent(intent)
        }) { listings ->
            listings?.isNotEmpty() ?: false
        }
    }

    override fun onNewIntent(intent: Intent?) {
        when (intent?.action) {
            Intent.ACTION_VIEW -> {
                val data = intent.data?.pathSegments ?: return

                Log.d("moneta.share", "Received data: $data")

                if (data.size != 2) {
                    return
                }

                openListing(applicationContext, api, data[1])
            }
        }
    }
}
