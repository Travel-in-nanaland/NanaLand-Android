package com.jeju.nanaland.ui.experience

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jeju.nanaland.R
import com.jeju.nanaland.domain.entity.experience.ExperienceContent
import com.jeju.nanaland.ui.component.common.CustomSurface
import com.jeju.nanaland.ui.component.common.CustomTopBar
import com.jeju.nanaland.ui.component.detailscreen.other.DetailScreenDescription
import com.jeju.nanaland.ui.component.detailscreen.other.DetailScreenInformation
import com.jeju.nanaland.ui.component.detailscreen.other.DetailScreenInformationModificationProposalButton
import com.jeju.nanaland.ui.component.detailscreen.other.DetailScreenTopBannerImage
import com.jeju.nanaland.ui.component.detailscreen.other.MoveToTopButton
import com.jeju.nanaland.util.ui.ScreenPreview
import com.jeju.nanaland.util.ui.UiState
import kotlinx.coroutines.launch

@Composable
fun ExperienceContentScreen(
    contentId: Int?,
    isSearch: Boolean,
    updatePrevScreenListFavorite: (Int, Boolean) -> Unit,
    moveToBackScreen: () -> Unit,
    moveToInfoModificationProposalScreen: () -> Unit,
    moveToSignInScreen: () -> Unit,
    viewModel: ExperienceContentViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.getExperienceContent(contentId, isSearch)
    }
    val experienceContent = viewModel.experienceContent.collectAsState().value
    ExperienceContentScreen(
        contentId = contentId,
        experienceContent = experienceContent,
        toggleFavorite = viewModel::toggleFavorite,
        updatePrevScreenListFavorite = updatePrevScreenListFavorite,
        moveToBackScreen = moveToBackScreen,
        moveToInfoModificationProposalScreen = moveToInfoModificationProposalScreen,
        moveToSignInScreen = moveToSignInScreen,
        isContent = true
    )
}

@Composable
private fun ExperienceContentScreen(
    contentId: Int?,
    experienceContent: UiState<ExperienceContent>,
    toggleFavorite: (Int, (Int, Boolean) -> Unit) -> Unit,
    updatePrevScreenListFavorite: (Int, Boolean) -> Unit,
    moveToBackScreen: () -> Unit,
    moveToInfoModificationProposalScreen: () -> Unit,
    moveToSignInScreen: () -> Unit,
    isContent: Boolean
) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()
    CustomSurface {
        CustomTopBar(
            title = "액티비티",
            onBackButtonClicked = moveToBackScreen
        )
        when (experienceContent) {
            is UiState.Loading -> {}
            is UiState.Success -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    Column(modifier = Modifier.verticalScroll(scrollState)) {
                        DetailScreenTopBannerImage(imageUri = experienceContent.data.images[0].thumbnailUrl)

                        Spacer(Modifier.height(24.dp))

                        Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
                            DetailScreenDescription(
                                isFavorite = experienceContent.data.favorite,
                                tag = experienceContent.data.addressTag,
                                title = experienceContent.data.title,
                                content = experienceContent.data.content,
                                onFavoriteButtonClicked = { toggleFavorite(experienceContent.data.id, updatePrevScreenListFavorite) },
                                onShareButtonClicked = {},
                                moveToSignInScreen = moveToSignInScreen
                            )

                            Spacer(Modifier.height(32.dp))

                            if (experienceContent.data.address.isNotEmpty()) {
                                DetailScreenInformation(
                                    drawableId = R.drawable.ic_location_outlined,
                                    title = "주소",
                                    content = experienceContent.data.address
                                )

                                Spacer(Modifier.height(24.dp))
                            }

                            if (experienceContent.data.contact.isNotEmpty()) {
                                DetailScreenInformation(
                                    drawableId = R.drawable.ic_phone_outlined,
                                    title = "연락처",
                                    content = experienceContent.data.contact
                                )

                                Spacer(Modifier.height(24.dp))
                            }

                            if (experienceContent.data.time.isNotEmpty()) {
                                DetailScreenInformation(
                                    drawableId = R.drawable.ic_clock_outlined,
                                    title = "이용 시간",
                                    content = experienceContent.data.time
                                )

                                Spacer(Modifier.height(24.dp))
                            }

                            if (experienceContent.data.amenity.isNotEmpty()) {
                                DetailScreenInformation(
                                    drawableId = R.drawable.ic_ticket_outlined,
                                    title = "입장료",
                                    content = experienceContent.data.amenity
                                )

                                Spacer(Modifier.height(24.dp))
                            }
                        }

                        Spacer(Modifier.height(16.dp))

                        DetailScreenInformationModificationProposalButton {
                            moveToInfoModificationProposalScreen()
                        }

                        Spacer(Modifier.height(80.dp))
                    }

                    MoveToTopButton {
                        coroutineScope.launch { scrollState.animateScrollTo(0) }
                    }
                }
            }
            is UiState.Failure -> {}
        }
    }
}

@ScreenPreview
@Composable
private fun ExperienceContentScreenPreview() {

}