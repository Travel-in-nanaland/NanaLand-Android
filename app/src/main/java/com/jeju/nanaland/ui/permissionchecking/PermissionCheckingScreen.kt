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
import com.jeju.nanaland.ui.component.common.CustomSurface
import com.jeju.nanaland.ui.component.common.CustomTopBar
import com.jeju.nanaland.ui.component.detailscreen.other.DetailScreenNotice
import com.jeju.nanaland.ui.component.permissionchecking.PermissionCheckingScreenGuideText
import com.jeju.nanaland.ui.component.permissionchecking.PermissionCheckingScreenHorizontalDivider
import com.jeju.nanaland.ui.component.permissionchecking.PermissionCheckingScreenItem

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
            title = "접근 권한 안내",
            onBackButtonClicked = moveToBackScreen
        )

        Column(Modifier.verticalScroll(rememberScrollState())) {
            Spacer(Modifier.height(32.dp))

            Box(Modifier.padding(start = 16.dp, end = 16.dp)) {
                PermissionCheckingScreenGuideText()
            }

            Spacer(Modifier.height(24.dp))

            PermissionCheckingScreenHorizontalDivider()

            Spacer(Modifier.height(24.dp))

            Column(Modifier.padding(start = 16.dp, end = 16.dp)) {
                PermissionCheckingScreenItem(
                    title = "전화 기기 정보",
                    description = "휴대폰 상태 및 ID 검증을 위해 접근 권한이 필요합니다.",
                    isNecessary = true
                )

                Spacer(Modifier.height(16.dp))

                DetailScreenNotice(
                    title = "알려드립니다",
                    content = "필수적 접근권한은 나나랜드인제주 서비스 이용에 반드시 필요하며, " +
                            "권한이 거부되면 서비스 이용이 제한되는 점 안내드립니다."
                )

                Spacer(Modifier.height(32.dp))

                PermissionCheckingScreenItem(
                    title = "저장공간",
                    description = "사진 저장 기능을 제공하기 위해 필요합니다.",
                    isNecessary = false
                )

                Spacer(Modifier.height(16.dp))

                PermissionCheckingScreenItem(
                    title = "위치정보",
                    description = "현재 위치기반으로 주변 맛집 관광지 등 정보를 제공하기 위해 필요합니다.",
                    isNecessary = false
                )

                Spacer(Modifier.height(16.dp))

                PermissionCheckingScreenItem(
                    title = "카메라",
                    description = "리뷰 사진 등록 및 맛집정보 등록을 위해 필요합니다.",
                    isNecessary = false
                )

                Spacer(Modifier.height(16.dp))

                PermissionCheckingScreenItem(
                    title = "알림",
                    description = "이벤트 및 혜택정보 알림 수신을 위해 필요합니다.",
                    isNecessary = false
                )

                Spacer(Modifier.height(16.dp))

                PermissionCheckingScreenItem(
                    title = "오디오 및 음악",
                    description = "영상 콘텐츠 등록을 위해 필요합니다.",
                    isNecessary = false
                )

                Spacer(Modifier.height(16.dp))

                DetailScreenNotice(
                    title = "알려드립니다",
                    content = "선택적 접근권한에 동의하지 않으셨을 경우에, 나나랜드인제주  " +
                            "서비스의 일부 기능을 제한 받으실 수 있는 점 안내드립니다."
                )
            }
        }
    }
}