package com.jeju.nanaland.ui.theme

import android.graphics.BlurMaskFilter
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.unit.dp

fun Modifier.shadowBottomNav() = dropShadow(
    positionX = 0,
    positionY = -3,
    blur = 10,
    spread = 0,
    color = 0x262627,
    alpha = 10
)

fun Modifier.shadowInfo() = dropShadow(
    positionX = 0,
    positionY = 5,
    blur = 5,
    spread = 0,
    color = 0x262627,
    alpha = 15
)

fun Modifier.shadowTopNav() = dropShadow(
    positionX = 0,
    positionY = 5,
    blur = 8,
    spread = 0,
    color = 0x151515,
    alpha = 5
)


@Stable
fun Modifier.dropShadow(
    positionX: Int,
    positionY: Int,
    blur: Int,
    spread: Int,
    color: Int,
    alpha: Int
) = this.drawBehind {
    val shadowSize = Size(size.width + spread.dp.toPx(), size.height + spread.dp.toPx())
    val shadowOutline = RoundedCornerShape(0.dp).createOutline(shadowSize, layoutDirection, this)

    val paint = Paint().apply {
        this.color = Color(color).copy(alpha = alpha / 100f)
    }

    val frameworkPaint = paint.asFrameworkPaint()
    frameworkPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_OUT)
    if (blur.dp.toPx() > 0) {
        frameworkPaint.maskFilter = BlurMaskFilter(blur.dp.toPx(), BlurMaskFilter.Blur.NORMAL)
    }

    drawIntoCanvas { canvas ->
        canvas.save()
        canvas.translate(positionX.dp.toPx(), positionY.dp.toPx())
        canvas.drawOutline(shadowOutline, paint)
        canvas.restore()
    }
}
