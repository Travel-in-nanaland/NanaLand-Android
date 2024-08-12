package com.jeju.nanaland.ui.component.detailscreen.other.parts.description

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.R
import com.jeju.nanaland.globalvalue.userdata.UserData
import com.jeju.nanaland.ui.component.nonmember.NonMemberGuideDialog
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.ui.clickableNoEffect

@Composable
fun DetailScreenFavoriteButton2(
    isFavorite: Boolean,
    onClick: () -> Unit,
    moveToSignInScreen: () -> Unit,
) {
    val isNonMemberGuideDialogShowing = remember { mutableStateOf(false) }

    Image(
        modifier = Modifier
            .size(32.dp)
            .clickableNoEffect {
                if (UserData.provider == "GUEST") {
                    isNonMemberGuideDialogShowing.value = true
                } else {
                    onClick()
                }
            },
        painter = painterResource(if (isFavorite) R.drawable.ic_heart_filled else R.drawable.ic_heart_outlined_thick),
        contentDescription = null,
        colorFilter = ColorFilter.tint(getColor().main)
    )

    if (isNonMemberGuideDialogShowing.value) {
        NonMemberGuideDialog(
            onCloseClick = { isNonMemberGuideDialogShowing.value = false },
            moveToSignInScreen = moveToSignInScreen
        )
    }
}