package com.nanaland.ui.nanapick

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nanaland.R
import com.nanaland.domain.entity.nanapick.NanaPickContentData
import com.nanaland.ui.component.common.CustomSurface
import com.nanaland.ui.component.common.CustomTopBar
import com.nanaland.ui.component.detailscreen.MoveToTopButton
import com.nanaland.ui.theme.getColor
import com.nanaland.ui.theme.body01
import com.nanaland.ui.theme.body02
import com.nanaland.ui.theme.body02Bold
import com.nanaland.ui.theme.caption01
import com.nanaland.ui.theme.title01Bold
import com.nanaland.util.ui.UiState
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
        tmp = true
    )
}

@Composable
private fun NanaPickContentScreen(
    nanaPickContent: UiState<NanaPickContentData>,
    moveToBackScreen: () -> Unit,
    tmp: Boolean
) {
    val coroutineScope = rememberCoroutineScope()
    CustomSurface {
        CustomTopBar(
            title = "나나's Pick",
            onBackButtonClicked = moveToBackScreen
        )
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            val listState = rememberLazyListState()
            LazyColumn(
                state = listState
            ) {
                item {
                    TopBannerContent(
                        nanaPickContentUiState = nanaPickContent
                    )
                    Column(
                        modifier = Modifier
                            .padding(20.dp)
                    ) {
                        when (nanaPickContent) {
                            is UiState.Loading -> { Box(Modifier.fillMaxSize()) }
                            is UiState.Loading -> { Box(Modifier.fillMaxSize()) }
                            is UiState.Success -> {
                                TipBox(
                                    notice = nanaPickContent.data.notice ?: ""
                                )
                                Spacer(Modifier.height(40.dp))
                                DetailContent(
                                    nanaPickContent = nanaPickContent.data
                                )
                            }
                            is UiState.Failure -> { Box(Modifier.fillMaxSize()) }
                        }
                    }
                }
            }
            MoveToTopButton(
                onClick = { coroutineScope.launch { listState.animateScrollToItem(0, 0) } }
            )
        }
    }
}

@Composable
private fun TopBannerContent(
    nanaPickContentUiState: UiState<NanaPickContentData>
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(240.dp)
            .background(Color(0xFF262F60))
    ) {
        when (nanaPickContentUiState) {
            is UiState.Loading -> { Box(Modifier.fillMaxSize()) }
            is UiState.Loading -> { Box(Modifier.fillMaxSize()) }
            is UiState.Success -> {
                GlideImage(
                    modifier = Modifier.fillMaxSize(),
                    imageModel = { nanaPickContentUiState.data.originUrl }
                )
            }
            is UiState.Failure -> { Box(Modifier.fillMaxSize()) }
        }
        Row(
            modifier = Modifier
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
//            Text(
//                text = "vol.0",
//                fontWeight = FontWeight.Medium,
//                color = Color(0xFFFFFFFF)
//            )
            Spacer(Modifier.weight(1f))
            Image(
                painter = painterResource(id = R.drawable.ic_heart_outlined),
                contentDescription = null,
                colorFilter = ColorFilter.tint(Color(0xFFFFFFFF))
            )
            Spacer(Modifier.width(10.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_share_outlined),
                contentDescription = null,
                colorFilter = ColorFilter.tint(Color(0xFFFFFFFF))
            )
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