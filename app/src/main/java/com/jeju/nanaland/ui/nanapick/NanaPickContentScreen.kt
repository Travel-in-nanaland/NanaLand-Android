package com.jeju.nanaland.ui.nanapick

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jeju.nanaland.R
import com.jeju.nanaland.domain.entity.nanapick.NanaPickContentData
import com.jeju.nanaland.ui.component.common.CustomSurface
import com.jeju.nanaland.ui.component.common.CustomTopBar
import com.jeju.nanaland.ui.component.detailscreen.nanapick.NanaPickContentSubContents
import com.jeju.nanaland.ui.component.detailscreen.nanapick.NanaPickContentTopBanner
import com.jeju.nanaland.ui.component.detailscreen.other.DetailScreenNotice
import com.jeju.nanaland.ui.component.detailscreen.other.MoveToTopButton
import com.jeju.nanaland.ui.theme.body01
import com.jeju.nanaland.ui.theme.body02
import com.jeju.nanaland.ui.theme.body02Bold
import com.jeju.nanaland.ui.theme.caption01
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.ui.theme.title01Bold
import com.jeju.nanaland.util.ui.UiState
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.launch

@Composable
fun NanaPickContentScreen(
    contentId: Long?,
    moveToBackScreen: () -> Unit,
    viewModel: NanaPickContentViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.getNanaPickContent(contentId)
    }
    val nanaPickContent = viewModel.nanaPickContent.collectAsState().value
    NanaPickContentScreen(
        nanaPickContent = nanaPickContent,
        moveToBackScreen = moveToBackScreen,
        toggleFavorite = { viewModel.toggleFavorite(contentId) },
        tmp = true
    )
}

@Composable
private fun NanaPickContentScreen(
    nanaPickContent: UiState<NanaPickContentData>,
    toggleFavorite: () -> Unit,
    moveToBackScreen: () -> Unit,
    tmp: Boolean
) {
    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()
    CustomSurface {
        CustomTopBar(
            title = "나나's Pick",
            onBackButtonClicked = moveToBackScreen
        )

        when (nanaPickContent) {
            is UiState.Loading -> {}
            is UiState.Success -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    Column(modifier = Modifier.verticalScroll(scrollState)) {
                        NanaPickContentTopBanner(
                            nanaPickContent = nanaPickContent,
                            toggleFavorite = toggleFavorite
                        )

                        Spacer(Modifier.height(16.dp))

                        Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
                            DetailScreenNotice(
                                title = "알아두면 좋아요!",
                                content = nanaPickContent.data.notice
                            )

                            Spacer(Modifier.height(32.dp))

                            NanaPickContentSubContents(nanaPickContent = nanaPickContent.data)
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

@Composable
private fun TipBox(
    notice: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color(0xFFEEECFE),
                shape = RoundedCornerShape(12.dp)
            )
            .padding(10.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_warning_outlined),
                contentDescription = null
            )
            Spacer(Modifier.width(4.dp))
            Text(
                text = "알아두면 좋아요!",
                color = Color(0xFF583FF5),
                style = body02Bold
            )
        }
        Spacer(Modifier.height(6.dp))
        Text(
            text = notice,
            color = getColor().black,
            style = caption01
        )
    }
}

@Composable
private fun DetailContent(
    nanaPickContent: NanaPickContentData
) {
    nanaPickContent.nanaDetails.forEach { subContent ->
        Text(
            modifier = Modifier
                .padding(start = 40.dp),
            text = subContent.subTitle ?: "",
            color = Color(0xFF583FF5),
            style = caption01
        )

        Spacer(Modifier.height(4.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(30.dp)
                    .shadow(
                        elevation = 4.dp,
                        shape = CircleShape,
                        spotColor = Color(0x00000000)
                    )
                    .clip(CircleShape)
                    .background(Color(0xFFFFFFFF)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = subContent.number.toString(),
                    color = Color(0xFF583FF5),
                    style = title01Bold
                )
            }

            Spacer(Modifier.width(10.dp))

            Text(
                text = subContent.title ?: "",
                color = getColor().black,
                style = title01Bold
            )
        }

        Spacer(Modifier.height(14.dp))

        GlideImage(
            modifier = Modifier
                .width(328.dp)
                .height(176.dp),
            imageModel = { subContent.imageUrl }
        )

        Spacer(Modifier.height(10.dp))

        Text(
            text = subContent.content ?: "",
            color = getColor().black,
            style = body01
        )

        Spacer(Modifier.height(10.dp))

        subContent.nanaPickSubContentAdditionalInfoList.forEach { additionalInfo ->
            Text(
                text = "${additionalInfo.infoKey} : ${additionalInfo.infoValue}",
                color = getColor().gray01,
                style = body02
            )
            Spacer(Modifier.height(10.dp))
        }

        Row {
            subContent.hashtags.forEach { tag ->
                TagChip(
                    text = tag ?: ""
                )

                Spacer(Modifier.width(10.dp))
            }
        }

        Spacer(Modifier.height(60.dp))
    }
}

@Composable
private fun TagChip(
    text: String
) {
    Box(
        modifier = Modifier
            .height(32.dp)
            .background(
                color = getColor().main10,
                shape = RoundedCornerShape(50)
            )
            .padding(start = 10.dp, end = 10.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = getColor().main,
            style = body02
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun NanaPickContentPreview() {

}