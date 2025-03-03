package com.jeju.nanaland.ui.nature

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jeju.nanaland.R
import com.jeju.nanaland.domain.entity.nature.NatureContent
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
import com.jeju.nanaland.ui.component.detailscreen.other.DetailScreenNotice
import com.jeju.nanaland.ui.component.detailscreen.other.DetailScreenTopBannerImage
import com.jeju.nanaland.util.intent.goToShare
import com.jeju.nanaland.util.resource.getString
import com.jeju.nanaland.util.ui.ScreenPreview
import com.jeju.nanaland.util.ui.UiState

@Composable
fun NatureContentScreen(
    contentId: Int?,
    isSearch: Boolean,
    updatePrevScreenListFavorite: (Int, Boolean) -> Unit,
    moveToBackScreen: () -> Unit,
    moveToInfoModificationProposalScreen: () -> Unit,
    moveToSignInScreen: () -> Unit,
    moveToMap: (ROUTE.Content.Map)-> Unit,
    viewModel: NatureContentViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.getNatureContent(contentId, isSearch)
    }
    val natureContent = viewModel.natureContent.collectAsState().value
    NatureContentScreen(
        contentId = contentId,
        natureContent = natureContent,
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
private fun NatureContentScreen(
    contentId: Int?,
    natureContent: UiState<NatureContent>,
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
            title = getString(R.string.common_자연),
            onBackButtonClicked = moveToBackScreen,
            menus = arrayOf(
                (if ((natureContent as? UiState.Success)?.data?.favorite == true) R.drawable.ic_heart_filled
                else R.drawable.ic_heart_outlined_thick) to {
                    if (UserData.provider == "GUEST") {
                        isNonMemberGuideDialogShowing.value = true
                    } else if(natureContent is UiState.Success){
                        toggleFavorite(natureContent.data.id, updatePrevScreenListFavorite)
                    }
                },
                R.drawable.ic_share_outlined to { goToShare(context, "nature",contentId) }
            )
        )

        when (natureContent) {
            is UiState.Loading -> {}
            is UiState.Success -> {
                Box(modifier = Modifier.fillMaxSize()) {
                        Column(modifier = Modifier.verticalScroll(scrollState)) {
                            DetailScreenTopBannerImage(imageUri = natureContent.data.images[0]?.originUrl)

                            Spacer(Modifier.height(24.dp))

                            Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
                                DetailScreenDescription(
                                    isFavorite = natureContent.data.favorite,
                                    tag = natureContent.data.addressTag,
                                    title = natureContent.data.title,
                                    content = natureContent.data.content,
                                    onFavoriteButtonClicked = { toggleFavorite(natureContent.data.id, updatePrevScreenListFavorite) },
                                    onShareButtonClicked = { goToShare(context, "nature",contentId) },
                                    moveToSignInScreen = moveToSignInScreen,
                                )

                                Spacer(Modifier.height(24.dp))

                                DetailScreenNotice(
                                    title = getString(R.string.detail_screen_common_소개합니다),
                                    content = natureContent.data.intro
                                )

                                Spacer(Modifier.height(32.dp))

                                if (!natureContent.data.address.isNullOrEmpty()) {
                                    DetailScreenInformation(
                                        drawableId = R.drawable.ic_location_outlined,
                                        title = getString(R.string.detail_screen_common_주소),
                                        content = natureContent.data.address,
                                        moveToMap = { moveToMap(ROUTE.Content.Map(
                                            name = natureContent.data.title,
                                            localLocate = natureContent.data.address,
                                            id = natureContent.data.id,
                                            category = "NATURE"
                                        )) }
                                    )

                                    Spacer(Modifier.height(24.dp))
                                }

                                if (!natureContent.data.contact.isNullOrEmpty()) {
                                    DetailScreenInformation(
                                        drawableId = R.drawable.ic_phone_outlined,
                                        title = getString(R.string.detail_screen_common_연락처),
                                        content = natureContent.data.contact
                                    )

                                    Spacer(Modifier.height(24.dp))
                                }

                                if (!natureContent.data.time.isNullOrEmpty()) {
                                    DetailScreenInformation(
                                        drawableId = R.drawable.ic_clock_outlined,
                                        title = getString(R.string.detail_screen_common_이용_시간),
                                        content = natureContent.data.time
                                    )

                                    Spacer(Modifier.height(24.dp))
                                }

                                if (!natureContent.data.fee.isNullOrEmpty()) {
                                    DetailScreenInformation(
                                        drawableId = R.drawable.ic_ticket_outlined,
                                        title = getString(R.string.detail_screen_common_입장료),
                                        content = natureContent.data.fee
                                    )

                                    Spacer(Modifier.height(24.dp))
                                }

                                if (!natureContent.data.details.isNullOrEmpty()) {
                                    DetailScreenInformation(
                                        drawableId = R.drawable.ic_note_outlined,
                                        title = getString(R.string.detail_screen_common_상세_정보),
                                        content = natureContent.data.details
                                    )

                                    Spacer(Modifier.height(24.dp))
                                }

                                if (!natureContent.data.amenity.isNullOrEmpty()) {
                                    DetailScreenInformation(
                                        drawableId = R.drawable.ic_amenity_outlined,
                                        title = getString(R.string.detail_screen_common_편의시설),
                                        content = natureContent.data.amenity
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
private fun NatureContentScreenPreview() {

}