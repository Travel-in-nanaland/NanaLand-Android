package com.jeju.nanaland.ui.settings.policy

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.component.common.CustomSurface
import com.jeju.nanaland.ui.component.common.topbar.TopBarCommon
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
        TopBarCommon(
            title = getString(R.string.settings_screen_약관_및_정책),
            onBackButtonClicked = { moveToBackScreen() }
        )

        Spacer(Modifier.height(24.dp))

        SettingsScreenCategoryItem(
            text = getString(R.string.policy_agree_screen_marketing_policy),
            onClick = { updatePolicyAgreement("MARKETING", !isMarketingPolicyAgreed) },
            endView = {
                PolicySettingScreenCheckIcon(
                    isSelected = isMarketingPolicyAgreed
                )
            }
        )

        SettingsScreenCategoryItem(
            text = getString(R.string.policy_agree_screen_location_policy),
            onClick = { updatePolicyAgreement("LOCATION_SERVICE", !isLocationPolicyAgreed) },
            endView = {
                PolicySettingScreenCheckIcon(
                    isSelected = isLocationPolicyAgreed
                )
            }
        )

        PolicySettingScreenWarningText()
    }
}