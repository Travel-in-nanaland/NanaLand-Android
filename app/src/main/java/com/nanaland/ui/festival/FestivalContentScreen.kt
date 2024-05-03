package com.nanaland.ui.festival

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.nanaland.domain.entity.festival.FestivalContentData
import com.nanaland.ui.component.common.CustomSurface
import com.nanaland.ui.component.common.CustomTopBarWithShadow
import com.nanaland.ui.component.detailscreen.DetailScreenDescription
import com.nanaland.ui.component.detailscreen.DetailScreenTopBannerImage
import com.nanaland.util.ui.ScreenPreview
import com.nanaland.util.ui.UiState

@Composable
fun FestivalContentScreen(
    contentId: Long?,
    moveToBackScreen: () -> Unit,
    viewModel: FestivalContentViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.getFestivalContent(contentId)
    }
    val festivalContent = viewModel.festivalContent.collectAsState().value
    FestivalContentScreen(
        festivalContent = festivalContent,
        moveToBackScreen = moveToBackScreen,
        isContent = true
    )
}

@Composable
private fun FestivalContentScreen(
    festivalContent: UiState<FestivalContentData>,
    moveToBackScreen: () -> Unit,
    isContent: Boolean
) {
    CustomSurface {
        when (festivalContent) {
            is UiState.Loading -> {}
            is UiState.Success -> {
                CustomTopBarWithShadow(
                    title = "축제",
                    onBackButtonClicked = moveToBackScreen
                )

                Column(
                    modifier = Modifier.verticalScroll(rememberScrollState())
                ) {
                    DetailScreenTopBannerImage(imageUri = festivalContent.data.originUrl)

                    DetailScreenDescription(
                        isLiked = festivalContent.data.favorite,
                        tag = festivalContent.data.addressTag,
                        title = festivalContent.data.title,
                        content = festivalContent.data.content,
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
private fun FestivalContentScreenPreview() {

}