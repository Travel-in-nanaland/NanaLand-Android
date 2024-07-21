package com.jeju.nanaland.ui.reviewwrite

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.jeju.nanaland.R
import com.jeju.nanaland.globalvalue.constant.ROUTE_REVIEW_WRITE
import com.jeju.nanaland.globalvalue.constant.ROUTE_REVIEW_WRITE_COMPLETE
import com.jeju.nanaland.globalvalue.constant.ROUTE_REVIEW_WRITE_KEYWORD
import com.jeju.nanaland.ui.component.common.BottomOkButton
import com.jeju.nanaland.ui.component.common.CustomSurface
import com.jeju.nanaland.ui.component.common.CustomTopBar
import com.jeju.nanaland.ui.theme.body02
import com.jeju.nanaland.ui.theme.bodyBold
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.resource.getString
import com.jeju.nanaland.util.ui.ScreenPreview
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@ScreenPreview
@Composable
private fun ReviewWriteScreenPreview() {
    ReviewWriteUI(ReviewWriteUiState(), "", { }, { }, { }, { }, { }, { }, { }, { })
}

@Composable
fun ReviewWriteScreen(
    navController: NavController,
    viewModel: ReviewWriteViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    ReviewWriteUI(
        uiState = uiState.value,
        reviewText = viewModel.reviewText,
        moveToBackScreen = { navController.popBackStack() },
        moveToKeywordScreen = { navController.navigate(ROUTE_REVIEW_WRITE_KEYWORD) },
        moveToCompleteScreen = {
            navController.navigate(ROUTE_REVIEW_WRITE_COMPLETE) {
                popUpTo(ROUTE_REVIEW_WRITE) { inclusive = true}
            }
        },
            onChangedRating = viewModel::updateRating,
            onAddImage = viewModel::addImage,
            onRemoveImage = viewModel::removeImage,
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
}


@Composable
private fun ReviewWriteUI(
    uiState: ReviewWriteUiState,
    reviewText: String,
    moveToBackScreen: () -> Unit,
    moveToKeywordScreen: () -> Unit,
    moveToCompleteScreen: () -> Unit,
    onChangedRating: (Int) -> Unit,
    onAddImage: (List<Uri>) -> Unit,
    onRemoveImage: (Uri) -> Unit,
    onChangedText: (String) -> Unit,
    onRemoveKeyword: (String) -> Unit,
) {

    val context = LocalContext.current
    val scrollState = rememberScrollState()
    val takePhotoFromAlbumLauncher =
        rememberLauncherForActivityResult(
            if(ReviewWriteViewModel.MAX_IMAGE_CNT - uiState.reviewImage.size <= 1)
                ActivityResultContracts.PickVisualMedia()
            else
                ActivityResultContracts.PickMultipleVisualMedia(ReviewWriteViewModel.MAX_IMAGE_CNT - uiState.reviewImage.size)
        ) { uri ->
            if(uri == null)
                return@rememberLauncherForActivityResult
            if(uri is Uri)
                onAddImage(listOf(uri))
            else if(uri is List<*>)
                onAddImage(uri as List<Uri>)
        }

    CustomSurface {
        CustomTopBar(
            title = "",
            onBackButtonClicked = moveToBackScreen
        )

        Spacer(modifier = Modifier.height(24.dp))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .imePadding()
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally,
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
                style = bodyBold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = uiState.subTitleTxt,
                color = getColor().black,
                style = body02
            )

            MyDivider()
            AnnotatedStringConvertQuotes(getString(R.string.review_write_select_rating))
            Spacer(modifier = Modifier.height(16.dp))
            StarRating(rating = uiState.reviewRating) {
                onChangedRating(it)
            }

            MyDivider()
            AnnotatedStringConvertQuotes(getString(R.string.review_write_writing))
            Spacer(modifier = Modifier.height(16.dp))

            ReviewImage(
                images = uiState.reviewImage,
                onAddImage = {
                    if(uiState.reviewImage.size < ReviewWriteViewModel.MAX_IMAGE_CNT)
                        takePhotoFromAlbumLauncher.launch(
                            PickVisualMediaRequest(
                                ActivityResultContracts.PickVisualMedia.ImageOnly,
                            )
                        )
                    else
                        Toast.makeText(context, getString(R.string.review_write_error_maximum_picture, ReviewWriteViewModel.MAX_IMAGE_CNT ), Toast.LENGTH_SHORT).show()
                },
                onRemoveImage = onRemoveImage
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
private fun AnnotatedStringConvertQuotes(text: String) {
    val conv = text.split('"')
    Text(
        text = buildAnnotatedString {
            conv.forEachIndexed { index, s ->
                if(index % 2 == 1)
                    withStyle(
                        style = SpanStyle(
                            color = getColor().main
                        )
                    ) {
                        append(s)
                    }
                else
                    append(s)
            }
        },
        color = getColor().black,
        style = bodyBold
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
private fun ReviewImage(
    images: List<Uri>,
    maxImageCnt: Int = 5,
    onAddImage: () ->Unit,
    onRemoveImage: (Uri) ->Unit,
) {
    val scrollState = rememberScrollState()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(scrollState),
        horizontalArrangement = Arrangement.spacedBy(8.dp)

    ) {
        Spacer(modifier = Modifier.width(16.dp))

        Column (
            modifier = Modifier
                .clickable {
                    onAddImage()
                }
                .size(80.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(getColor().gray02),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Icon(
                modifier = Modifier.size(38.dp),
                painter = painterResource(id = R.drawable.ic_camera_outlined),
                contentDescription = null,
                tint = getColor().white
            )
            Text(
                text = "${images.size} / $maxImageCnt",
                color = getColor().white,
                style = body02
            )
        }

        images.forEach { image ->
            Box(
                Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp))
            ) {
                GlideImage(
                    modifier = Modifier.fillMaxSize(),
                    imageModel = { image },
                    imageOptions = ImageOptions(
                        contentScale = ContentScale.Crop,
                        alignment = Alignment.Center
                    ),
                    previewPlaceholder = R.drawable.img_ad_1
                )
                Box(
                    Modifier
                        .padding(2.dp)
                        .size(22.dp)
                        .align(Alignment.TopEnd)
                        .clickable {
                            onRemoveImage(image)
                        }
                ) {
                    Icon(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(3.dp)
                            .clip(CircleShape)
                            .background(getColor().black)
                            .padding(2.dp),
                        imageVector = Icons.Default.Close,
                        contentDescription = null,
                        tint = getColor().white
                    )
                }
            }
        }

        Spacer(modifier = Modifier.width(16.dp))
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
            .padding(16.dp)
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
                style = bodyBold
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ReviewKeywordChip(
    keywords: List<String>,
    onRemoveKeyword: (String) -> Unit,
    moveToKeywordScreen: () -> Unit
) {
    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
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
                    text = it,
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