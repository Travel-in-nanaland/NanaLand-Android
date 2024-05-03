package com.nanaland.ui.nature

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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nanaland.R
import com.nanaland.domain.entity.nature.NatureContentData
import com.nanaland.ui.component.common.CustomSurface
import com.nanaland.ui.component.common.CustomTopBarWithShadow
import com.nanaland.ui.component.detailscreen.DetailScreenDescription
import com.nanaland.ui.component.detailscreen.DetailScreenInformation
import com.nanaland.ui.component.detailscreen.DetailScreenNotice
import com.nanaland.ui.component.detailscreen.DetailScreenTopBannerImage
import com.nanaland.ui.component.detailscreen.MoveToTopButton
import com.nanaland.util.ui.ScreenPreview
import com.nanaland.util.ui.UiState
import kotlinx.coroutines.launch
import okhttp3.internal.notify

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
        toggleFavorite = viewModel::toggleFavorite,
        moveToBackScreen = moveToBackScreen,
        isContent = true
    )
}

@Composable
private fun NatureContentScreen(
    natureContent: UiState<NatureContentData>,
    toggleFavorite: (Long) -> Unit,
    moveToBackScreen: () -> Unit,
    isContent: Boolean
) {
    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()
    CustomSurface {
        when (natureContent) {
            is UiState.Loading -> {}
            is UiState.Success -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    Column {
                        CustomTopBarWithShadow(
                            title = "7대 자연",
                            onBackButtonClicked = moveToBackScreen
                        )

                        Column(modifier = Modifier.verticalScroll(scrollState)) {
                            DetailScreenTopBannerImage(imageUri = natureContent.data.originUrl)

                            Spacer(Modifier.height(24.dp))

                            Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
                                DetailScreenDescription(
                                    isLiked = natureContent.data.favorite,
                                    tag = natureContent.data.addressTag,
                                    title = natureContent.data.title,
                                    content = natureContent.data.content,
                                    onLikeButtonClicked = { toggleFavorite(natureContent.data.id) },
                                    onShareButtonClicked = {}
                                )

                                Spacer(Modifier.height(24.dp))

                                DetailScreenNotice(
                                    title = "소개합니다!",
                                    content = natureContent.data.intro
                                )

                                Spacer(Modifier.height(16.dp))

                                if (!natureContent.data.address.isNullOrEmpty()) {
                                    DetailScreenInformation(
                                        drawableId = R.drawable.ic_location_outlined,
                                        title = "주소",
                                        content = natureContent.data.address
                                    )

                                    Spacer(Modifier.height(16.dp))
                                }

                                if (!natureContent.data.contact.isNullOrEmpty()) {
                                    DetailScreenInformation(
                                        drawableId = R.drawable.ic_phone_outlined,
                                        title = "연락처",
                                        content = natureContent.data.contact
                                    )

                                    Spacer(Modifier.height(16.dp))
                                }

                                if (!natureContent.data.time.isNullOrEmpty()) {
                                    DetailScreenInformation(
                                        drawableId = R.drawable.ic_clock_outlined,
                                        title = "이용 시간",
                                        content = natureContent.data.time
                                    )

                                    Spacer(Modifier.height(16.dp))
                                }

                                if (!natureContent.data.fee.isNullOrEmpty()) {
                                    DetailScreenInformation(
                                        drawableId = R.drawable.ic_ticket_outlined,
                                        title = "입장료",
                                        content = natureContent.data.fee
                                    )

                                    Spacer(Modifier.height(16.dp))
                                }

                                if (!natureContent.data.details.isNullOrEmpty()) {
                                    DetailScreenInformation(
                                        drawableId = R.drawable.ic_clip_outlined,
                                        title = "상세 정보",
                                        content = natureContent.data.details
                                    )

                                    Spacer(Modifier.height(16.dp))
                                }

                                if (!natureContent.data.amenity.isNullOrEmpty()) {
                                    DetailScreenInformation(
                                        drawableId = R.drawable.ic_amenity_outlined,
                                        title = "편의시설",
                                        content = natureContent.data.amenity
                                    )

                                    Spacer(Modifier.height(16.dp))
                                }
                            }

                            Spacer(Modifier.height(80.dp))
                        }
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
private fun NatureContentScreenPreview() {

}