package io.houf.moneta.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import io.houf.moneta.MonetaApp
import io.houf.moneta.R
import io.houf.moneta.theme.MonetaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(state: Bundle?) {
        setTheme(R.style.Theme_Moneta)

        super.onCreate(state)

        setContent {
            MonetaTheme {
                MonetaApp(applicationContext)
            }
        }
    }
}
