package com.jeju.nanaland.util.ui

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathOperation
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection

class TicketShape(
    private val ovalWidth: Float,
    private val ovalHeight: Float
) : Shape {

    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        return Outline.Generic(
            path = drawTicketPath(
                size = size,
                ovalWidth = ovalWidth,
                ovalHeight = ovalHeight
            )
        )
    }
}

fun drawTicketPath(size: Size, ovalWidth: Float, ovalHeight: Float): Path {
    return Path().apply {
        val rectPath = Path().apply {
            reset()
            lineTo(x = size.width, y = 0f)
            lineTo(x = size.width, y = size.height)
            lineTo(x = 0f, y = size.height)
            lineTo(x = 0f, y = 0f)
        }

        val ovalPath = Path().apply {
            addOval(
                Rect(
                    left = size.width / 2 - ovalWidth / 2,
                    top = -ovalHeight / 2,
                    right = size.width / 2 + ovalWidth / 2,
                    bottom = ovalHeight / 2
                )
            )
        }
//        reset()
//        lineTo(x = size.width / 2 - cornerRadius, y = 0f)
//        arcTo(
//            rect = Rect(
//                left = size.width / 2 - cornerRadius,
//                top = -cornerRadius,
//                right = size.width / 2 + cornerRadius,
//                bottom = cornerRadius
//            ),
//            startAngleDegrees = 180.0f,
//            sweepAngleDegrees = -180.0f,
//            forceMoveTo = false
//        )
//        lineTo(x = size.width, y = 0f)
//        lineTo(x = size.width, y = size.height)
//        lineTo(x = 0f, y = size.height)
//        lineTo(x = 0f, y = 0f)

        op(rectPath, ovalPath, PathOperation.Difference)
    }
}