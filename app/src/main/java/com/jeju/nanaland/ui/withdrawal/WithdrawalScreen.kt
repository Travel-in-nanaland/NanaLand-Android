package com.jeju.nanaland.ui.withdrawal

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jeju.nanaland.globalvalue.type.WithdrawalReasonType
import com.jeju.nanaland.ui.component.common.CustomSurface
import com.jeju.nanaland.ui.component.common.CustomTopBar
import com.jeju.nanaland.ui.component.permissionchecking.PermissionCheckingScreenHorizontalDivider
import com.jeju.nanaland.ui.component.withdrawal.WithdrawalScreenAgreeText
import com.jeju.nanaland.ui.component.withdrawal.WithdrawalScreenCancelButton
import com.jeju.nanaland.ui.component.withdrawal.WithdrawalScreenConfirmDialog
import com.jeju.nanaland.ui.component.withdrawal.WithdrawalScreenGuideLineDescription
import com.jeju.nanaland.ui.component.withdrawal.WithdrawalScreenGuideLineHeading
import com.jeju.nanaland.ui.component.withdrawal.WithdrawalScreenLogo
import com.jeju.nanaland.ui.component.withdrawal.WithdrawalScreenReasonHeading
import com.jeju.nanaland.ui.component.withdrawal.WithdrawalScreenReasonItem
import com.jeju.nanaland.ui.component.withdrawal.WithdrawalScreenWithdrawButton
import com.jeju.nanaland.util.ui.scrollableVerticalArrangement

@Composable
fun WithdrawalScreen(
    moveToBackScreen: () -> Unit,
    moveToSignInScreen: () -> Unit,
    viewModel: WithdrawalViewModel = hiltViewModel()
) {
    val selectedReason = viewModel.selectedReason.collectAsState().value
    WithdrawalScreen(
        selectedReason = selectedReason,
        updateSelectedReason = viewModel::updateSelectedReason,
        withdraw = viewModel::withdraw,
        moveToBackScreen = moveToBackScreen,
        moveToSignInScreen = moveToSignInScreen,
        isContent = true
    )
}

@Composable
private fun WithdrawalScreen(
    selectedReason: WithdrawalReasonType,
    updateSelectedReason: (WithdrawalReasonType) -> Unit,
    withdraw: (() -> Unit,) -> Unit,
    moveToBackScreen: () -> Unit,
    moveToSignInScreen: () -> Unit,
    isContent: Boolean
) {
    val isDialogShowing = remember { mutableStateOf(false) }
    CustomSurface {
        CustomTopBar(
            title = "회원탈퇴",
            onBackButtonClicked = { moveToBackScreen() }
        )

        Spacer(Modifier.height(32.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = remember { scrollableVerticalArrangement }
        ) {
            item {
                Column(Modifier.padding(start = 16.dp, end = 16.dp)) {
                    WithdrawalScreenLogo()

                    Spacer(Modifier.height(16.dp))

                    WithdrawalScreenGuideLineHeading()

                    Spacer(Modifier.height(8.dp))

                    WithdrawalScreenGuideLineDescription(
                        text = "1. 본 서비스를 탈퇴하시면 나나랜드인제주 서비스 기반으로 제공되는 모든 서비스로부터 해지 및 소멸되는 점을 안내드립니다."
                    )

                    Spacer(Modifier.height(8.dp))

                    WithdrawalScreenGuideLineDescription(
                        text = "2. 회원 탈퇴를 하시면, 보유하고 계신 각종 쿠폰, 포인트는 자동 소멸되며 재가입하실 경우에도 복원되지 않습니다."
                    )

                    Spacer(Modifier.height(8.dp))

                    WithdrawalScreenGuideLineDescription(
                        text = "3. 서비스 탈퇴 후 전자상거래법에 의해 보존해야 하는 거래기록은 90일간 보관됩니다."
                    )

                    Spacer(Modifier.height(8.dp))

                    WithdrawalScreenGuideLineDescription(
                        text = "4. 회원 탈퇴 시 회원가입 이벤트에는 재 참여하실 수 없습니다."
                    )

                    Spacer(Modifier.height(8.dp))

                    WithdrawalScreenGuideLineDescription(
                        text = "5. 탈퇴 후 90일 이내에 재가입을 하시면, 기존 계정으로 사용하실 수 있습니다."
                    )

                    Spacer(Modifier.height(16.dp))

                    WithdrawalScreenAgreeText()
                }

                Spacer(Modifier.height(16.dp))

                PermissionCheckingScreenHorizontalDivider()

                Spacer(Modifier.height(16.dp))

                Column(Modifier.padding(start = 16.dp, end = 16.dp)) {
                    WithdrawalScreenReasonHeading()

                    Spacer(Modifier.height(8.dp))

                    WithdrawalScreenReasonItem(
                        isSelected = selectedReason == WithdrawalReasonType.InsufficientContent,
                        text = "콘텐츠 내용 부족",
                        onClick = { updateSelectedReason(WithdrawalReasonType.InsufficientContent) }
                    )

                    WithdrawalScreenReasonItem(
                        isSelected = selectedReason == WithdrawalReasonType.InconvenientService,
                        text = "서비스 이용 불편",
                        onClick = { updateSelectedReason(WithdrawalReasonType.InconvenientService) }
                    )

                    WithdrawalScreenReasonItem(
                        isSelected = selectedReason == WithdrawalReasonType.InconvenientCommunity,
                        text = "커뮤니티 사용 불편",
                        onClick = { updateSelectedReason(WithdrawalReasonType.InconvenientCommunity) }
                    )

                    WithdrawalScreenReasonItem(
                        isSelected = selectedReason == WithdrawalReasonType.RareVisits,
                        text = "방문 횟수 거의 없음",
                        onClick = { updateSelectedReason(WithdrawalReasonType.RareVisits) }
                    )

                    Spacer(Modifier.height(16.dp))
                }
            }

            item {
                Column(Modifier.padding(start = 16.dp, end = 16.dp)) {
                    Row {
                        WithdrawalScreenWithdrawButton { isDialogShowing.value = true }

                        Spacer(Modifier.weight(1f))

                        WithdrawalScreenCancelButton { moveToBackScreen() }
                    }
                }

                Spacer(Modifier.height(20.dp))
            }
        }
    }

    if (isDialogShowing.value) {
        WithdrawalScreenConfirmDialog(
            onConfirm = { withdraw(moveToSignInScreen) },
            onCancel = { isDialogShowing.value = false }
        )
    }
}