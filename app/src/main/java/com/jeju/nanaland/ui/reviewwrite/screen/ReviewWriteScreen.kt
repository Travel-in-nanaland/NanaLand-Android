package com.jeju.nanaland.ui.reviewwrite.screen

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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jeju.nanaland.R
import com.jeju.nanaland.domain.navigation.NavViewModel
import com.jeju.nanaland.domain.navigation.ROUTE
import com.jeju.nanaland.globalvalue.type.ReviewCategoryType
import com.jeju.nanaland.globalvalue.type.ReviewKeyword
import com.jeju.nanaland.ui.component.common.BottomOkButton
import com.jeju.nanaland.ui.component.common.CustomSurface
import com.jeju.nanaland.ui.component.common.UploadImages
import com.jeju.nanaland.ui.component.common.dialog.DialogCommon
import com.jeju.nanaland.ui.component.common.dialog.DialogCommonType
import com.jeju.nanaland.ui.component.common.dialog.SubmitLoadingDialog
import com.jeju.nanaland.ui.component.common.text.TextWithPointColor
import com.jeju.nanaland.ui.component.common.topbar.TopBarCommon
import com.jeju.nanaland.ui.reviewwrite.ReviewWriteUiState
import com.jeju.nanaland.ui.reviewwrite.ReviewWriteViewModel
import com.jeju.nanaland.ui.theme.body02
import com.jeju.nanaland.ui.theme.bodyBold
import com.jeju.nanaland.ui.theme.caption01SemiBold
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.resource.getString
import com.jeju.nanaland.util.resource.getStringArray
import com.jeju.nanaland.util.ui.UiState
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ReviewWriteScreen(
    navViewModel: NavViewModel,
    id: Int,
    category: ReviewCategoryType,
    viewModel: ReviewWriteViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    val callState = viewModel.callState.collectAsStateWithLifecycle()
    var cancelDialogVisible by remember { mutableStateOf(false) }
    val writeMaximumErrorToast = Toast.makeText(context, getString(R.string.review_write_error_maximum_text, ReviewWriteViewModel.MAX_TEXT_LENGTH ), Toast.LENGTH_SHORT)
    BackHandler {
        cancelDialogVisible = true
    }
    ReviewWriteUI(
        isEdit = viewModel.isEdit,
        uiState = uiState.value,
        reviewText = viewModel.reviewText,
        moveToBackScreen = { cancelDialogVisible = true },
        moveToKeywordScreen = { navViewModel.navigate(ROUTE.Content.ReviewWrite.Keyword) },
        moveToCompleteScreen = {
            viewModel.submit(
                id = id,
                category = category,
                snapshotData = uiState.value,
                snapshotContent = viewModel.reviewText,
                newImages = uiState.value.reviewImage.filter{
                    it.first == -1
                }.map {
                    it.second
                }
            )
        },
        onChangedRating = viewModel::updateRating,
        onChangeImages = viewModel::changeImage,
        onChangedText = {
            if(it.length > ReviewWriteViewModel.MAX_TEXT_LENGTH) {
                writeMaximumErrorToast.show()
                CoroutineScope(Dispatchers.Default).launch {
                    delay(1500)
                    writeMaximumErrorToast.cancel()
                }
                val viewMaximumLength = ReviewWriteViewModel.MAX_TEXT_LENGTH + 1
                if(
                    it.length == viewMaximumLength || // for overflow view
                    it.length - viewModel.reviewText.length > 2 // for paste
                )
                    viewModel.updateReviewText(it.substring(0, viewMaximumLength))
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
                navViewModel.navigatePopUpTo(ROUTE.Content.ReviewWrite.Complete(category.toString()), ROUTE.Content.ReviewWrite)
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
            DialogCommonType.Write,
            onDismiss = { cancelDialogVisible = false },
            onYes = {cancelDialogVisible = false; navViewModel.popBackStack()},
        )
    }
}


@Composable
private fun ReviewWriteUI(
    isEdit: Boolean,
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
        TopBarCommon(
            title = getString( if(!isEdit) R.string.review_write_title  else  R.string.review_write_title_edit),
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
                    previewPlaceholder = painterResource(R.drawable.img_ad_1)
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
                Title(getString(R.string.review_write_select_rating), true)
                Spacer(modifier = Modifier.height(16.dp))
                StarRating(rating = uiState.reviewRating) {
                    onChangedRating(it)
                }

                MyDivider()
                Title(getString(R.string.review_write_keyword), true)
                Spacer(modifier = Modifier.height(16.dp))
                ReviewKeywordChip(
                    keywords = uiState.reviewKeyword,
                    onRemoveKeyword = onRemoveKeyword,
                    moveToKeywordScreen = moveToKeywordScreen
                )

                MyDivider()
                Title(getString(R.string.review_write_writing), true)
                Spacer(modifier = Modifier.height(16.dp))
                ReviewText(
                    text = reviewText,
                    maxTextLength = ReviewWriteViewModel.MAX_TEXT_LENGTH,
                    onText = onChangedText
                )

                MyDivider()
                Title(getString(R.string.review_write_picture), false)
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
    if(keywords.isEmpty()) {
        Row(Modifier.fillMaxWidth()) {
            Spacer(modifier = Modifier.weight(1f))
            ReviewKeywordAddButton(moveToKeywordScreen)
            Spacer(modifier = Modifier.weight(1f))
        }
    } else {
        FlowRow(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            ReviewKeywordAddButton(moveToKeywordScreen)

            keywords.forEach {
                Row(
                    modifier = Modifier
                        .background(
                            color = getColor().main10,
                            shape = RoundedCornerShape(30.dp)
                        )
                        .clickable { onRemoveKeyword(it) }
                        .padding(horizontal = 12.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = getStringArray(R.array.review_keyword)[it.stringIndex],
                        color = getColor().main,
                        style = caption01SemiBold
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
}

@Composable
private fun ReviewKeywordAddButton(
    moveToKeywordScreen: () -> Unit
) {
    Row(
        modifier = Modifier
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(30.dp),
                color = getColor().main
            )
            .clickable { moveToKeywordScreen() }
            .padding(horizontal = 12.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = getString(R.string.review_write_add_keyword),
            color = getColor().main,
            style = caption01SemiBold
        )
        Spacer(modifier = Modifier.width(4.dp))
        Icon(
            modifier = Modifier.size(16.dp),
            imageVector = Icons.Default.Add,
            contentDescription = null,
            tint = getColor().main
        )
    }
}

@Composable
private fun Title(
    text: String,
    isEssential: Boolean
) {
    Row {
        TextWithPointColor(
            text = text,
            style = bodyBold
        )
        if(isEssential)
            Text(
                modifier = Modifier.padding(start = 2.dp, bottom = 8.dp),
                text = "*",
                style = body02,
                color = getColor().main
            )
    }
}