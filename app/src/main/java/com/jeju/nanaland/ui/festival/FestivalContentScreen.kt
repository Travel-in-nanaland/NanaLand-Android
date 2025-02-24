package com.jeju.nanaland.ui.festival

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jeju.nanaland.R
import com.jeju.nanaland.domain.entity.festival.FestivalContentData
import com.jeju.nanaland.domain.navigation.ROUTE
import com.jeju.nanaland.globalvalue.userdata.UserData
import com.jeju.nanaland.ui.component.common.CustomSurface
import com.jeju.nanaland.ui.component.common.dialog.DialogCommon
import com.jeju.nanaland.ui.component.common.dialog.DialogCommonType
import com.jeju.nanaland.ui.component.common.icon.GoToUpInList
import com.jeju.nanaland.ui.component.common.topbar.TopBarCommon
import com.jeju.nanaland.ui.component.detailscreen.other.DetailScreenDescription
import com.jeju.nanaland.ui.component.detailscreen.other.DetailScreenInformation
import com.jeju.nanaland.ui.component.detailscreen.other.DetailScreenInformationModificationProposalButton
import com.jeju.nanaland.ui.theme.body02
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.intent.goToShare
import com.jeju.nanaland.util.resource.getString
import com.jeju.nanaland.util.ui.ScreenPreview
import com.jeju.nanaland.util.ui.UiState
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun FestivalContentScreen(
    contentId: Int?,
    isSearch: Boolean,
    updatePrevScreenListFavorite: (Int, Boolean) -> Unit,
    moveToBackScreen: () -> Unit,
    moveToInfoModificationProposalScreen: () -> Unit,
    moveToSignInScreen: () -> Unit,
    moveToMap: (ROUTE.Content.Map)-> Unit,
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
        moveToMap = moveToMap,
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
    moveToMap: (ROUTE.Content.Map)-> Unit,
    isContent: Boolean
) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()
    val isNonMemberGuideDialogShowing = remember { mutableStateOf(false) }
    if (isNonMemberGuideDialogShowing.value) {
        DialogCommon(
            DialogCommonType.Login,
            onDismiss = { isNonMemberGuideDialogShowing.value = false },
            onYes = moveToSignInScreen,
        )
    }

    CustomSurface {
        TopBarCommon(
            title = getString(R.string.common_축제),
            onBackButtonClicked = moveToBackScreen,
            menus = arrayOf(
                (if ((festivalContent as? UiState.Success)?.data?.favorite == true) R.drawable.ic_heart_filled
                else R.drawable.ic_heart_outlined_thick) to {
                    if (UserData.provider == "GUEST") {
                        isNonMemberGuideDialogShowing.value = true
                    } else if(festivalContent is UiState.Success){
                        toggleFavorite(festivalContent.data.id, updatePrevScreenListFavorite)
                    }
                },
                R.drawable.ic_share_outlined to { goToShare(context, "festival",contentId) }
            )
        )
        when (festivalContent) {
            is UiState.Loading -> {}
            is UiState.Success -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    Column(modifier = Modifier.verticalScroll(scrollState)) {
                        Box {
                            GlideImage(
                                modifier = Modifier.fillMaxWidth().height(240.dp),
                                imageModel = { festivalContent.data.images[0].originUrl },
                            )
                            if(!festivalContent.data.isNotOver)
                                Text(
                                    modifier = Modifier
                                        .background(getColor().black50)
                                        .fillMaxWidth()
                                        .padding(vertical = 10.dp),
                                    text = getString(R.string.festival_list_screen_close_detail),
                                    style = body02,
                                    color = getColor().white,
                                    textAlign = TextAlign.Center
                                )
                        }

                        Spacer(Modifier.height(24.dp))

                        Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
                            DetailScreenDescription(
                                isFavorite = festivalContent.data.favorite,
                                tag = festivalContent.data.addressTag,
                                title = festivalContent.data.title,
                                content = festivalContent.data.content,
                                onFavoriteButtonClicked = { toggleFavorite(festivalContent.data.id, updatePrevScreenListFavorite) },
                                onShareButtonClicked = { goToShare(context, "festival",contentId) },
                                moveToSignInScreen = moveToSignInScreen,
                            )

                            Spacer(Modifier.height(32.dp))

                            if (festivalContent.data.address.isNotEmpty()) {
                                DetailScreenInformation(
                                    drawableId = R.drawable.ic_location_outlined,
                                    title = getString(R.string.detail_screen_common_주소),
                                    content = festivalContent.data.address,
                                    moveToMap = { moveToMap(ROUTE.Content.Map(
                                        name = festivalContent.data.title,
                                        localLocate = festivalContent.data.address,
                                        koreaLocate = festivalContent.data.address,
                                        lat = 33.359451, // TODO
                                        lng = 126.545839,
                                    )) }
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

                    GoToUpInList(scrollState, Modifier.align(Alignment.BottomEnd))
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