package io.houf.moneta.util

import android.content.Context
import android.net.Uri
import android.widget.Toast
import com.android.volley.*
import com.android.volley.toolbox.HttpHeaderParser
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.google.gson.JsonSyntaxException
import io.houf.moneta.BuildConfig
import java.io.UnsupportedEncodingException
import java.nio.charset.Charset

private val gson = GsonBuilder()
    .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
    .create()

class ApiRequest<T>(
    context: Context,
    url: String,
    private val cls: Class<T>,
    private val listener: (T) -> Unit
) : Request<T>(Method.GET, url, { error ->
    Toast.makeText(context, error.localizedMessage, Toast.LENGTH_LONG).show()
}) {
    override fun getHeaders() = mutableMapOf("X-CMC_PRO_API_KEY" to BuildConfig.API_KEY)

    override fun deliverResponse(response: T) = listener(response)

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
        } catch (e: UnsupportedEncodingException) {
            Response.error(ParseError(e))
        } catch (e: JsonSyntaxException) {
            Response.error(ParseError(e))
        }
    }
}

inline fun <reified T> RequestQueue.add(context: Context, url: String, noinline listener: (T) -> Unit) {
    val builder = Uri.Builder()
        .scheme("https")
        .authority("pro-api.coinmarketcap.com")
        .appendPath("v1")

    url.split("/").forEach { path ->
        builder.appendPath(path)
    }

    this.add(ApiRequest(context, builder.toString(), T::class.java, listener))
}
