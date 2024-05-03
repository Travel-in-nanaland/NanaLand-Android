package com.nanaland.ui.component.thumbnail


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nanaland.ui.component.thumbnail.parts.ThumbnailImage
import com.nanaland.ui.component.thumbnail.parts.ThumbnailSubtitle
import com.nanaland.ui.component.thumbnail.parts.ThumbnailTitle
import com.nanaland.ui.theme.NanaLandTheme
import com.nanaland.util.ui.ComponentPreview
import com.nanaland.util.ui.clickableNoEffect

@Composable
fun MainHomeThumbnail(
    imageUri: String?,
    title: String?,
    subTitle: String?,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .width(160.dp)
            .wrapContentHeight()
            .clickableNoEffect { onClick() }
    ) {
        ThumbnailImage(imageUri = imageUri)

        Spacer(Modifier.height(8.dp))

        ThumbnailTitle(text = title)

        Spacer(Modifier.height(4.dp))

        ThumbnailSubtitle(text = subTitle)
    }
}

@ComponentPreview
@Composable
private fun MainHomeThumbnailPreview1() {
    NanaLandTheme {
        MainHomeThumbnail(
            imageUri = "",
            title = "title",
            subTitle = "subTitle",
            onClick = {}
        )
    }
}

@ComponentPreview
@Composable
private fun MainHomeThumbnailPreview2() {
    NanaLandTheme {
        MainHomeThumbnail(
            imageUri = "",
            title = "title title title title title title title",
            subTitle = "subTitle",
            onClick = {}
        )
    }
}