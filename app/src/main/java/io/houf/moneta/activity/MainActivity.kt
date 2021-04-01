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
import io.houf.moneta.util.openListing
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
    }

    override fun onNewIntent(intent: Intent?) {
        when (intent?.action) {
            Intent.ACTION_SEND -> {
                val text = intent.getStringExtra(Intent.EXTRA_TEXT) ?: return

                Log.d("moneta.share", "Received text: $text")

                openListing(applicationContext, api, text)
            }
        }
    }
}
