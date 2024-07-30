package com.jeju.nanaland.ui.nanapick

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jeju.nanaland.R
import com.jeju.nanaland.domain.entity.nanapick.NanaPickContentData
import com.jeju.nanaland.ui.component.common.CustomSurface
import com.jeju.nanaland.ui.component.common.CustomTopBar
import com.jeju.nanaland.ui.component.detailscreen.nanapick.NanaPickContentAttractivePointDialog
import com.jeju.nanaland.ui.component.detailscreen.nanapick.NanaPickContentSubContents
import com.jeju.nanaland.ui.component.detailscreen.nanapick.NanaPickContentTopBanner
import com.jeju.nanaland.ui.component.detailscreen.other.DetailScreenNotice
import com.jeju.nanaland.ui.component.detailscreen.other.MoveToTopButton
import com.jeju.nanaland.util.resource.getString
import com.jeju.nanaland.util.ui.UiState
import kotlinx.coroutines.launch

@Composable
fun NanaPickContentScreen(
    contentId: Int?,
    moveToBackScreen: () -> Unit,
    moveToSignInScreen: () -> Unit,
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
    isContent: Boolean
) {
    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()
    var attractiveDialogText by remember { mutableStateOf("") }

    if(attractiveDialogText.isNotBlank())
        NanaPickContentAttractivePointDialog(attractiveDialogText) {
            attractiveDialogText = ""
        }

    CustomSurface {
        CustomTopBar(
            title = getString(R.string.common_나나s_Pick),
            onBackButtonClicked = moveToBackScreen
        )

        when (nanaPickContent) {
            is UiState.Loading -> {}
            is UiState.Success -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    Column(modifier = Modifier.verticalScroll(scrollState)) {
                        NanaPickContentTopBanner(
                            contentId = contentId,
                            nanaPickContent = nanaPickContent,
                            toggleFavorite = toggleFavorite,
                            moveToSignInScreen = moveToSignInScreen,
                        )

                        Spacer(Modifier.height(16.dp))

                        Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
                            if (!nanaPickContent.data.notice.isNullOrEmpty()) {
                                DetailScreenNotice(
                                    title = getString(R.string.detail_screen_common_알아두면_좋아요),
                                    content = nanaPickContent.data.notice
                                )
                            }

                            Spacer(Modifier.height(48.dp))

                            NanaPickContentSubContents(nanaPickContent = nanaPickContent.data) {
                                attractiveDialogText = it
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

@Preview(showBackground = true)
@Composable
private fun NanaPickContentPreview() {

}