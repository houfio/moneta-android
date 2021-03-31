package io.houf.moneta.util

import android.content.Context
import android.net.Uri
import android.widget.Toast
import com.android.volley.*
import com.android.volley.toolbox.HttpHeaderParser
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import io.houf.moneta.BuildConfig
import io.houf.moneta.model.ResponseModel
import java.io.UnsupportedEncodingException
import java.nio.charset.Charset

private val gson = Gson()

class ApiRequest<T>(
    context: Context,
    url: String,
    private val listener: (ResponseModel<T>) -> Unit
) : Request<ResponseModel<T>>(Method.GET, url, { error ->
    Toast.makeText(context, error.localizedMessage, Toast.LENGTH_LONG).show()
}) {
    override fun getHeaders() = mutableMapOf("X-CMC_PRO_API_KEY" to BuildConfig.API_KEY)

    override fun deliverResponse(response: ResponseModel<T>) = listener(response)

    override fun parseNetworkResponse(response: NetworkResponse?): Response<ResponseModel<T>> {
        return try {
            val json = String(
                response?.data ?: ByteArray(0),
                Charset.forName(HttpHeaderParser.parseCharset(response?.headers))
            )

            Response.success(
                gson.fromJson(json, object : TypeToken<ResponseModel<T>>() {}.type),
                HttpHeaderParser.parseCacheHeaders(response)
            )
        } catch (e: UnsupportedEncodingException) {
            Response.error(ParseError(e))
        } catch (e: JsonSyntaxException) {
            Response.error(ParseError(e))
        }
    }
}

fun <T> RequestQueue.add(context: Context, url: String, listener: (ResponseModel<T>) -> Unit) {
    val builder = Uri.Builder()
        .scheme("https")
        .authority("pro-api.coinmarketcap.com")
        .appendPath("v1")

    url.split("/").forEach { path ->
        builder.appendPath(path)
    }

    this.add(ApiRequest(context, builder.toString(), listener))
}
