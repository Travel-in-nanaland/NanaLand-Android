package com.nanaland.ui.main.home.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nanaland.R
import com.nanaland.ui.component.SearchContentCard
import com.nanaland.ui.theme.getColor
import com.nanaland.ui.theme.body02
import com.nanaland.ui.theme.body02SemiBold
import com.nanaland.ui.theme.title02Bold
import com.nanaland.util.ui.UiState
import com.nanaland.util.ui.clickableNoEffect

@Composable
fun SearchingContent(
    scaffoldPadding: PaddingValues,
    viewModel: SearchViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.getTopKeywords()
    }
    val topKeywords = viewModel.topKeywords.collectAsState().value
    SearchingContent(
        scaffoldPadding = scaffoldPadding,
        topKeywords = topKeywords,
        isContent = true
    )
}

@Composable
private fun SearchingContent(
    scaffoldPadding: PaddingValues,
    topKeywords: UiState<List<String>>,
    isContent: Boolean
) {
    LazyColumn(
        modifier = Modifier.imePadding()
    ) {
        item {
            Column(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp)
            ) {
                Row {
                    Text(
                        text = "최근 검색어",
                        color = getColor().black,
                        style = title02Bold
                    )
                    Spacer(Modifier.weight(1f))
                    Text(
                        text = "모두 지우기",
                        color = getColor().gray01
                    )
                }

                Spacer(Modifier.height(10.dp))

                RecentSearchList()

                Spacer(Modifier.height(20.dp))

                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = title02Bold.toSpanStyle().copy(
                                color = getColor().main
                            ) ,
                        ) {
                            append("가장 많이")
                        }
                        withStyle(
                            style = title02Bold.toSpanStyle().copy(
                                color = getColor().black
                            )
                        ) {
                            append(" 검색하고 있어요!")
                        }
                    }
                )

                Spacer(Modifier.height(20.dp))

                TopKeywords(
                    topKeywords = topKeywords
                )

                Spacer(Modifier.height(20.dp))

                Text(
                    text = "검색량 UP! JEJU Place \uD83C\uDF4A",
                    color = getColor().black,
                    style = title02Bold
                )

                Spacer(Modifier.height(20.dp))

                HotSearchedList()
            }
            Spacer(Modifier.height(scaffoldPadding.calculateBottomPadding()))
        }
    }
}

@Composable
private fun RecentSearchList() {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .height(34.dp)
    ) {
        item {
            RecentSearchItem(text = "제주")
        }
    }
}

@Composable
private fun RecentSearchItem(
    text: String,
) {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .background(
                color = getColor().main10,
                shape = RoundedCornerShape(50)
            )
            .padding(start = 20.dp, end = 20.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = text,
                color = getColor().main,
                style = body02
            )
            Spacer(Modifier.width(10.dp))
            Image(
                modifier = Modifier
                    .size(16.dp)
                    .clickableNoEffect {

                    },
                painter = painterResource(id = R.drawable.ic_close),
                contentDescription = null,
                colorFilter = ColorFilter.tint(getColor().main)
            )
        }
    }
}

@Composable
private fun TopKeywords(
    topKeywords: UiState<List<String>>,
) {
    Row {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            repeat(4) {
                Box(
                    modifier = Modifier.height(40.dp)
                ) {
                    when (topKeywords) {
                        is UiState.Empty -> {}
                        is UiState.Loading -> {}
                        is UiState.Success -> {
                            Text(
                                text = "${it + 1}. ${topKeywords.data[it]}",
                                color = if (it == 0 || it == 1) {
                                    getColor().main
                                } else getColor().gray01,
                                style = if (it == 0 || it == 1) {
                                    body02SemiBold
                                } else body02
                            )
                        }
                        is UiState.Failure -> {}
                    }
                }
            }
        }
        Column(
            modifier = Modifier.weight(1f)
        ) {
            repeat(4) {
                Box(
                    modifier = Modifier.height(40.dp)
                ) {
                    when (topKeywords) {
                        is UiState.Empty -> {}
                        is UiState.Loading -> {}
                        is UiState.Success -> {
                            Text(
                                text = "${it + 5}. ${topKeywords.data[it + 4]}",
                                color = getColor().black,
                                style = body02
                            )
                        }
                        is UiState.Failure -> {}
                    }
                }
            }
        }
    }
}

@Composable
private fun HotSearchedList() {
//    Row {
//        SearchContentCard()
//
//        Spacer(Modifier.width(8.dp))
//
//        SearchContentCard()
//    }
}

@Preview
@Composable
private fun SearchingContentPreview() {

}