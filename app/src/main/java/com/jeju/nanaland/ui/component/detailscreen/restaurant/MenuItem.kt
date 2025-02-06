package com.jeju.nanaland.ui.component.detailscreen.restaurant

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.R
import com.jeju.nanaland.domain.entity.common.ImageUrl
import com.jeju.nanaland.ui.component.common.dialog.FullImageDialog
import com.jeju.nanaland.ui.theme.body02
import com.jeju.nanaland.ui.theme.body02Bold
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.language.customContext
import com.jeju.nanaland.util.resource.getString
import com.jeju.nanaland.util.string.toWithComma
import com.jeju.nanaland.util.ui.clickableNoEffect
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun MenuItem(
    menuName: String,
    price: String,
    imageUrl: ImageUrl?,
) {
    var fullImageUrl by remember { mutableStateOf<String?>(null) }

    fullImageUrl?.let {
        FullImageDialog(it) { fullImageUrl = null }
    }

    Row(
        modifier = Modifier.heightIn(min = 72.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Column {
            Text(
                text = menuName,
                color = getColor().black,
                style = body02Bold
            )

            Spacer(Modifier.height(4.dp))

            Text(
                text =
                price.toDoubleOrNull()?.let {
                    getString(
                        R.string.common_원,
                        it.toWithComma(customContext.resources.configuration.locales[0])
                    )
                } ?: price,
                color = getColor().black,
                style = body02
            )
        }

        Spacer(Modifier.weight(1f))

        GlideImage(
            modifier = Modifier
                .size(56.dp)
                .clip(RoundedCornerShape(8.dp))
                .clickableNoEffect { fullImageUrl = imageUrl?.originUrl },
            imageModel = { imageUrl?.thumbnailUrl }
        )
    }
}