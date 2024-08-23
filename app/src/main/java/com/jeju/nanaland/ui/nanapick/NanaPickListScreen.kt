package com.jeju.nanaland.ui.nanapick

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jeju.nanaland.R
import com.jeju.nanaland.domain.entity.nanapick.NanaPickBannerData
import com.jeju.nanaland.ui.component.common.topbar.CustomTopBarNoBackButton
import com.jeju.nanaland.ui.component.main.home.HomeScreenAdPageIndicator
import com.jeju.nanaland.ui.nanapick.component.NanaPickThumbnailBanner1
import com.jeju.nanaland.ui.nanapick.component.NewTag
import com.jeju.nanaland.ui.theme.body02SemiBold
import com.jeju.nanaland.ui.theme.caption01
import com.jeju.nanaland.ui.theme.caption01SemiBold
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.ui.theme.title01Bold
import com.jeju.nanaland.ui.theme.title02Bold
import com.jeju.nanaland.util.resource.getString
import com.jeju.nanaland.util.ui.UiState
import com.jeju.nanaland.util.ui.clickableNoEffect
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun NanaPickListScreen(
    moveToNanaPickContentScreen: (Int) -> Unit,
    moveToMainScreen: () -> Unit,
    moveToNanaPickAllListScreen: () -> Unit,
    viewModel: NanaPickListViewModel = hiltViewModel()
) {
    val nanaPickList = viewModel.nanaPickList.collectAsState().value
    val recommendedNanaPickList = viewModel.recommendedNanaPickList.collectAsState().value
    LaunchedEffect(Unit) {
        viewModel.getNanaPickList()
        viewModel.getRecommendedNanaPickList()
    }
    NanaPickListScreen(
        nanaPickList = nanaPickList,
        recommendedNanaPickList = recommendedNanaPickList,
        moveToNanaPickContentScreen = moveToNanaPickContentScreen,
        moveToMainScreen = moveToMainScreen,
        moveToNanaPickAllListScreen = moveToNanaPickAllListScreen,
        isContent = true
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun NanaPickListScreen(
    nanaPickList: UiState<List<NanaPickBannerData>>,
    recommendedNanaPickList: UiState<List<NanaPickBannerData>>,
    moveToMainScreen: () -> Unit,
    moveToNanaPickAllListScreen: () -> Unit,
    moveToNanaPickContentScreen: (Int) -> Unit,
    isContent: Boolean
) {
    val pagerState = rememberPagerState(
        initialPage = 200,
        pageCount = {100000}
    )
    Column {
        CustomTopBarNoBackButton(
            title = getString(R.string.common_나나s_Pick)
        )

        Column(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(Modifier.height(24.dp))

            Text(
                text = "금주 추천 나나's Pick",
                color = getColor().black,
                style = title02Bold
            )

            Spacer(Modifier.height(12.dp))

            when (recommendedNanaPickList) {
                is UiState.Loading -> {}
                is UiState.Success -> {
                    HorizontalPager(
                        state = pagerState,
                        pageSize = PageSize.Fixed(226.dp)
                    ) { page ->
                        Column {
                            Box(
                                modifier = Modifier
                                    .padding(end = 16.dp)
                                    .height(280.dp)
                                    .width(210.dp)
                            ) {
                                GlideImage(
                                    modifier = Modifier.fillMaxWidth(),
                                    imageModel = { recommendedNanaPickList.data[page % recommendedNanaPickList.data.size].firstImage.thumbnailUrl },
                                    imageOptions = ImageOptions(contentScale = ContentScale.FillHeight)
                                )

                                Text(
                                    modifier = Modifier
                                        .align(Alignment.TopEnd)
                                        .padding(top = 8.dp, end = 8.dp),
                                    text = recommendedNanaPickList.data[page % recommendedNanaPickList.data.size].version,
                                    color = getColor().white,
                                    style = caption01SemiBold
                                )

                                Column(
                                    modifier = Modifier
                                        .align(Alignment.BottomStart)
                                        .padding(start = 16.dp, bottom = 16.dp)
                                ) {
                                    Text(
                                        text = recommendedNanaPickList.data[page % recommendedNanaPickList.data.size].subHeading,
                                        color = getColor().white,
                                        style = body02SemiBold
                                    )

                                    Text(
                                        text = recommendedNanaPickList.data[page % recommendedNanaPickList.data.size].heading,
                                        color = getColor().white,
                                        style = title01Bold
                                    )
                                }
                            }

                            Spacer(Modifier.height(8.dp))

                            Row(
                                modifier = Modifier.width(210.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                recommendedNanaPickList.data[page % recommendedNanaPickList.data.size].newest?.let {
                                    if (it) {
                                        NewTag(padding = 0)

                                        Spacer(Modifier.width(4.dp))
                                    }
                                }

                                Text(
                                    text = recommendedNanaPickList.data[page % recommendedNanaPickList.data.size].heading,
                                    color = getColor().black,
                                    style = title02Bold,
                                    overflow = TextOverflow.Ellipsis,
                                    maxLines = 1,
                                    onTextLayout = { textLayoutResult: TextLayoutResult ->

                                    }
                                )
                            }
                        }
                    }
                }
                is UiState.Failure -> {}
            }

            Spacer(Modifier.height(16.dp))

            HomeScreenAdPageIndicator(pageNum = pagerState.currentPage)

            Spacer(Modifier.height(32.dp))

            Row(
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = "나나's Pick",
                    color = getColor().black,
                    style = title02Bold
                )

                Spacer(Modifier.weight(1f))

                Text(
                    modifier = Modifier
                        .clickableNoEffect { moveToNanaPickAllListScreen() },
                    text = "모두보기",
                    color = getColor().gray01,
                    style = caption01
                )
            }

            Spacer(Modifier.height(8.dp))

            when (nanaPickList) {
                is UiState.Loading -> {}
                is UiState.Success -> {
                    nanaPickList.data.forEachIndexed() { idx, item ->
                        NanaPickThumbnailBanner1(
                            item = item,
                            onClick = moveToNanaPickContentScreen
                        )
                        Spacer(Modifier.height(16.dp))
                    }
                }
                is UiState.Failure -> {}
            }
        }
    }
}

@Preview
@Composable
private fun NanaPickListScreenPreview() {

}