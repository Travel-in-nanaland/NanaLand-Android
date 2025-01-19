package com.jeju.nanaland.ui.component.thumbnail.parts

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.R
import com.jeju.nanaland.globalvalue.userdata.UserData
import com.jeju.nanaland.ui.component.common.dialog.DialogCommon
import com.jeju.nanaland.ui.component.common.dialog.DialogCommonType
import com.jeju.nanaland.ui.theme.NanaLandTheme
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.ui.ComponentPreview
import com.jeju.nanaland.util.ui.clickableNoEffect

@Composable
fun ThumbnailFavoriteButton(
    isFavorite: Boolean,
    onClick: () -> Unit,
    moveToSignInScreen: () -> Unit,
) {
    val isNonMemberGuideDialogShowing = remember { mutableStateOf(false) }

    Image(
        modifier = Modifier
            .size(20.dp)
            .clickableNoEffect {
                if (UserData.provider == "GUEST") {
                    isNonMemberGuideDialogShowing.value = true
                } else {
                    onClick()
                }
            }
            .background(getColor().white, shape = CircleShape)
            .padding(2.dp),
        painter = painterResource(R.drawable.ic_heart_filled),
        contentDescription = null,
        colorFilter = ColorFilter.tint(if (isFavorite) getColor().main else getColor().black25)
    )

    if (isNonMemberGuideDialogShowing.value) {
        DialogCommon(
            DialogCommonType.Login,
            onDismiss = { isNonMemberGuideDialogShowing.value = false },
            onYes = moveToSignInScreen,
        )
    }
}

@ComponentPreview
@Composable
private fun ThumbnailFavoriteButtonPreview1() {
    NanaLandTheme {
        ThumbnailFavoriteButton(
            isFavorite = true,
            onClick = {},
            moveToSignInScreen = {}
        )
    }
}

@ComponentPreview
@Composable
private fun ThumbnailFavoriteButtonPreview2() {
    NanaLandTheme {
        ThumbnailFavoriteButton(
            isFavorite = false,
            onClick = {},
            moveToSignInScreen = {}
        )
    }
}