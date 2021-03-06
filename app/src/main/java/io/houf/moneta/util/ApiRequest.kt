package io.houf.moneta.util

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.android.volley.NetworkResponse
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.HttpHeaderParser
import io.houf.moneta.BuildConfig
import io.houf.moneta.R
import io.houf.moneta.util.data.decodeJson
import java.nio.charset.Charset

class ApiRequest<T>(
    context: Context,
    url: String,
    private val cls: Class<T>,
    private val onSuccess: (T) -> Unit,
    private val onCache: ((String) -> Unit)? = null
) : Request<T>(Method.GET, url, { e ->
    Log.d("moneta.request", "Network request failed", e)
    Toast.makeText(context, R.string.network_error, Toast.LENGTH_SHORT).show()
}) {
    override fun getHeaders() = mutableMapOf("X-CMC_PRO_API_KEY" to BuildConfig.API_KEY)

    override fun deliverResponse(response: T) = onSuccess(response)

    override fun parseNetworkResponse(response: NetworkResponse?): Response<T> {
        return try {
            val json = String(
                response?.data ?: ByteArray(0),
                Charset.forName(HttpHeaderParser.parseCharset(response?.headers))
            )

            Log.d("moneta.request", "Decoding network response: $json")

            onCache?.invoke(json)

            Response.success(decodeJson(json, cls), null)
        } catch (e: Exception) {
            Response.error(VolleyError(e))
        }
    }
}
