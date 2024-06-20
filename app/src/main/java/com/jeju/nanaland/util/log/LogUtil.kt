package com.jeju.nanaland.util.log

import android.util.Log
import com.jeju.nanaland.BuildConfig
import com.jeju.nanaland.util.network.NetworkResult

object LogUtil {
    fun printNetworkLog(request: Any?, response: NetworkResult<Any>, name: String) {
        when (response) {
            is NetworkResult.Success -> {
                e("NetworkResultLog", "$name Success\n[request]\n" +
                        "$request\n[response code]\n" +
                        "${response.code}\n[response data]\n" +
                        "${response.data}\n-")
            }
            is NetworkResult.Error -> {
                e("NetworkResultLog", "$name Error\n[request]\n" +
                        "$request\n[response code]\n" +
                        "${response.code}\n" +
                        "[response data]\n" +
                        "${response.message}\n-")
            }
            is NetworkResult.Exception -> {
                e("NetworkResultLog", "$name Exception\n" +
                        "[request]\n" +
                        "$request\n" +
                        "[error]\n" +
                        "${response.e}\n" +
                        "-")
            }
        }
    }

    fun e (tag: String, message: String) {
        if (BuildConfig.DEBUG) Log.e(tag, message) else Unit
    }
}