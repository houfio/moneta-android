package io.houf.moneta.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import io.houf.moneta.MonetaApp
import io.houf.moneta.theme.MonetaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(state: Bundle?) {
        super.onCreate(state)

        setContent {
            MonetaTheme {
                MonetaApp(applicationContext)
            }
        }
    }
}
