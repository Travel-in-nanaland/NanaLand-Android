package com.nanaland.util.network

import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import okhttp3.Interceptor
import okhttp3.Response
import okio.Buffer
import okio.GzipSource
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets
import javax.inject.Inject

class LogInterceptor @Inject constructor() : Interceptor{

    private val mGson = GsonBuilder().setPrettyPrinting().create()
    private val mJsonParser = JsonParser()

    override fun intercept(chain: Interceptor.Chain): Response {
        Log.e("httpRequestLog", "[url]\n${chain.request().url}\n" +
                "[host]\n${chain.request().url.host}\n" +
                "[query]\n${chain.request().url.query?.replace("&", "\n")}\n" +
                "[method]\n${chain.request().method}\n" +
                "[headers]\n${(0 until chain.request().headers.size).joinToString(separator = "\n") { "- ${chain.request().headers.name(it)}: ${chain.request().headers.value(it)}" }}\n" +
                "[body]\n${chain.request().body}")
        val response =  chain.proceed(chain.request())

        val responseBody = response.body!!
        val contentLength = responseBody.contentLength()

        val source = responseBody.source()
        source.request(Long.MAX_VALUE)
        var buffer = responseBody.source().buffer
        val responseHeaders = response.headers

        if ("gzip".equals(responseHeaders["Content-Encoding"], ignoreCase = true)) {
            GzipSource(buffer.clone() ?: Buffer()).use { gzippedResponseBody ->
                buffer = Buffer()
                buffer.writeAll(gzippedResponseBody)
            }
        }
        val contentType = response.body?.contentType()
        val charset: Charset = contentType?.charset(StandardCharsets.UTF_8) ?: StandardCharsets.UTF_8
        val bodyString = buffer.clone().readString(charset)
        val prettyJson = mGson.toJson(mJsonParser.parse(bodyString))
        Log.e("httpResponseLog", "[code]\n${response.code}\n" +
                "[headers]\n${response.headers}" +
                "[body]\n${prettyJson}")
        return response
    }
}