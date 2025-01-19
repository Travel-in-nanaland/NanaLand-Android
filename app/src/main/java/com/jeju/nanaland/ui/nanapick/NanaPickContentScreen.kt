package com.jeju.nanaland.ui.nanapick

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jeju.nanaland.R
import com.jeju.nanaland.domain.entity.nanapick.NanaPickContentData
import com.jeju.nanaland.domain.navigation.ROUTE
import com.jeju.nanaland.ui.component.common.CustomSurface
import com.jeju.nanaland.ui.component.detailscreen.nanapick.NanaPickContentAttractivePointDialog
import com.jeju.nanaland.ui.component.detailscreen.nanapick.NanaPickContentSubContents
import com.jeju.nanaland.ui.component.detailscreen.nanapick.NanaPickContentTopBanner
import com.jeju.nanaland.ui.component.detailscreen.other.MoveToTopButton
import com.jeju.nanaland.ui.component.detailscreen.other.parts.notice.DetailScreenNoticeContent
import com.jeju.nanaland.ui.component.detailscreen.other.parts.notice.DetailScreenNoticeTitle
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.log.LogUtil
import com.jeju.nanaland.util.resource.getString
import com.jeju.nanaland.util.ui.UiState
import com.jeju.nanaland.util.ui.drawColoredShadow
import kotlinx.coroutines.launch
import kotlin.math.max

@Composable
fun NanaPickContentScreen(
    contentId: Int?,
    moveToBackScreen: () -> Unit,
    moveToSignInScreen: () -> Unit,
    moveToMap: (ROUTE.Content.Map)-> Unit,
    viewModel: NanaPickContentViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.getNanaPickContent(contentId)
    }
    val nanaPickContent = viewModel.nanaPickContent.collectAsState().value
    NanaPickContentScreen(
        contentId = contentId,
        nanaPickContent = nanaPickContent,
        moveToBackScreen = moveToBackScreen,
        toggleFavorite = { viewModel.toggleFavorite(contentId) },
        moveToSignInScreen = moveToSignInScreen,
        moveToMap = moveToMap,
        isContent = true
    )
}

@Composable
private fun NanaPickContentScreen(
    contentId: Int?,
    nanaPickContent: UiState<NanaPickContentData>,
    toggleFavorite: () -> Unit,
    moveToBackScreen: () -> Unit,
    moveToSignInScreen: () -> Unit,
    moveToMap: (ROUTE.Content.Map)-> Unit,
    isContent: Boolean
) {
    val density = LocalDensity.current.density
    val scrollState = rememberScrollState()
    LogUtil.e("", "${density} ${scrollState.value} ${scrollState.value / 2.0}, ${400 - (scrollState.value / 2.0)}")
    val coroutineScope = rememberCoroutineScope()
    var attractiveDialogText by remember { mutableStateOf("") }

    if(attractiveDialogText.isNotBlank())
        NanaPickContentAttractivePointDialog(attractiveDialogText) {
            attractiveDialogText = ""
        }

    CustomSurface {

        when (nanaPickContent) {
            is UiState.Loading -> {}
            is UiState.Success -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    Column(modifier = Modifier
                        .verticalScroll(scrollState)
                        .padding(top = 400.dp)
                    ) {

                        Spacer(Modifier.height(16.dp))

                        Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
                            if (!nanaPickContent.data.notice.isNullOrEmpty()) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .drawColoredShadow(
                                            color = getColor().black,
                                            alpha = 0.1f,
                                            shadowRadius = 12.dp,
                                            offsetX = 0.dp,
                                            offsetY = 0.dp
                                        )
                                        .background(
                                            color = getColor().white,
                                            shape = RoundedCornerShape(12.dp)
                                        )
                                        .padding(16.dp)
                                ) {
                                    DetailScreenNoticeTitle(
                                        getString(R.string.detail_screen_common_알아두면_좋아요)
                                    )

                                    Spacer(Modifier.height(4.dp))

                                    DetailScreenNoticeContent(nanaPickContent.data.notice)
                                }
                            }

                            Spacer(Modifier.height(48.dp))

                            NanaPickContentSubContents(nanaPickContent = nanaPickContent.data, moveToMap = moveToMap) {
                                attractiveDialogText = it
                            }
                        }

                        Spacer(Modifier.height(80.dp))
                    }

                    NanaPickContentTopBanner(
                        height = max(400 - (scrollState.value / density).toInt(), 185),
                        isEllipsis = scrollState.value / density > (400 - 185) / 2,
                        contentId = contentId,
                        nanaPickContent = nanaPickContent,
                        onBackButtonClicked = moveToBackScreen,
                        toggleFavorite = toggleFavorite,
                        moveToSignInScreen = moveToSignInScreen,
                    )

                    MoveToTopButton {
                        coroutineScope.launch { scrollState.animateScrollTo(0) }
                    }
                }
            }
            is UiState.Failure -> {}
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun NanaPickContentPreview() {

}