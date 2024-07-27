package com.jeju.nanaland.ui.experience

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jeju.nanaland.R
import com.jeju.nanaland.globalvalue.type.ExperienceCategoryType
import com.jeju.nanaland.globalvalue.type.FestivalCategoryType
import com.jeju.nanaland.ui.component.common.CustomSurface
import com.jeju.nanaland.ui.component.common.CustomTopBar
import com.jeju.nanaland.ui.component.listscreen.category.ExperienceCategoryListTab
import com.jeju.nanaland.ui.component.listscreen.category.FestivalCategoryListTab
import com.jeju.nanaland.ui.theme.body01
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.resource.getString
import com.jeju.nanaland.util.ui.ScreenPreview

@Composable
fun ExperienceListScreen(
    moveToBackScreen: () -> Unit,
    viewModel: ExperienceListViewModel = hiltViewModel()
) {
    val selectedCategoryType = viewModel.selectedCategoryType.collectAsState().value
    ExperienceListScreen(
        selectedCategoryType = selectedCategoryType,
        updateSelectedCategoryType = viewModel::updateSelectedCategoryType,
        moveToBackScreen = moveToBackScreen,
        isContent = true
    )
}

@Composable
private fun ExperienceListScreen(
    selectedCategoryType: ExperienceCategoryType,
    updateSelectedCategoryType: (ExperienceCategoryType) -> Unit,
    moveToBackScreen: () -> Unit,
    isContent: Boolean
) {
    CustomSurface {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                CustomTopBar(
                    title = "이색 체험",
                    onBackButtonClicked = moveToBackScreen
                )

                Spacer(Modifier.height(16.dp))

                ExperienceCategoryListTab(
                    selectedCategoryType = selectedCategoryType,
                    updateSelectedCategoryType = updateSelectedCategoryType
                )
            }
        }
    }
}

@ScreenPreview
@Composable
private fun ExperienceListScreenPreview() {

}