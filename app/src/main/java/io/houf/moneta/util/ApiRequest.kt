package io.houf.moneta.util

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.android.volley.NetworkResponse
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.HttpHeaderParser
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import io.houf.moneta.BuildConfig
import java.nio.charset.Charset

private val gson = GsonBuilder()
    .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
    .create()

class ApiRequest<T>(
    context: Context,
    url: String,
    private val cls: Class<T>,
    private val onSuccess: (T) -> Unit
) : Request<T>(Method.GET, url, { error ->
    val message = error.localizedMessage ?: "Unknown request error";

    Log.d("MONETA", message)
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
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
