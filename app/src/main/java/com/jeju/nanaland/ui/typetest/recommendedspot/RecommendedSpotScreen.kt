package com.jeju.nanaland.ui.typetest.recommendedspot

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jeju.nanaland.R
import com.jeju.nanaland.domain.entity.member.RecommendedPostData
import com.jeju.nanaland.ui.component.common.CustomSurface
import com.jeju.nanaland.ui.component.common.topbar.TopBarCommon
import com.jeju.nanaland.ui.component.signup.recommendedspot.RecommendedSpotScreenBottomButton
import com.jeju.nanaland.ui.component.signup.recommendedspot.RecommendedSpotScreenItem
import com.jeju.nanaland.ui.component.signup.recommendedspot.RecommendedSpotScreenText1
import com.jeju.nanaland.ui.component.signup.recommendedspot.RecommendedSpotScreenText2
import com.jeju.nanaland.util.resource.getString

@Composable
fun RecommendedSpotScreen(
    moveToMainScreen: () -> Unit,
    moveToBackScreen: () -> Unit,
    moveToDetailScreen:(Int, String) -> Unit,
    viewModel: RecommendedSpotViewModel = hiltViewModel()
) {
    val recommendedPostList = viewModel.recommendedPostList.collectAsState().value
    RecommendedSpotScreen(
        recommendedPostList = recommendedPostList,
        moveToMainScreen = moveToMainScreen,
        moveToBackScreen = moveToBackScreen,
        moveToDetailScreen = moveToDetailScreen,
        isContent = true
    )
}

@Composable
private fun RecommendedSpotScreen(
    recommendedPostList: List<RecommendedPostData>,
    moveToMainScreen: () -> Unit,
    moveToBackScreen: () -> Unit,
    moveToDetailScreen:(Int, String) -> Unit,
    isContent: Boolean
) {
    CustomSurface {
        TopBarCommon(
            title = getString(R.string.type_test_recommended_spot_추천_여행지),
            onBackButtonClicked = moveToBackScreen
        )

        Box(Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                Spacer(Modifier.height(32.dp))

                Column(Modifier.padding(start = 16.dp, end = 16.dp)) {
                    RecommendedSpotScreenText1()
                    RecommendedSpotScreenText2()
                }

                Spacer(Modifier.height(40.dp))

//                if (recommendedPostList.isNotEmpty()) {
                    Column(Modifier.padding(start = 30.dp, end = 30.dp)) {
//                        RecommendedSpotScreenItem(
//                            imageUri = "",
//                            title = "가나다라",
//                            description = "ㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏ"
//                        )
                        recommendedPostList.forEach { item ->
                            RecommendedSpotScreenItem(
                                imageUri = item.firstImage.originUrl,
                                title = item.title ?: "",
                                onDetailClick = { moveToDetailScreen(item.id, item.category) }
                            )

                            Spacer(Modifier.height(32.dp))
                        }
                    }
//                }

                Spacer(Modifier.height(80.dp))
            }

            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 20.dp)
            ) {
                RecommendedSpotScreenBottomButton { moveToMainScreen() }
            }
        }
    }
}