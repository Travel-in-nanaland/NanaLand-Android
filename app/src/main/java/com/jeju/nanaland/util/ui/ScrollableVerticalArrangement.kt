package com.jeju.nanaland.util.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.unit.Density

val scrollableVerticalArrangement = object : Arrangement.Vertical {
    override fun Density.arrange(
        totalSize: Int,
        sizes: IntArray,
        outPositions: IntArray
    ) {
        var currentOffset = 0
        sizes.forEachIndexed { index, size ->
            if (index == sizes.lastIndex) {
                outPositions[index] = totalSize - size
            } else {
                // 마지막 아이템이 아니면
                outPositions[index] = currentOffset
                currentOffset += size
            }
        }
    }
}