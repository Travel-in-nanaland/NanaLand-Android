package com.jeju.nanaland.util.network

import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import okhttp3.internal.platform.Platform
import okhttp3.logging.HttpLoggingInterceptor
import java.util.logging.Level
import javax.inject.Inject


class PrettyLogger @Inject constructor() : HttpLoggingInterceptor.Logger {
    private val mGson = GsonBuilder().setPrettyPrinting().create()
    private val mJsonParser = JsonParser()
    override fun log(message: String) {
        val trimMessage = message.trim()
        if ((trimMessage.startsWith("{") && trimMessage.endsWith("}")) || (trimMessage.startsWith("[") && trimMessage.endsWith("]"))) {
            try {
                val prettyJson = mGson.toJson(mJsonParser.parse(message))
                Platform.get().log(prettyJson, Level.SEVERE.intValue(), null)
            } catch (e: Exception) {
                Platform.get().log(message, Level.SEVERE.intValue(), e)
            }
        } else {
            Platform.get().log(message, Level.SEVERE.intValue(), null)
        }
    }
}