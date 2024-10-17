package com.jeju.nanaland.ui.permissionchecking

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.component.common.CustomSurface
import com.jeju.nanaland.ui.component.common.text.TextWithPointColor
import com.jeju.nanaland.ui.component.common.topbar.CustomTopBar
import com.jeju.nanaland.ui.component.detailscreen.other.DetailScreenNotice
import com.jeju.nanaland.ui.component.permissionchecking.PermissionCheckingScreenHorizontalDivider
import com.jeju.nanaland.ui.component.permissionchecking.PermissionCheckingScreenItem
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.ui.theme.title02
import com.jeju.nanaland.ui.theme.title02Bold
import com.jeju.nanaland.util.resource.getString

@Composable
fun PermissionCheckingScreen(
    moveToBackScreen: () -> Unit,
) {
    PermissionCheckingScreen(
        moveToBackScreen = moveToBackScreen,
        isContent = true
    )
}

@Composable
private fun PermissionCheckingScreen(
    moveToBackScreen: () -> Unit,
    isContent: Boolean
) {
    CustomSurface {
        CustomTopBar(
            title = getString(R.string.permission_checking_screen_접근_권한_안내),
            onBackButtonClicked = moveToBackScreen
        )

        Column(Modifier.verticalScroll(rememberScrollState())) {
            Spacer(Modifier.height(32.dp))

            Box(Modifier.padding(start = 16.dp, end = 16.dp)) {
                TextWithPointColor(
                    text = getString(R.string.permission_checking_screen_guide),
                    style = title02,
                    color = getColor().black,
                    pointStyle = title02Bold,
                    pointColor = getColor().black
                )
            }

            Spacer(Modifier.height(24.dp))

            PermissionCheckingScreenHorizontalDivider()

            Spacer(Modifier.height(24.dp))

            Column(Modifier.padding(start = 16.dp, end = 16.dp)) {
                PermissionCheckingScreenItem(
                    title = getString(R.string.permission_checking_screen_전화_기기_정보),
                    description = getString(R.string.permission_checking_screen_전화_기기_정보_description),
                    isNecessary = true
                )

                Spacer(Modifier.height(16.dp))

                DetailScreenNotice(
                    title = getString(R.string.permission_checking_screen_알려드립니다),
                    content = getString(R.string.permission_checking_screen_text1)
                )

                Spacer(Modifier.height(32.dp))

                PermissionCheckingScreenItem(
                    title = getString(R.string.permission_checking_screen_저장공간),
                    description = getString(R.string.permission_checking_screen_저장공간_description),
                    isNecessary = false
                )

                Spacer(Modifier.height(16.dp))

                PermissionCheckingScreenItem(
                    title = getString(R.string.permission_checking_screen_위치정보),
                    description = getString(R.string.permission_checking_screen_위치정보_description),
                    isNecessary = false
                )

                Spacer(Modifier.height(16.dp))

                PermissionCheckingScreenItem(
                    title = getString(R.string.permission_checking_screen_카메라),
                    description = getString(R.string.permission_checking_screen_카메라_description),
                    isNecessary = false
                )

                Spacer(Modifier.height(16.dp))

                PermissionCheckingScreenItem(
                    title = getString(R.string.permission_checking_screen_알림),
                    description = getString(R.string.permission_checking_screen_알림_description),
                    isNecessary = false
                )

                Spacer(Modifier.height(16.dp))

                PermissionCheckingScreenItem(
                    title = getString(R.string.permission_checking_screen_오디오_및_음악),
                    description = getString(R.string.permission_checking_screen_오디오_및_음악_description),
                    isNecessary = false
                )

                Spacer(Modifier.height(16.dp))

                DetailScreenNotice(
                    title = getString(R.string.permission_checking_screen_알려드립니다),
                    content = getString(R.string.permission_checking_screen_text2)
                )

                Spacer(Modifier.height(20.dp))
            }
        }
    }
}