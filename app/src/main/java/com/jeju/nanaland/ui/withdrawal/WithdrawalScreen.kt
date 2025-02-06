package com.jeju.nanaland.ui.withdrawal

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jeju.nanaland.R
import com.jeju.nanaland.globalvalue.type.WithdrawalReasonType
import com.jeju.nanaland.ui.component.common.CustomSurface
import com.jeju.nanaland.ui.component.common.dialog.DialogCommon
import com.jeju.nanaland.ui.component.common.dialog.DialogCommonType
import com.jeju.nanaland.ui.component.common.topbar.TopBarCommon
import com.jeju.nanaland.ui.component.permissionchecking.PermissionCheckingScreenHorizontalDivider
import com.jeju.nanaland.ui.component.withdrawal.WithdrawalScreenCancelButton
import com.jeju.nanaland.ui.component.withdrawal.WithdrawalScreenGuideLineDescription
import com.jeju.nanaland.ui.component.withdrawal.WithdrawalScreenGuideLineHeading
import com.jeju.nanaland.ui.component.withdrawal.WithdrawalScreenLogo
import com.jeju.nanaland.ui.component.withdrawal.WithdrawalScreenReasonHeading
import com.jeju.nanaland.ui.component.withdrawal.WithdrawalScreenReasonItem
import com.jeju.nanaland.ui.component.withdrawal.WithdrawalScreenWithdrawButton
import com.jeju.nanaland.util.resource.getString
import com.jeju.nanaland.util.ui.clickableNoEffect
import com.jeju.nanaland.util.ui.scrollableVerticalArrangement

@Composable
fun WithdrawalScreen(
    moveToBackScreen: () -> Unit,
    moveToLanguageInitScreen: () -> Unit,
    viewModel: WithdrawalViewModel = hiltViewModel()
) {
    val selectedReason = viewModel.selectedReason.collectAsState().value
    WithdrawalScreen(
        selectedReason = selectedReason,
        updateSelectedReason = viewModel::updateSelectedReason,
        withdraw = viewModel::withdraw,
        moveToBackScreen = moveToBackScreen,
        moveToLanguageInitScreen = moveToLanguageInitScreen,
        isContent = true
    )
}

@Composable
private fun WithdrawalScreen(
    selectedReason: WithdrawalReasonType,
    updateSelectedReason: (WithdrawalReasonType) -> Unit,
    withdraw: (() -> Unit,Boolean) -> Unit,
    moveToBackScreen: () -> Unit,
    moveToLanguageInitScreen: () -> Unit,
    isContent: Boolean
) {
    val isDialogShowing = remember { mutableStateOf(false) }
    var logoClickCount by remember { mutableIntStateOf(0) }

    CustomSurface {
        TopBarCommon(
            title = getString(R.string.withdrawal_screen_회원탈퇴),
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

                    WithdrawalScreenGuideLineHeading(
                        modifier = Modifier.clickableNoEffect { logoClickCount += 1 }
                    )

                    Spacer(Modifier.height(8.dp))

                    WithdrawalScreenGuideLineDescription(
                        text = getString(R.string.withdrawal_screen_text1)
                    )

                    Spacer(Modifier.height(8.dp))

                    WithdrawalScreenGuideLineDescription(
                        text = getString(R.string.withdrawal_screen_text2)
                    )

                    Spacer(Modifier.height(8.dp))

                    WithdrawalScreenGuideLineDescription(
                        text = getString(R.string.withdrawal_screen_text3)
                    )

                    Spacer(Modifier.height(8.dp))

                    WithdrawalScreenGuideLineDescription(
                        text = getString(R.string.withdrawal_screen_text4)
                    )

                    Spacer(Modifier.height(8.dp))

                    WithdrawalScreenGuideLineDescription(
                        text = getString(R.string.withdrawal_screen_text5)
                    )
                }

                Spacer(Modifier.height(16.dp))

                PermissionCheckingScreenHorizontalDivider()

                Spacer(Modifier.height(16.dp))

                Column(Modifier.padding(start = 16.dp, end = 16.dp)) {
                    WithdrawalScreenReasonHeading()

                    Spacer(Modifier.height(8.dp))

                    WithdrawalScreenReasonItem(
                        isSelected = selectedReason == WithdrawalReasonType.InsufficientContent,
                        text = getString(R.string.withdrawal_screen_reason1),
                        onClick = { updateSelectedReason(WithdrawalReasonType.InsufficientContent) }
                    )

                    WithdrawalScreenReasonItem(
                        isSelected = selectedReason == WithdrawalReasonType.InconvenientService,
                        text = getString(R.string.withdrawal_screen_reason2),
                        onClick = { updateSelectedReason(WithdrawalReasonType.InconvenientService) }
                    )

                    WithdrawalScreenReasonItem(
                        isSelected = selectedReason == WithdrawalReasonType.InconvenientCommunity,
                        text = getString(R.string.withdrawal_screen_reason3),
                        onClick = { updateSelectedReason(WithdrawalReasonType.InconvenientCommunity) }
                    )

                    WithdrawalScreenReasonItem(
                        isSelected = selectedReason == WithdrawalReasonType.RareVisits,
                        text = getString(R.string.withdrawal_screen_reason4),
                        onClick = { updateSelectedReason(WithdrawalReasonType.RareVisits) }
                    )

                    Spacer(Modifier.height(16.dp))
                }
            }

            item {
                Column(Modifier.padding(start = 16.dp, end = 16.dp)) {
                    Row {
                        WithdrawalScreenWithdrawButton {
                            if (selectedReason != WithdrawalReasonType.Idle) {
                                isDialogShowing.value = true
                            }
                        }

                        Spacer(Modifier.weight(1f))

                        WithdrawalScreenCancelButton { moveToBackScreen() }
                    }
                }

                Spacer(Modifier.height(20.dp))
            }
        }
    }

    if (isDialogShowing.value) {
        DialogCommon(
            DialogCommonType.Withdrawal,
            onDismiss = { isDialogShowing.value = false },
            onYes = { withdraw(moveToLanguageInitScreen, false) }
        )
    }
    if(logoClickCount >= 20) {
        AlertDialog(
            title = {
                Text(text = "강제탈퇴")
            },
            onDismissRequest = {},
            confirmButton = {
                TextButton(
                    onClick = {
                        withdraw(moveToLanguageInitScreen, true)
                    }
                ) {
                    Text("Confirm")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        logoClickCount = 0
                    }
                ) {
                    Text("Dismiss")
                }
            }
        )
    }
}