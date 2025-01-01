package com.jeju.nanaland.ui.component.policyagree

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.ui.component.policyagree.parts.PolicyAgreeScreenArrowIcon
import com.jeju.nanaland.ui.component.policyagree.parts.PolicyAgreeScreenCategoryText
import com.jeju.nanaland.ui.component.policyagree.parts.PolicyAgreeScreenCheckButton
import com.jeju.nanaland.util.ui.clickableNoEffect

@Composable
fun PolicyAgreeScreenCategoryContent(
    isSelected: Boolean,
    toggleIsSelected: () -> Unit,
    text: String,
    isNecessary: Boolean,
    moveToDetailsScreen: () -> Unit,
) {
    Row(
        modifier = Modifier.padding(vertical = 13.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        PolicyAgreeScreenCheckButton(
            isSelected = isSelected,
            onClick = toggleIsSelected
        )

        Spacer(Modifier.width(8.dp))

        Row(
            modifier = Modifier
                .clickableNoEffect { moveToDetailsScreen() },
            verticalAlignment = Alignment.CenterVertically
        ) {
            PolicyAgreeScreenCategoryText(
                text = text,
                isNecessary = isNecessary
            )

            PolicyAgreeScreenArrowIcon()
        }
    }
}