package com.nanaland.ui.nature

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.nanaland.domain.entity.nature.NatureContentData
import com.nanaland.ui.component.common.CustomSurface
import com.nanaland.ui.component.common.CustomTopBarWithShadow
import com.nanaland.ui.component.detailscreen.DetailScreenDescription
import com.nanaland.ui.component.detailscreen.DetailScreenTopBannerImage
import com.nanaland.util.ui.ScreenPreview
import com.nanaland.util.ui.UiState

@Composable
fun NatureContentScreen(
    contentId: Long?,
    moveToBackScreen: () -> Unit,
    viewModel: NatureContentViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.getNatureContent(contentId)
    }
    val natureContent = viewModel.natureContent.collectAsState().value
    NatureContentScreen(
        natureContent = natureContent,
        moveToBackScreen = moveToBackScreen,
        isContent = true
    )
}

@Composable
private fun NatureContentScreen(
    natureContent: UiState<NatureContentData>,
    moveToBackScreen: () -> Unit,
    isContent: Boolean
) {
    CustomSurface {
        when (natureContent) {
            is UiState.Loading -> {}
            is UiState.Success -> {
                CustomTopBarWithShadow(
                    title = "7대 자연",
                    onBackButtonClicked = moveToBackScreen
                )

                Column(
                    modifier = Modifier.verticalScroll(rememberScrollState())
                ) {
                    DetailScreenTopBannerImage(imageUri = natureContent.data.originUrl)

                    DetailScreenDescription(
                        isLiked = natureContent.data.favorite,
                        tag = natureContent.data.addressTag,
                        title = natureContent.data.title,
                        content = natureContent.data.content,
                        onLikeButtonClicked = {},
                        onShareButtonClicked = {}
                    )
                }
            }
            is UiState.Failure -> {}
        }
    }
}

@ScreenPreview
@Composable
private fun NatureContentScreenPreview() {

}