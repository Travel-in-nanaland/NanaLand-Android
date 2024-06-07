package com.jeju.nanaland.ui.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.component.common.CustomSurface
import com.jeju.nanaland.ui.component.policyagree.PolicyAgreeScreenAgreeAllContent
import com.jeju.nanaland.ui.component.policyagree.PolicyAgreeScreenBottomButton
import com.jeju.nanaland.ui.component.policyagree.PolicyAgreeScreenCategoryContent
import com.jeju.nanaland.ui.component.policyagree.PolicyAgreeScreenLogo
import com.jeju.nanaland.ui.component.policyagree.PolicyAgreeScreenWelcomeText
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.resource.getString

@Composable
fun PolicyAgreeScreen(
    moveToPrivacyPolicyDetailsScreen: () -> Unit,
    moveToMarketingPolicyScreen: () -> Unit,
    moveToLocationPolicyScreen: () -> Unit,
    moveToSignUpProfileSettingScreen: (Boolean, Boolean, Boolean) -> Unit,
) {
    PolicyAgreeScreen(
        moveToPrivacyPolicyDetailsScreen =  moveToPrivacyPolicyDetailsScreen,
        moveToMarketingPolicyScreen = moveToMarketingPolicyScreen,
        moveToLocationPolicyScreen = moveToLocationPolicyScreen,
        moveToSignUpProfileSettingScreen = moveToSignUpProfileSettingScreen,
        isContent = true
    )
}

@Composable
private fun PolicyAgreeScreen(
    moveToPrivacyPolicyDetailsScreen: () -> Unit,
    moveToMarketingPolicyScreen: () -> Unit,
    moveToLocationPolicyScreen: () -> Unit,
    moveToSignUpProfileSettingScreen: (Boolean, Boolean, Boolean) -> Unit,
    isContent: Boolean
) {
    val isPrivacyPolicyAgreed = remember { mutableStateOf(false) }
    val isMarketingPolicyAgreed = remember { mutableStateOf(false) }
    val isLocationPolicyAgreed = remember { mutableStateOf(false) }
    CustomSurface {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Column(Modifier.padding(start = 16.dp, end = 16.dp)) {
                PolicyAgreeScreenLogo()

                Spacer(Modifier.height(16.dp))

                PolicyAgreeScreenWelcomeText()

                Spacer(Modifier.height(80.dp))

                PolicyAgreeScreenAgreeAllContent(
                    isSelected = isPrivacyPolicyAgreed.value && isMarketingPolicyAgreed.value && isLocationPolicyAgreed.value,
                    toggleIsSelected = {
                        if (isPrivacyPolicyAgreed.value && isMarketingPolicyAgreed.value && isLocationPolicyAgreed.value) {
                            isPrivacyPolicyAgreed.value = false
                            isMarketingPolicyAgreed.value = false
                            isLocationPolicyAgreed.value = false
                        } else {
                            isPrivacyPolicyAgreed.value = true
                            isMarketingPolicyAgreed.value = true
                            isLocationPolicyAgreed.value = true
                        }
                    }
                )
            }

            Spacer(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 8.dp)
                    .height(1.dp)
                    .background(getColor().gray02)
            )

            Column(Modifier.padding(start = 16.dp, end = 16.dp)) {
                PolicyAgreeScreenCategoryContent(
                    isSelected = isPrivacyPolicyAgreed.value,
                    toggleIsSelected = { isPrivacyPolicyAgreed.value = !isPrivacyPolicyAgreed.value },
                    text = getString(R.string.policy_agree_screen_privacy_policy),
                    isNecessary = true,
                    moveToDetailsScreen = { moveToPrivacyPolicyDetailsScreen() }
                )

                PolicyAgreeScreenCategoryContent(
                    isSelected = isMarketingPolicyAgreed.value,
                    toggleIsSelected = { isMarketingPolicyAgreed.value = !isMarketingPolicyAgreed.value },
                    text = getString(R.string.policy_agree_screen_marketing_policy),
                    isNecessary = false,
                    moveToDetailsScreen = { moveToMarketingPolicyScreen() }
                )

                PolicyAgreeScreenCategoryContent(
                    isSelected = isLocationPolicyAgreed.value,
                    toggleIsSelected = { isLocationPolicyAgreed.value = !isLocationPolicyAgreed.value },
                    text = getString(R.string.policy_agree_screen_location_policy),
                    isNecessary = false,
                    moveToDetailsScreen = { moveToLocationPolicyScreen() }
                )
            }
        }

        PolicyAgreeScreenBottomButton(isActivated = isPrivacyPolicyAgreed.value) {
            moveToSignUpProfileSettingScreen(
                isPrivacyPolicyAgreed.value,
                isMarketingPolicyAgreed.value,
                isLocationPolicyAgreed.value
            )
        }

        Spacer(Modifier.height(24.dp))
    }
}