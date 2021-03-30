package io.houf.moneta.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.google.gson.Gson
import io.houf.moneta.BuildConfig
import io.houf.moneta.MonetaApp
import io.houf.moneta.R
import io.houf.moneta.theme.MonetaTheme
import okhttp3.*
import java.io.IOException
import io.houf.moneta.model.Listing

class MainActivity : ComponentActivity() {

    private val client:OkHttpClient = OkHttpClient()

    override fun onCreate(state: Bundle?) {
        setTheme(R.style.Theme_Moneta)

        super.onCreate(state)

        val hashMap : HashMap<String, String> =
            hashMapOf<String, String>("start" to "1", "limit" to "100", "convert" to "EUR")

        try {
            makeAPICall("https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest", hashMap)
        } catch(error: IOException) {

        }

        setContent {
            MonetaTheme {
                MonetaApp(applicationContext)
            }
        }
    }

    private fun makeAPICall(url: String, parameters: HashMap<String, String>) {
        val request = Request.Builder()
            .url(url)
            .addHeader("X-CMC_PRO_API_KEY", BuildConfig.API_KEY)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                // do something on failure
            }
            override fun onResponse(call: Call, response: Response) {
                val gson = Gson()
                val data = response.body()
                if (data != null) {
                    val listing: Listing = gson.fromJson(data.string(), Listing::class.java)
                    print("calling methodx")
                    print(listing)
                }
            }
        })
    }
}
