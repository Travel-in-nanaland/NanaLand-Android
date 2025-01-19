package com.jeju.nanaland.ui.profile.root.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.domain.entity.member.UserProfile
import com.jeju.nanaland.ui.profile.root.component.parts.ProfileScreenIntroducePart
import com.jeju.nanaland.ui.profile.root.component.parts.ProfileScreenTypePart

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProfileScreenProfileSection(
    profile: UserProfile,
    isMine: Boolean,
    moveToSignInScreen: () -> Unit,
    moveToProfileModificationScreen: () -> Unit,
    moveToTypeTestScreen: () -> Unit,
    moveToTypeTestResultScreen: () -> Unit
) {
    Column {
        ProfileScreenIntroducePart(
            profile = profile,
            isMine = isMine,
            moveToSignInScreen = moveToSignInScreen,
            moveToProfileModificationScreen = moveToProfileModificationScreen
        )

        Spacer(Modifier.height(12.dp))
        if(profile.provider == "GUEST" || profile.travelType == null)
            Spacer(Modifier.height(12.dp))

        if(profile.provider != "GUEST") {
            ProfileScreenTypePart(
                profile = profile,
                isMine = isMine,
                moveToTypeTestScreen = moveToTypeTestScreen,
                moveToTypeTestResultScreen = moveToTypeTestResultScreen
            )
            Spacer(Modifier.height(20.dp))
        }
    }

}
