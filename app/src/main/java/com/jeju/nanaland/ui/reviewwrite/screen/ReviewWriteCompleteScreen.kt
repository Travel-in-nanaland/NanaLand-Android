package com.jeju.nanaland.ui.reviewwrite.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.jeju.nanaland.R
import com.jeju.nanaland.globalvalue.constant.ROUTE_REVIEW_WRITE_COMPLETE
import com.jeju.nanaland.globalvalue.constant.ROUTE_REVIEW_WRITE_ROUTE
import com.jeju.nanaland.globalvalue.constant.ROUTE_REVIEW_WRITE_SEARCH
import com.jeju.nanaland.globalvalue.type.ReviewCategoryType
import com.jeju.nanaland.ui.component.common.BottomOkButton
import com.jeju.nanaland.ui.component.common.BottomOkButtonOutlined
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
    navController: NavController,
    categoryType: ReviewCategoryType
) {
    ReviewWriteCompleteUI(
        category = categoryType,
        onAgain = { navController.popBackStack() },
        onAdd = {
            if(navController.previousBackStackEntry?.destination?.route == ROUTE_REVIEW_WRITE_SEARCH)
                navController.popBackStack()
            else
                navController.navigate(ROUTE_REVIEW_WRITE_ROUTE) {
                    popUpTo(ROUTE_REVIEW_WRITE_COMPLETE) { inclusive = true }
                }
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
        Column(
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

            Spacer(Modifier.weight(1f))

            BottomOkButton(
                text = getString(R.string.review_write_keyword_complete_btn_again),
                isActivated = true,
                onClick = onAgain
            )

            Spacer(Modifier.height(16.dp))

            BottomOkButtonOutlined(
                text = getString(R.string.review_write_keyword_complete_btn_add),
                onClick = onAdd
            )

            Spacer(Modifier.height(20.dp))
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