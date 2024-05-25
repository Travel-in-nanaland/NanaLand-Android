package com.jeju.nanaland.ui.component.common

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsAnimationCompat
import androidx.core.view.WindowInsetsCompat
import com.jeju.nanaland.globalvalue.constant.SYSTEM_NAVIGATION_BAR_HEIGHT
import com.jeju.nanaland.globalvalue.constant.SYSTEM_STATUS_BAR_HEIGHT
import com.jeju.nanaland.ui.theme.getColor

@Composable
fun CustomSurface(
    content: @Composable ColumnScope.(Boolean) -> Unit
) {
    val view = LocalView.current
    val context = LocalContext.current
    val isImeKeyboardShowing = remember { mutableStateOf(false) }
    LaunchedEffect(view) {
        val activity = context.findActivity()
        if (activity != null) {
            ViewCompat.setOnApplyWindowInsetsListener(activity.window.decorView.rootView) { _, insets ->
//            val imeVisible = insets.isVisible(WindowInsetsCompat.Type.ime())
//            val imeHeight = insets.getInsets(WindowInsetsCompat.Type.ime()).bottom
                insets
            }

            ViewCompat.setWindowInsetsAnimationCallback(
                activity.window.decorView.rootView,
                object : WindowInsetsAnimationCompat.Callback(DISPATCH_MODE_STOP) {
                    override fun onProgress(
                        insets: WindowInsetsCompat,
                        runningAnimations: MutableList<WindowInsetsAnimationCompat>
                    ): WindowInsetsCompat {
                        val imeHeight = insets.getInsets(WindowInsetsCompat.Type.ime()).bottom
                        val sysBarInsets = insets.getInsets(WindowInsetsCompat.Type.systemBars())

                        val updatedHeight = if(imeHeight - sysBarInsets.bottom < 0) 0 else imeHeight - sysBarInsets.bottom
                        val viewHeight = view.height
                        isImeKeyboardShowing.value = updatedHeight > viewHeight * 0.2f
//                    LogUtil.e("updatedHeight", "${updatedHeight}")
//                    LogUtil.e("viewHeight", "${viewHeight}")
                        // translate your view with updated height
                        return insets
                    }
                }
            )
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(getColor().white)
    ) {
        Spacer(
            Modifier
                .fillMaxWidth()
                .height((SYSTEM_STATUS_BAR_HEIGHT).dp)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            content(isImeKeyboardShowing.value)
        }
        if (!isImeKeyboardShowing.value) {
            Spacer(
                Modifier
                    .fillMaxWidth()
                    .height((SYSTEM_NAVIGATION_BAR_HEIGHT).dp)
            )
        }
    }
}

fun Context.findActivity(): Activity? {
    var context = this
    while (context is ContextWrapper) {
        if (context is Activity) return context
        context = context.baseContext
    }
    return null
}