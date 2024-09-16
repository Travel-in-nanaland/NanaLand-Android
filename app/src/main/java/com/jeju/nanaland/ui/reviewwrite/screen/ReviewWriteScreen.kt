package com.jeju.nanaland.ui.reviewwrite.screen

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.navOptions
import com.jeju.nanaland.R
import com.jeju.nanaland.domain.request.UriRequestBody
import com.jeju.nanaland.globalvalue.constant.ROUTE_REVIEW_WRITE
import com.jeju.nanaland.globalvalue.constant.ROUTE_REVIEW_WRITE_COMPLETE
import com.jeju.nanaland.globalvalue.constant.ROUTE_REVIEW_WRITE_KEYWORD
import com.jeju.nanaland.globalvalue.type.ReviewCategoryType
import com.jeju.nanaland.globalvalue.type.ReviewKeyword
import com.jeju.nanaland.ui.component.common.BottomOkButton
import com.jeju.nanaland.ui.component.common.CustomSurface
import com.jeju.nanaland.ui.component.common.DialogCommon
import com.jeju.nanaland.ui.component.common.UploadImages
import com.jeju.nanaland.ui.component.common.dialog.SubmitLoadingDialog
import com.jeju.nanaland.ui.component.common.text.TextWithPointColor
import com.jeju.nanaland.ui.component.common.topbar.CustomTopBar
import com.jeju.nanaland.ui.reviewwrite.ReviewWriteUiState
import com.jeju.nanaland.ui.reviewwrite.ReviewWriteViewModel
import com.jeju.nanaland.ui.theme.body02
import com.jeju.nanaland.ui.theme.bodyBold
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.navigation.navigate
import com.jeju.nanaland.util.resource.getString
import com.jeju.nanaland.util.resource.getStringArray
import com.jeju.nanaland.util.ui.UiState
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun ReviewWriteScreen(
    navController: NavController,
    id: Int,
    category: ReviewCategoryType,
    viewModel: ReviewWriteViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    val callState = viewModel.callState.collectAsStateWithLifecycle()
    var cancelDialogVisible by remember { mutableStateOf(false) }
    BackHandler {
        cancelDialogVisible = true
    }
    ReviewWriteUI(
        uiState = uiState.value,
        reviewText = viewModel.reviewText,
        moveToBackScreen = { cancelDialogVisible = true },
        moveToKeywordScreen = { navController.navigate(ROUTE_REVIEW_WRITE_KEYWORD) },
        moveToCompleteScreen = {
            viewModel.submit(
                id = id,
                category = category,
                snapshotData = uiState.value,
                snapshotContent = viewModel.reviewText,
                newImages = uiState.value.reviewImage.filter{
                    it.first == -1
                }.map {
                    UriRequestBody(context, Uri.parse(it.second))
                }
            )
        },
        onChangedRating = viewModel::updateRating,
        onChangeImages = viewModel::changeImage,
        onChangedText = {
            if(it.length > ReviewWriteViewModel.MAX_TEXT_LENGTH) {
                Toast.makeText(context, getString(R.string.review_write_error_maximum_text, ReviewWriteViewModel.MAX_TEXT_LENGTH ), Toast.LENGTH_SHORT).show()
                viewModel.updateReviewText(it.substring(0, ReviewWriteViewModel.MAX_TEXT_LENGTH + 1))
            }
            else
                viewModel.updateReviewText(it)
        },
        onRemoveKeyword = viewModel::removeKeyword
    )
    callState.value?.let {
        when (it) {
            is UiState.Loading -> {
                SubmitLoadingDialog(
                    getString(R.string.loading_wait_text_desc2),
                )
            }
            is UiState.Success -> {
                val bundle = bundleOf(
                    "category" to category.toString()
                )
                navController.navigate(ROUTE_REVIEW_WRITE_COMPLETE, bundle, navOptions = navOptions{
                    popUpTo(ROUTE_REVIEW_WRITE) { inclusive = true}
                })
                viewModel.setCallStateNull()
                viewModel.updateReviewText("")
            }
            is UiState.Failure -> {
                viewModel.setCallStateNull()
                Toast.makeText(context, getString(R.string.common_인터넷_문제), Toast.LENGTH_LONG).show()
            }
        }
    }
    if(cancelDialogVisible) {
        DialogCommon(
            title = getString(R.string.review_write_cancel_dialog_title),
            subTitle = getString(R.string.review_write_cancel_dialog_subtitle),
            onDismissRequest = { cancelDialogVisible = false },
            onPositive = { navController.popBackStack() },
            onNegative = { cancelDialogVisible = false }
        )
    }
}


@Composable
private fun ReviewWriteUI(
    uiState: ReviewWriteUiState,
    reviewText: String,
    moveToBackScreen: () -> Unit,
    moveToKeywordScreen: () -> Unit,
    moveToCompleteScreen: () -> Unit,
    onChangedRating: (Int) -> Unit,
    onChangeImages: (List<Pair<Int,String>>) -> Unit,
    onChangedText: (String) -> Unit,
    onRemoveKeyword: (ReviewKeyword) -> Unit,
) {

    val scrollState = rememberScrollState()

    CustomSurface {
        CustomTopBar(
            title = getString(R.string.review_write_title),
            onBackButtonClicked = moveToBackScreen
        )

        Spacer(modifier = Modifier.height(24.dp))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .imePadding()
                .verticalScroll(scrollState)
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                GlideImage(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    imageModel = { uiState.titleImg },
                    imageOptions = ImageOptions(
                        contentScale = ContentScale.Crop,
                        alignment = Alignment.Center
                    ),
                    previewPlaceholder = R.drawable.img_ad_1
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = uiState.titleTxt,
                    color = getColor().black,
                    style = bodyBold,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = uiState.subTitleTxt,
                    color = getColor().black,
                    style = body02,
                    textAlign = TextAlign.Center
                )

                MyDivider()
                TextWithPointColor(
                    text = getString(R.string.review_write_select_rating),
                    style = bodyBold
                )
                Spacer(modifier = Modifier.height(16.dp))
                StarRating(rating = uiState.reviewRating) {
                    onChangedRating(it)
                }

                MyDivider()
                TextWithPointColor(
                    text = getString(R.string.review_write_writing),
                    style = bodyBold
                )
                Spacer(modifier = Modifier.height(16.dp))

                UploadImages(
                    images = uiState.reviewImage.map { it.second },
                    onChangeImages = { images ->
                        onChangeImages(images.map { image ->
                            Pair(
                                uiState.reviewImage.firstOrNull { it.second == image }?.first ?: -1,
                                image
                            )
                        })
                    }
                )

                ReviewText(
                    text = reviewText,
                    maxTextLength = ReviewWriteViewModel.MAX_TEXT_LENGTH,
                    onText = onChangedText
                )

                ReviewKeywordChip(
                    keywords = uiState.reviewKeyword,
                    onRemoveKeyword = onRemoveKeyword,
                    moveToKeywordScreen = moveToKeywordScreen
                )
                Spacer(modifier = Modifier.height(32.dp))
            }
            BottomOkButton(getString(R.string.review_write_complete),uiState.canSubmit){
                moveToCompleteScreen()
            }
            Spacer(modifier = Modifier.height(16.dp))
        } // Column
    } // CustomSurface
} // ReviewWriteUI

@Composable
private fun MyDivider() {
    HorizontalDivider(
        modifier = Modifier
            .width(64.dp)
            .padding(vertical = 24.dp),
        thickness = 1.dp,
        color = getColor().gray02
    )
}

@Composable
private fun StarRating(
    rating: Int,
    maxStars: Int = 5,
    onRatingChanged: (Int) -> Unit
) {
    Row {
        for (i in 1..maxStars) {
            Icon(
                painter = painterResource(R.drawable.ic_star_rating),
                contentDescription = null,
                modifier = Modifier
                    .clickable {
                        if(rating == i)
                            onRatingChanged(i - 1)
                        else
                            onRatingChanged(i)
                    },
                tint = if (i <= rating) getColor().yellow
                    else getColor().gray02

            )
        }
    }
}

@Composable
private fun ReviewText(
    text: String,
    maxTextLength: Int,
    onText: (String) -> Unit
) {
    val tl = text.length

    BasicTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
            .border(
                1.dp,
                getColor().gray02,
                RoundedCornerShape(8.dp)
            ),
        value = text,
        onValueChange = onText,
        textStyle = body02.copy(
            color = getColor().black
        ),
        minLines = 6
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp),
            ) {
                it()
                if(tl == 0)
                    Text(
                        text = getString(R.string.review_write_writing_hint),
                        style = body02,
                        color = getColor().gray01
                    )
            }

            Text(
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(end = 12.dp, bottom = 8.dp),
                text = buildAnnotatedString {
                    append("(")
                    if(tl > maxTextLength)
                        withStyle(
                            style = SpanStyle(
                                color = getColor().warning
                            )
                        ) {
                            append(tl.toString())
                        }
                    else
                        append(tl.toString())
                    append(" /$maxTextLength)")
                },
                color = getColor().gray01,
                style = body02
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ReviewKeywordChip(
    keywords: List<ReviewKeyword>,
    onRemoveKeyword: (ReviewKeyword) -> Unit,
    moveToKeywordScreen: () -> Unit
) {
    FlowRow(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(
            modifier = Modifier
                .border(
                    width = 1.dp,
                    shape = RoundedCornerShape(30.dp),
                    color = getColor().main
                )
                .clickable { moveToKeywordScreen() }
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = getString(R.string.review_write_add_keyword),
                color = getColor().main,
                style = body02
            )
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                modifier = Modifier.size(16.dp),
                imageVector = Icons.Default.Add,
                contentDescription = null,
                tint = getColor().main
            )
        }

        keywords.forEach {
            Row(
                modifier = Modifier
                    .background(
                        color = getColor().main10,
                        shape = RoundedCornerShape(30.dp)
                    )
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .clickable { onRemoveKeyword(it) },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = getStringArray(R.array.review_keyword)[it.stringIndex],
                    color = getColor().main,
                    style = body02
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    modifier = Modifier.size(16.dp),
                    imageVector = Icons.Default.Clear,
                    contentDescription = null,
                    tint = getColor().main
                )
            }
        }
    }
}