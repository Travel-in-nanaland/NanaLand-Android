package com.jeju.nanaland.ui.signup

import android.annotation.SuppressLint
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.viewinterop.AndroidView
import com.jeju.nanaland.ui.component.common.CustomSurface

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun LocationPolicyDetailsScreen() {
    CustomSurface {
        val mUrl = "https://marbled-melon-1d2.notion.site/55d9bfc40f6c41728b5b16f79ed9b08d?pvs=4"

        AndroidView(
            factory = { context ->
                WebView(context).apply {
                    settings.javaScriptEnabled = true
                    settings.useWideViewPort = true
                    settings.domStorageEnabled = true
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                }
            },
            update = {
                it.loadUrl(mUrl)
            },
        )
    }
}