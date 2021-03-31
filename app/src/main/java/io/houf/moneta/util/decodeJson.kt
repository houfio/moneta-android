package io.houf.moneta.util

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder

private val gson = GsonBuilder()
    .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
    .create()

fun <T> decodeJson(json: String, cls: Class<T>) = gson.fromJson(json, cls)!!
