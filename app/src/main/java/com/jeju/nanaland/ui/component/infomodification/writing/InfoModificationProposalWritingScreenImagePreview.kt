package com.jeju.nanaland.ui.component.infomodification.writing

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.theme.body02Bold
import com.jeju.nanaland.ui.theme.caption01
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.resource.getString
import com.jeju.nanaland.util.ui.clickableNoEffect
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun InfoModificationProposalWritingScreenImagePreview(
    imageUri: String?,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(getColor().gray02)
            .clickableNoEffect { onClick() },
        contentAlignment = Alignment.Center
    ) {
        if (imageUri != null) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                GlideImage(
                    modifier = Modifier.fillMaxSize(),
                    imageModel = { imageUri },
                    imageOptions = ImageOptions(contentScale = ContentScale.Crop)
                )

                Image(
                    modifier = Modifier.size(56.dp),
                    painter = painterResource(R.drawable.ic_camera_outlined),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(getColor().white)
                )
            }
        } else {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier.size(56.dp),
                    painter = painterResource(R.drawable.ic_camera_outlined),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(getColor().white)
                )

                Spacer(Modifier.height(8.dp))

                Text(
                    text = getString(R.string.info_modification_proposal_사진_추가하기),
                    color = getColor().white,
                    style = body02Bold
                )

                Spacer(Modifier.height(4.dp))

                Text(
                    text = getString(R.string.info_modification_proposal_text2),
                    color = getColor().white,
                    style = caption01,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}