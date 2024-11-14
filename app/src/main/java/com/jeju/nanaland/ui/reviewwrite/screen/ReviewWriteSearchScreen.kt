package com.jeju.nanaland.ui.reviewwrite.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jeju.nanaland.R
import com.jeju.nanaland.domain.entity.review.ReviewKeywordResult
import com.jeju.nanaland.domain.navigation.NavViewModel
import com.jeju.nanaland.domain.navigation.ROUTE
import com.jeju.nanaland.ui.component.common.CustomSurface
import com.jeju.nanaland.ui.component.common.topbar.CustomTopBar
import com.jeju.nanaland.ui.reviewwrite.ReviewWriteViewModel
import com.jeju.nanaland.ui.theme.body01
import com.jeju.nanaland.ui.theme.body02SemiBold
import com.jeju.nanaland.ui.theme.caption01
import com.jeju.nanaland.ui.theme.caption02
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.resource.getString
import com.jeju.nanaland.util.ui.UiState
import com.jeju.nanaland.util.ui.clickableNoEffect
import com.jeju.nanaland.util.ui.conditional
import com.jeju.nanaland.util.ui.drawColoredShadow
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun ReviewWriteSearchScreen(
    navViewModel: NavViewModel,
    viewModel: ReviewWriteViewModel = hiltViewModel()
) {
    val keyword = viewModel.keywordText.collectAsStateWithLifecycle()
    val searchResult = viewModel.reviewsByKeyword.collectAsStateWithLifecycle(UiState.Success(emptyList()))

    ReviewWriteSearchUI(
        keyword = keyword.value,
        onKeyword = { viewModel.keywordText.value = it },
        data = searchResult.value,
        moveToBackScreen = { navViewModel.popBackStack() },
        moveToWriteScreen = {
            viewModel.init(it.id, it.category, false)
            navViewModel.navigate(ROUTE.Content.ReviewWrite(it.id, it.category.toString()))
        }
    )
}

@Composable
private fun ReviewWriteSearchUI(
    keyword: String,
    onKeyword: (String) -> Unit,
    data: UiState<List<ReviewKeywordResult>>,
    moveToBackScreen: () -> Unit,
    moveToWriteScreen: (ReviewKeywordResult) -> Unit,
) {
    var successData by remember { mutableStateOf(emptyList<ReviewKeywordResult>()) }

    LaunchedEffect(data) {
        if(data is UiState.Success)
            successData = data.data
    }

    CustomSurface {
        CustomTopBar(
            title = getString(R.string.review_write_title),
            onBackButtonClicked = moveToBackScreen
        )
        Spacer(modifier = Modifier.height((24 - 16).dp))

        SearchField(keyword, onKeyword)

        if(keyword.isNotBlank()){
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                when (data) {
                    is UiState.Failure -> ResultNetworkErrorView()
                    is UiState.Loading -> {
                        if(successData.isEmpty())
                            ResultSkeletonRowView()
                        else
                            ResultDataRowView(successData, true, moveToWriteScreen)
                    }
                    is UiState.Success -> ResultDataRowView(successData, false, moveToWriteScreen)
                }
            }
        }
    }
}

@Composable
private fun SearchField(
    text: String,
    onText: (String) -> Unit
) {
    BasicTextField(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .drawColoredShadow(
                color = getColor().black,
                alpha = 0.1f,
                shadowRadius = 10.dp,
            )
            .clip(RoundedCornerShape(10.dp))
            .background(
                color = getColor().white,
                shape = RoundedCornerShape(10.dp)
            )
            .padding(horizontal = 8.dp, vertical = 15.dp),
        value = text,
        onValueChange = onText,
        textStyle = body01.copy(
            color = getColor().black
        ),
        singleLine = true,
    ) {
        Row {
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = null,
                tint = getColor().main
            )
            Spacer(modifier = Modifier.width(8.dp))
            Box(Modifier.fillMaxWidth()) {
                if(text.isEmpty()) {
                    Text(
                        text = getString(R.string.review_write_search_hint),
                        style = body01,
                        color = getColor().gray01,
                        maxLines = 1
                    )
                }
                it()
            }
        }
    }
}

@Composable
private fun ResultDataRowView(
    data: List<ReviewKeywordResult>,
    isBlur: Boolean,
    onClick:(ReviewKeywordResult) -> Unit
) {
    if(data.isEmpty()){
        Text(
            text = getString(R.string.search_result_screen_no_result),
            style = body01,
            color = getColor().gray01
        )
        Spacer(modifier = Modifier.height(40.dp))
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .conditional(isBlur) { blur(1.dp) },
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(data) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickableNoEffect {
                            if(!isBlur)
                                onClick(it)
                        }
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    GlideImage(
                        modifier = Modifier
                            .size(56.dp)
                            .clip(RoundedCornerShape(6.dp)),
                        imageModel = { it.firstImage.originUrl },
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(
                            modifier = Modifier
                                .background(
                                    color = getColor().main10,
                                    shape = RoundedCornerShape(50)
                                )
                                .padding(horizontal = 12.dp, vertical = 1.dp),
                            text = it.categoryValue,
                            color = getColor().main,
                            style = caption02
                        )
                        Text(
                            text = it.title,
                            style = body02SemiBold,
                            color = getColor().black,
                        )
                        Text(
                            text = it.address,
                            style = caption01,
                            color = getColor().black,
                        )
                    }
                }
            }
        }
    }
}
@Composable
private fun ResultSkeletonRowView(
    dataCnt: Int = 16,
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(dataCnt) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    Modifier
                        .size(56.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .background(getColor().skeleton)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Box(
                        Modifier
                            .size(width = 44.dp, height = 12.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(getColor().skeleton)
                    )
                    Box(
                        Modifier
                            .size(width = 80.dp, height = 12.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(getColor().skeleton)
                    )
                    Box(
                        Modifier
                            .size(width = 156.dp, height = 12.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(getColor().skeleton)
                    )
                }
            }
        }
    }
}
@Composable
private fun ResultNetworkErrorView(
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_sad_orange),
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = getString(R.string.common_인터넷_문제),
            style = body01,
            color = getColor().gray01
        )

        Spacer(modifier = Modifier.height(40.dp))
    }
}