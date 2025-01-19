package com.jeju.nanaland.util.network

import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import com.google.gson.JsonIOException
import com.google.gson.JsonParser
import com.google.gson.JsonParser.parseString
import com.google.gson.JsonSyntaxException
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.MalformedJsonException
import com.jeju.nanaland.util.log.LogUtil
import okhttp3.Interceptor
import okhttp3.Response
import okio.Buffer
import okio.GzipSource
import java.io.IOException
import java.io.Reader
import java.io.StringReader
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets
import javax.inject.Inject

class LogInterceptor @Inject constructor() : Interceptor{

    private val mGson = GsonBuilder().setLenient().setPrettyPrinting().create()

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val requestBody = request.body
        var requestBodyString = ""
        if (requestBody != null) {
            val buffer = Buffer()
            requestBody.writeTo(buffer)
            val contentType = requestBody.contentType()
            val charset: Charset = contentType?.charset(StandardCharsets.UTF_8) ?: StandardCharsets.UTF_8
            val bodyString = buffer.clone().readString(charset)
            requestBodyString = try {
                // 일반 request
                mGson.toJson(customParseString(bodyString))
            } catch (e: Exception) {
                // Multipart 사용 시
//                if (bodyString.contains("image/*")) {
//                    val newString = bodyString.substringBefore("image/*") + "image/*"
//                    newString
//                }
//                else bodyString
                bodyString
            }
        }
        LogUtil.e("httpRequestLog", "[url]\n${request.url}\n" +
                "[host]\n${request.url.host}\n" +
                "[query]\n${request.url.query?.replace("&", "\n")}\n" +
                "[method]\n${request.method}\n" +
                "[headers]\n${(0 until request.headers.size).joinToString(separator = "\n") { "- ${request.headers.name(it)}: ${request.headers.value(it)}" }}\n" +
                "[body]\n$requestBodyString")

        val response =  chain.proceed(chain.request())
        val responseBody = response.body
        var responseBodyString = ""
        if (responseBody != null) {
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
            responseBodyString = try{
                mGson.toJson(parseString(bodyString))
            } catch (e: Exception) {
                e.printStackTrace()
                bodyString
            }
        }

        LogUtil.e("httpResponseLog", "[code]\n${response.code}\n" +
                "[headers]\n${response.headers}" +
                "[body]\n${responseBodyString}")
        return response
    }
}

fun customParseString(json: String): JsonElement {
    return customParseReader(StringReader(json))
}

fun customParseReader(reader: Reader): JsonElement {
    return try {
        val jsonReader = JsonReader(reader)
        jsonReader.isLenient = true
        val element = JsonParser.parseReader(jsonReader)
        if (!element.isJsonNull && jsonReader.peek() != JsonToken.END_DOCUMENT) {
            throw JsonSyntaxException("Did not consume the entire document.")
        }
        element
    } catch (e: MalformedJsonException) {
        throw JsonSyntaxException(e)
    } catch (e: IOException) {
        throw JsonIOException(e)
    } catch (e: NumberFormatException) {
        throw JsonSyntaxException(e)
    }
}