package com.jeju.nanaland.ui.reviewwrite.screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.jeju.nanaland.R
import com.jeju.nanaland.globalvalue.type.ReviewKeyword
import com.jeju.nanaland.ui.component.common.BottomOkButton
import com.jeju.nanaland.ui.component.common.CustomSurface
import com.jeju.nanaland.ui.component.common.topbar.CustomTopBar
import com.jeju.nanaland.ui.reviewwrite.ReviewWriteViewModel
import com.jeju.nanaland.ui.theme.body02
import com.jeju.nanaland.ui.theme.bodyBold
import com.jeju.nanaland.ui.theme.caption01
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.resource.getString
import com.jeju.nanaland.util.ui.ScreenPreview

@ScreenPreview
@Composable
private fun ReviewWriteKeywordScreenPreview() {
    ReviewWriteKeywordUI(listOf(), {}, {}, {})
}

@Composable
fun ReviewWriteKeywordScreen(
    navController: NavController,
    viewModel: ReviewWriteViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    var selectKeyword by remember {
        mutableStateOf(viewModel.uiState.value.reviewKeyword)
    }

    ReviewWriteKeywordUI(
        moveToBackScreen = { navController.popBackStack() },
        selectKeyword = selectKeyword,
        onKeyword = {
            val isSelect = it in selectKeyword
            if(ReviewWriteViewModel.MAX_KEYWORD_CNT <= selectKeyword.size && !isSelect)
                Toast.makeText(context, getString(R.string.review_write_keyword_error_over, ReviewWriteViewModel.MAX_KEYWORD_CNT), Toast.LENGTH_SHORT).show()
            else {
                selectKeyword = if(isSelect)
                    selectKeyword.minus(it)
                else
                    selectKeyword.plus(it)
            }
        },
        onComplete = {
            viewModel.setKeyword(selectKeyword)
            navController.popBackStack()
        }
    )
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ReviewWriteKeywordUI(
    selectKeyword: List<ReviewKeyword>,
    moveToBackScreen: () -> Unit,
    onKeyword:(ReviewKeyword) -> Unit,
    onComplete:() -> Unit,
) {
    val scrollState = rememberScrollState()
    val keywords = remember { listOf(
        Pair(getString(R.string.review_keyword_mood), ReviewKeyword.Mood.entries),
        Pair(getString(R.string.review_keyword_with), ReviewKeyword.With.entries),
        Pair(getString(R.string.review_keyword_infra), ReviewKeyword.Infra.entries)
    ) }

    CustomSurface {
        CustomTopBar(
            title = getString(R.string.review_write_keyword_title),
            onBackButtonClicked =  moveToBackScreen
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .verticalScroll(scrollState),
        ) {
            Text(
                modifier = Modifier.padding(vertical = 24.dp),
                text = stringResource(R.string.review_write_keyword_guide,
                    ReviewWriteViewModel.MIN_KEYWORD_CNT,
                    ReviewWriteViewModel.MAX_KEYWORD_CNT
                ),
                color = getColor().main,
                style = caption01
            )

            keywords.forEach { category ->
                Text(
                    modifier = Modifier.padding(bottom = 12.dp),
                    text = category.first,
                    color = getColor().black,
                    style = bodyBold
                )
                FlowRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 40.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    category.second.forEach { keyword ->
                        val isSelect =  keyword in selectKeyword
                        Text(
                            modifier = Modifier
                                .clip(RoundedCornerShape(30.dp))
                                .border(
                                    width = 1.dp,
                                    shape = RoundedCornerShape(30.dp),
                                    color = if (isSelect) getColor().main90 else getColor().gray02
                                )
                                .background(
                                    color = if (isSelect) getColor().main10 else getColor().white
                                )
                                .clickable { onKeyword(keyword) }
                                .padding(horizontal = 16.dp, vertical = 8.dp),
                            text = stringArrayResource(R.array.review_keyword)[keyword.stringIndex],
                            color = if(isSelect) getColor().main else getColor().gray02,
                            style = body02
                        )
                    }
                }
            }


            BottomOkButton(
                getString(R.string.filter_dialog_common_적용하기),
                selectKeyword.size in (ReviewWriteViewModel.MIN_KEYWORD_CNT..ReviewWriteViewModel.MAX_KEYWORD_CNT)
            ) {
                onComplete()
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
