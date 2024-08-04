package com.jeju.nanaland.ui.festival

import android.content.Intent
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
import com.jeju.nanaland.domain.entity.festival.FestivalContentData
import com.jeju.nanaland.ui.component.common.CustomSurface
import com.jeju.nanaland.ui.component.common.topbar.CustomTopBar
import com.jeju.nanaland.ui.component.detailscreen.other.DetailScreenDescription
import com.jeju.nanaland.ui.component.detailscreen.other.DetailScreenInformation
import com.jeju.nanaland.ui.component.detailscreen.other.DetailScreenInformationModificationProposalButton
import com.jeju.nanaland.ui.component.detailscreen.other.DetailScreenTopBannerImage
import com.jeju.nanaland.ui.component.detailscreen.other.MoveToTopButton
import com.jeju.nanaland.util.language.getLanguage
import com.jeju.nanaland.util.resource.getString
import com.jeju.nanaland.util.ui.ScreenPreview
import com.jeju.nanaland.util.ui.UiState
import kotlinx.coroutines.launch

@Composable
fun FestivalContentScreen(
    contentId: Int?,
    isSearch: Boolean,
    updatePrevScreenListFavorite: (Int, Boolean) -> Unit,
    moveToBackScreen: () -> Unit,
    moveToInfoModificationProposalScreen: () -> Unit,
    moveToSignInScreen: () -> Unit,
    viewModel: FestivalContentViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.getFestivalContent(contentId, isSearch)
    }
    val festivalContent = viewModel.festivalContent.collectAsState().value
    FestivalContentScreen(
        contentId = contentId,
        festivalContent = festivalContent,
        toggleFavorite = viewModel::toggleFavorite,
        updatePrevScreenListFavorite = updatePrevScreenListFavorite,
        moveToBackScreen = moveToBackScreen,
        moveToInfoModificationProposalScreen = moveToInfoModificationProposalScreen,
        moveToSignInScreen = moveToSignInScreen,
        isContent = true
    )
}

@Composable
private fun FestivalContentScreen(
    contentId: Int?,
    festivalContent: UiState<FestivalContentData>,
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
            title = getString(R.string.common_축제),
            onBackButtonClicked = moveToBackScreen
        )
        when (festivalContent) {
            is UiState.Loading -> {}
            is UiState.Success -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    Column(modifier = Modifier.verticalScroll(scrollState)) {
                        DetailScreenTopBannerImage(imageUri = festivalContent.data.images[0].thumbnailUrl)

                        Spacer(Modifier.height(24.dp))

                        Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
                            DetailScreenDescription(
                                isFavorite = festivalContent.data.favorite,
                                tag = festivalContent.data.addressTag,
                                title = festivalContent.data.title,
                                content = festivalContent.data.content,
                                onFavoriteButtonClicked = { toggleFavorite(festivalContent.data.id, updatePrevScreenListFavorite) },
                                onShareButtonClicked = {
                                    val sendIntent: Intent = Intent().apply {
                                        action = Intent.ACTION_SEND
                                        putExtra(Intent.EXTRA_TEXT, "http://13.125.110.80:8080/share/${getLanguage()}?category=festival&id=${contentId}")
                                        type = "text/plain"
                                    }
                                    val shareIntent = Intent.createChooser(sendIntent, null)
                                    context.startActivity(shareIntent)
                                },
                                moveToSignInScreen = moveToSignInScreen,
                            )

                            Spacer(Modifier.height(32.dp))

                            if (festivalContent.data.address.isNotEmpty()) {
                                DetailScreenInformation(
                                    drawableId = R.drawable.ic_location_outlined,
                                    title = getString(R.string.detail_screen_common_주소),
                                    content = festivalContent.data.address
                                )

                                Spacer(Modifier.height(24.dp))
                            }


                            if (festivalContent.data.contact.isNotEmpty()) {
                                DetailScreenInformation(
                                    drawableId = R.drawable.ic_phone_outlined,
                                    title = getString(R.string.detail_screen_common_연락처),
                                    content = festivalContent.data.contact
                                )

                                Spacer(Modifier.height(24.dp))
                            }

                            if (festivalContent.data.period.isNotEmpty()) {
                                DetailScreenInformation(
                                    drawableId = R.drawable.ic_calendar_outlined,
                                    title = getString(R.string.detail_screen_common_기간),
                                    content = festivalContent.data.period
                                )

                                Spacer(Modifier.height(24.dp))
                            }

                            if (festivalContent.data.time.isNotEmpty()) {
                                DetailScreenInformation(
                                    drawableId = R.drawable.ic_clock_outlined,
                                    title = getString(R.string.detail_screen_common_이용_시간),
                                    content = festivalContent.data.time
                                )

                                Spacer(Modifier.height(24.dp))
                            }

                            if (festivalContent.data.fee.isNotEmpty()) {
                                DetailScreenInformation(
                                    drawableId = R.drawable.ic_ticket_outlined,
                                    title = getString(R.string.detail_screen_common_입장료),
                                    content = festivalContent.data.fee
                                )

                                Spacer(Modifier.height(24.dp))
                            }

                            if (festivalContent.data.homepage.isNotEmpty()) {
                                DetailScreenInformation(
                                    drawableId = R.drawable.ic_clip_outlined,
                                    title = getString(R.string.detail_screen_common_홈페이지),
                                    content = festivalContent.data.homepage
                                )

                                Spacer(Modifier.height(24.dp))
                            }

                            Spacer(Modifier.height(16.dp))

                            DetailScreenInformationModificationProposalButton {
                                moveToInfoModificationProposalScreen()
                            }
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
private fun FestivalContentScreenPreview() {

}