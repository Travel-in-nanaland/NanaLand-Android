package com.jeju.nanaland.ui.component.permissionchecking

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.ui.component.permissionchecking.parts.PermissionCheckingScreenDescription
import com.jeju.nanaland.ui.component.permissionchecking.parts.PermissionCheckingScreenTitle

@Composable
fun PermissionCheckingScreenItem(
    title: String,
    description: String,
    isNecessary: Boolean
) {
    PermissionCheckingScreenTitle(
        text = title,
        isNecessary = isNecessary
    )

    Spacer(Modifier.height(4.dp))

    PermissionCheckingScreenDescription(text = description)
}