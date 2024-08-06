package com.jeju.nanaland.ui.component.common

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.theme.body02
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.resource.getString
import com.jeju.nanaland.util.ui.clickableNoEffect
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun UploadImages(
    images: List<Uri>,
    maxImageCnt: Int = 5,
    layoutSize: Dp = 80.dp,
    horizontalPadding: Dp = 16.dp,
    clickImage: @Composable (Int,Int) -> Unit = { c, m -> ClickImage(c, m) },
    onChangeImages: (List<Uri>) ->Unit,
) {
    val context = LocalContext.current

    val takePhotoFromAlbumLauncher =
        rememberLauncherForActivityResult(
            if(maxImageCnt - images.size <= 1)
                ActivityResultContracts.PickVisualMedia()
            else
                ActivityResultContracts.PickMultipleVisualMedia(maxImageCnt - images.size)
        ) { uri ->
            if(uri == null)
                return@rememberLauncherForActivityResult
            if(uri is Uri)
                onChangeImages(images.plus(uri))
            else if(uri is List<*>)
                onChangeImages(images.plus(uri as List<Uri>))
        }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(8.dp)

    ) {
        Spacer(modifier = Modifier.width(horizontalPadding))

        Box(
            modifier = Modifier
                .size(layoutSize)
                .clickableNoEffect {
                    if (images.size < maxImageCnt)
                        takePhotoFromAlbumLauncher.launch(
                            PickVisualMediaRequest(
                                ActivityResultContracts.PickVisualMedia.ImageOnly,
                            )
                        )
                    else
                        Toast.makeText(
                            context, getString(
                                R.string.review_write_error_maximum_picture,
                                maxImageCnt
                            ),
                            Toast.LENGTH_SHORT
                        ).show()
                }
        ) {
            clickImage(images.size, maxImageCnt)
        }

        images.forEach { image ->
            Box(
                Modifier
                    .size(layoutSize)
                    .clip(RoundedCornerShape(8.dp))
            ) {
                GlideImage(
                    modifier = Modifier.fillMaxSize(),
                    imageModel = { image },
                    imageOptions = ImageOptions(
                        contentScale = ContentScale.Crop,
                        alignment = Alignment.Center
                    )
                )
                Box(
                    Modifier
                        .padding(2.dp)
                        .size(22.dp)
                        .align(Alignment.TopEnd)
                        .clickableNoEffect {
                            onChangeImages(images.minus(image))
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

        Spacer(modifier = Modifier.width(horizontalPadding))
    }
}

@Composable
private fun ClickImage(
    currentImageCnt: Int,
    maxImageCnt: Int
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(8.dp))
            .background(getColor().gray02)
            .padding(horizontal = 22.dp, vertical = 13.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier
                .weight(1f)
                .aspectRatio(1f),
            painter = painterResource(id = R.drawable.ic_camera_outlined),
            contentDescription = null,
            tint = getColor().white
        )
        Text(
            text = "$currentImageCnt / $maxImageCnt",
            color = getColor().white,
            style = body02
        )
    }
}