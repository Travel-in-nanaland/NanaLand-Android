package com.jeju.nanaland.ui.reviewwrite.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.jeju.nanaland.R
import com.jeju.nanaland.domain.navigation.NavViewModel
import com.jeju.nanaland.domain.navigation.ROUTE
import com.jeju.nanaland.globalvalue.type.ReviewCategoryType
import com.jeju.nanaland.ui.component.common.BottomOkButton
import com.jeju.nanaland.ui.component.common.CustomSurface
import com.jeju.nanaland.ui.component.common.text.TextWithPointColor
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.ui.theme.largeTitle02
import com.jeju.nanaland.ui.theme.title02
import com.jeju.nanaland.util.resource.getString
import com.jeju.nanaland.util.ui.ScreenPreview

@ScreenPreview
@Composable
private fun ReviewWriteCompleteScreenPreview() {
    ReviewWriteCompleteUI(ReviewCategoryType.EXPERIENCE, {}, {})
}

@Composable
fun ReviewWriteCompleteScreen(
    navViewModel: NavViewModel,
    previousScreenIsSearch: Boolean,
    categoryType: ReviewCategoryType
) {
    ReviewWriteCompleteUI(
        category = categoryType,
        onAgain = { navViewModel.popBackStack() },
        onAdd = {
            if(previousScreenIsSearch)
                navViewModel.popBackStack()
            else
                navViewModel.navigatePopUpTo(ROUTE.Content.ReviewWrite,ROUTE.Content.ReviewWrite.Complete(""))
        }
    )
}


@Composable
private fun ReviewWriteCompleteUI(
    category: ReviewCategoryType,
    onAgain: () -> Unit,
    onAdd: () -> Unit,
) {
    val uiData = getUiDataByCategory(category)
    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(uiData.first)
    )
    val progress by animateLottieCompositionAsState(composition)

    CustomSurface {
        Box {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(Modifier.height(80.dp))

                LottieAnimation(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 55.dp),
                    composition = composition,
                    progress = { progress },
                )

                Spacer(Modifier.height(40.dp))

                Text(
                    text = uiData.second,
                    color = getColor().main,
                    style = largeTitle02,
                    textAlign = TextAlign.Center
                )

                Spacer(Modifier.height(8.dp))

                TextWithPointColor(
                    text = uiData.third,
                    color = getColor().black,
                    style = title02.copy(textAlign = TextAlign.Center),
                )
            }

            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 24.dp)
            ) {
                BottomOkButton(
                    text = getString(R.string.review_write_keyword_complete_btn_again),
                    isActivated = true,
                    onClick = onAgain
                )
            }
        }
    }
}

private fun getUiDataByCategory(
    categoryType: ReviewCategoryType
): Triple<Int/* image id */,String/* title */,String/* sub text */> {
    return when(categoryType) { // TODO
        ReviewCategoryType.EXPERIENCE -> Triple(
            R.raw.review_complete_star,
            getString(R.string.review_write_keyword_complete_title1),
            getString(R.string.review_write_keyword_complete_sub1),
        )
        ReviewCategoryType.RESTAURANT ->  Triple(
            R.raw.review_complete_salad,
            getString(R.string.review_write_keyword_complete_title2),
            getString(R.string.review_write_keyword_complete_sub2),
        )
        else -> throw Exception("unsupported ui")
    }
}