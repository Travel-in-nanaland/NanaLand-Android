package com.jeju.nanaland.ui.settings.policy

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.component.common.CustomSurface
import com.jeju.nanaland.ui.component.common.topbar.CustomTopBar
import com.jeju.nanaland.ui.component.settings.SettingsScreenCategoryItem
import com.jeju.nanaland.ui.component.settings.policy.PolicySettingScreenCheckIcon
import com.jeju.nanaland.ui.component.settings.policy.PolicySettingScreenWarningText
import com.jeju.nanaland.util.resource.getString

@Composable
fun PolicySettingScreen(
    moveToBackScreen: () -> Unit,
    viewModel: PolicySettingViewModel = hiltViewModel()
) {
    val isMarketingPolicyAgreed = viewModel.isMarketingPolicyAgreed.collectAsState().value
    val isLocationPolicyAgreed = viewModel.isLocationPolicyAgreed.collectAsState().value
    PolicySettingScreen(
        isMarketingPolicyAgreed = isMarketingPolicyAgreed,
        isLocationPolicyAgreed = isLocationPolicyAgreed,
        updatePolicyAgreement = viewModel::updatePolicyAgreement,
        moveToBackScreen = moveToBackScreen,
        isContent = true
    )
}

@Composable
private fun PolicySettingScreen(
    isMarketingPolicyAgreed: Boolean,
    isLocationPolicyAgreed: Boolean,
    updatePolicyAgreement: (String, Boolean) -> Unit,
    moveToBackScreen: () -> Unit,
    isContent: Boolean
) {
    CustomSurface {
        CustomTopBar(
            title = "약관 및 정책",
            onBackButtonClicked = { moveToBackScreen() }
        )

        Spacer(Modifier.height(24.dp))

        Box(
            contentAlignment = Alignment.CenterEnd
        ) {
            SettingsScreenCategoryItem(
                text = getString(R.string.policy_agree_screen_marketing_policy),
                onClick = { updatePolicyAgreement("MARKETING", !isMarketingPolicyAgreed) }
            )

            PolicySettingScreenCheckIcon(
                isSelected = isMarketingPolicyAgreed
            )
        }

        Box(
            contentAlignment = Alignment.CenterEnd
        ) {
            SettingsScreenCategoryItem(
                text = getString(R.string.policy_agree_screen_location_policy),
                onClick = { updatePolicyAgreement("LOCATION_SERVICE", !isLocationPolicyAgreed) }
            )

            PolicySettingScreenCheckIcon(
                isSelected = isLocationPolicyAgreed
            )
        }

        PolicySettingScreenWarningText()
    }
}