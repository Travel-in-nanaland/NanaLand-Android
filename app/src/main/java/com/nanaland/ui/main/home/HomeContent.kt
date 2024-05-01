package com.nanaland.ui.main.home

import androidx.annotation.DrawableRes
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nanaland.R
import com.nanaland.domain.entity.nanapick.NanaPickThumbnail
import com.nanaland.ui.component.HomeContentCard
import com.nanaland.ui.nanapick.NanaPickThumbnail
import com.nanaland.ui.theme.getColor
import com.nanaland.ui.theme.caption01SemiBold
import com.nanaland.ui.theme.caption02
import com.nanaland.ui.theme.title02Bold
import com.nanaland.util.ui.UiState
import com.nanaland.util.ui.clickableNoEffect
import kotlinx.coroutines.delay

@Composable
fun HomeContent(
    scaffoldPadding: PaddingValues,
    moveToNanaPickListScreen: () -> Unit,
    moveToNatureListScreen: () -> Unit,
    moveToFestivalListScreen: () -> Unit,
    moveToMarketListScreen: () -> Unit,
    moveToExperienceScreen: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val homePreviewBanner = viewModel.homeBannerPreview.collectAsState().value
    LaunchedEffect(Unit) {
        viewModel.getHomeBannerPreview()
    }
    HomeContent(
        scaffoldPadding = scaffoldPadding,
        homePreviewBanner = homePreviewBanner,
        moveToNanaPickListScreen = moveToNanaPickListScreen,
        moveToNatureListScreen = moveToNatureListScreen,
        moveToFestivalListScreen = moveToFestivalListScreen,
        moveToMarketListScreen = moveToMarketListScreen,
        moveToExperienceScreen = moveToExperienceScreen,
        isContent = true
    )
}

@Composable
private fun HomeContent(
    scaffoldPadding: PaddingValues,
    homePreviewBanner: UiState<List<NanaPickThumbnail>>,
    moveToNanaPickListScreen: () -> Unit,
    moveToNatureListScreen: () -> Unit,
    moveToFestivalListScreen: () -> Unit,
    moveToMarketListScreen: () -> Unit,
    moveToExperienceScreen: () -> Unit,
    isContent: Boolean
) {
    LazyColumn(
        modifier = Modifier.imePadding()
    ) {
        item {TopBanner(
            homePreviewBannerUiState = homePreviewBanner,
            onBannerClick = moveToNanaPickListScreen
        )
            Spacer(Modifier.height(20.dp))
            Column(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 20.dp)
            ) {
                CategoryButtons(
                    moveToNatureListScreen = moveToNatureListScreen,
                    moveToFestivalListScreen = moveToFestivalListScreen,
                    moveToMarketListScreen = moveToMarketListScreen,
                    moveToExperienceScreen = moveToExperienceScreen,
                    moveToNanaPickListScreen = moveToNanaPickListScreen,
                )

                Spacer(Modifier.height(20.dp))

                AdBanner()

                Spacer(Modifier.height(30.dp))

                Text(
                    text = "감자마케터 님을 위한 도민 추천 \uD83C\uDF4A",
                    color = Color(0xFF1A1A1A),
                    style = title02Bold
                )

                Spacer(Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    HomeContentCard()

                    Spacer(Modifier.width(8.dp))

                    HomeContentCard()
                }
            }
            Spacer(Modifier.height(scaffoldPadding.calculateBottomPadding()))
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun TopBanner(
    homePreviewBannerUiState: UiState<List<NanaPickThumbnail>>,
    onBannerClick: () -> Unit,
) {
    Box {
        val pagerState = rememberPagerState(
            initialPage = 100,
            pageCount = {1000}
        )
        val isDragged by pagerState.interactionSource.collectIsDraggedAsState()
        if (!isDragged) {
            LaunchedEffect(Unit) {
                while (true) {
                    delay(3500)
                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                }
            }
        }
        when (homePreviewBannerUiState) {
            is UiState.Empty -> {}
            is UiState.Loading -> {}
            is UiState.Success -> {
                HorizontalPager(state = pagerState) { page ->
                    NanaPickThumbnail(
                        item = homePreviewBannerUiState.data[page % (homePreviewBannerUiState.data.size)],
                        onClick = { _ ->
                            onBannerClick()
                        }
                    )
                }
                BannerPageIndicator(
                    itemCnt = homePreviewBannerUiState.data.size,
                    pageNum = pagerState.currentPage
                )
            }
            is UiState.Failure -> {}
        }
    }
}

@Composable
private fun BoxScope.BannerPageIndicator(
    itemCnt: Int,
    pageNum: Int
) {
    Box(
        modifier = Modifier
            .align(Alignment.BottomEnd)
            .padding(end = 20.dp, bottom = 20.dp)
            .width(41.dp)
            .height(20.dp)
            .background(
                color = Color(0x7F151515),
                shape = RoundedCornerShape(50)
            )
            .border(
                border = BorderStroke(
                    width = 1.dp,
                    color = Color(0x7FFFFFFF)
                ),
                shape = RoundedCornerShape(50)
            ),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.width(30.dp)
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = "${(pageNum % itemCnt) + 1}",
                color = Color(0xFFFFFFFF),
                textAlign = TextAlign.Center,
                style = caption02
            )
            Text(
                modifier = Modifier.weight(1f),
                text = "/",
                color = Color(0xFFFFFFFF),
                textAlign = TextAlign.Center,
                style = caption02
            )
            Text(
                modifier = Modifier.weight(1f),
                text = "$itemCnt",
                color = Color(0xFFFFFFFF),
                textAlign = TextAlign.Center,
                style = caption02
            )
        }
    }
}

@Composable
private fun CategoryButtons(
    moveToNatureListScreen: () -> Unit,
    moveToFestivalListScreen: () -> Unit,
    moveToMarketListScreen: () -> Unit,
    moveToExperienceScreen: () -> Unit,
    moveToNanaPickListScreen: () -> Unit,
) {
    Row() {
        CategoryButton(
            resId = R.drawable.img_nature,
            text = "7대 자연",
            onClick = moveToNatureListScreen
        )
        Spacer(Modifier.weight(1f))
        CategoryButton(
            resId = R.drawable.img_festival,
            text = "축제",
            onClick = moveToFestivalListScreen
        )
        Spacer(Modifier.weight(1f))
        CategoryButton(
            resId = R.drawable.img_market,
            text = "전통시장",
            onClick = moveToMarketListScreen
        )
        Spacer(Modifier.weight(1f))
        CategoryButton(
            resId = R.drawable.img_activity,
            text = "이색 체험",
            onClick = moveToExperienceScreen
        )
        Spacer(Modifier.weight(1f))
        CategoryButton(
            resId = R.drawable.img_nanapick,
            text = "나나's Pick",
            onClick = moveToNanaPickListScreen
        )
    }
}

@Composable
private fun CategoryButton(
    @DrawableRes resId: Int,
    text: String,
    onClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .width(60.dp)
            .height(80.dp)
            .clip(RoundedCornerShape(8.dp))
            .clickableNoEffect { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier
                .width(56.dp)
                .height(48.dp),
            painter = painterResource(id = resId),
            contentDescription = null
        )
        Text(
            text = text,
            color = Color(0xFF1A1A1A),
            style = caption01SemiBold
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun AdBanner() {
    val pagerState = rememberPagerState(
        initialPage = 100,
        pageCount = {1000}
    )
    val isDragged by pagerState.interactionSource.collectIsDraggedAsState()
    if (!isDragged) {
        LaunchedEffect(Unit) {
            while (true) {
                delay(3500)
                pagerState.animateScrollToPage(pagerState.currentPage + 1)
            }
        }
    }
    HorizontalPager(state = pagerState) { page ->
        AdContent(page)
    }
    Spacer(Modifier.height(10.dp))
    AdPageIndicator(pageNum = pagerState.currentPage)
}

@Composable
private fun AdContent(
    idx: Int
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(
                color = getColor().skyblue,
                shape = RoundedCornerShape(8.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "${idx} Ad"
        )
    }
}

@Composable
private fun AdPageIndicator(
    pageNum: Int
) {
    val transition = updateTransition(pageNum % 3, "")
    val dot1Color: Color by transition.animateColor(label = "") {
        when (it) {
            1 -> getColor().black
            else -> getColor().gray02
        }
    }
    val dot2Color: Color by transition.animateColor(label = "") {
        when (it) {
            2 -> getColor().black
            else -> getColor().gray02
        }
    }
    val dot3Color: Color by transition.animateColor(label = "") {
        when (it) {
            0 -> getColor().black
            else -> getColor().gray02
        }
    }
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(8.dp)
                .background(
                    color = dot1Color,
                    shape = CircleShape
                )
        )
        Spacer(Modifier.width(10.dp))
        Box(
            modifier = Modifier
                .size(8.dp)
                .background(
                    color = dot2Color,
                    shape = CircleShape
                )
        )
        Spacer(Modifier.width(10.dp))
        Box(
            modifier = Modifier
                .size(8.dp)
                .background(
                    color = dot3Color,
                    shape = CircleShape
                )
        )
    }
}

@Preview
@Composable
private fun HomeContentPreview() {
}