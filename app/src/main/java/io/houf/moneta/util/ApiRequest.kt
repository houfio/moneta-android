package io.houf.moneta.util

import android.net.Uri
import android.util.Log
import com.android.volley.*
import com.android.volley.toolbox.HttpHeaderParser
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import io.houf.moneta.BuildConfig
import java.nio.charset.Charset

private val gson = GsonBuilder()
    .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
    .create()

class ApiRequest<T>(
    url: String,
    private val cls: Class<T>,
    onError: (VolleyError) -> Unit,
    private val onSuccess: (T) -> Unit
) : Request<T>(Method.GET, url, { error ->
    Log.d("MONETA", error.localizedMessage ?: "Unknown request error")
    onError(error)
}) {
    override fun getHeaders() = mutableMapOf("X-CMC_PRO_API_KEY" to BuildConfig.API_KEY)

    override fun deliverResponse(response: T) = onSuccess(response)

    override fun parseNetworkResponse(response: NetworkResponse?): Response<T> {
        return try {
            val json = String(
                response?.data ?: ByteArray(0),
                Charset.forName(HttpHeaderParser.parseCharset(response?.headers))
            )

            Response.success(
                gson.fromJson(json, cls),
                HttpHeaderParser.parseCacheHeaders(response)
            )
        } catch (e: Exception) {
            Response.error(VolleyError(e))
        }
    }
}

inline fun <reified T> RequestQueue.add(
    url: String,
    noinline onError: (VolleyError) -> Unit,
    noinline onDone: (T) -> Unit
) {
    val builder = Uri.Builder()
        .scheme("https")
        .authority("pro-api.coinmarketcap.com")
        .appendPath("v1")

    url.split("/").forEach { path ->
        builder.appendPath(path)
    }

    this.add(ApiRequest(builder.toString(), T::class.java, onError, onDone))
}
