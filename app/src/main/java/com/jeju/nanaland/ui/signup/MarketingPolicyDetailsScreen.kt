package com.jeju.nanaland.ui.signup

import android.annotation.SuppressLint
import android.view.ViewGroup
import android.webkit.WebView
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import com.jeju.nanaland.ui.component.common.CustomSurface
import com.jeju.nanaland.ui.theme.getColor

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun MarketingPolicyDetailsScreen() {
    CustomSurface {
        val mUrl = "https://marbled-melon-1d2.notion.site/a46f94192c5a43269d784b1c940634f7?pvs=4"

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