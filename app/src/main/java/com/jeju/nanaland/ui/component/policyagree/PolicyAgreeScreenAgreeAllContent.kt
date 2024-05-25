package com.jeju.nanaland.ui.component.policyagree

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.ui.component.policyagree.parts.PolicyAgreeScreenAgreeAllContentText
import com.jeju.nanaland.ui.component.policyagree.parts.PolicyAgreeScreenCheckButton

@Composable
fun PolicyAgreeScreenAgreeAllContent(
    isSelected: Boolean,
    toggleIsSelected: () -> Unit,
) {
    Row(
        modifier = Modifier
            .height(48.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        PolicyAgreeScreenCheckButton(
            isSelected = isSelected,
            onClick = toggleIsSelected
        )

        Spacer(Modifier.width(8.dp))

        PolicyAgreeScreenAgreeAllContentText()
    }
}