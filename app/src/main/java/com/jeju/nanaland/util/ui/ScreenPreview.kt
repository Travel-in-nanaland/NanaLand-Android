package com.jeju.nanaland.util.ui

import androidx.compose.ui.tooling.preview.Preview

@Preview(apiLevel = 33, showBackground = true, device = "spec:shape=Normal,width=375,height=800,unit=dp,dpi=160", fontScale = 1f)
annotation class ScreenPreview

@Preview(apiLevel = 33, showBackground = true)
annotation class ComponentPreview

@Preview(apiLevel = 33, showBackground = true, backgroundColor = 0xFF000000)
annotation class ComponentPreviewBlack