package com.nanaland.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.nanaland.ui.theme.getColor
import com.nanaland.ui.theme.body02Bold
import com.nanaland.ui.theme.caption01
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun HomeContentCard() {
    HomeContentCard(
        isContent = true
    )
}

@Composable
private fun HomeContentCard(
    isContent: Boolean
) {
    Column(
        modifier = Modifier.width(160.dp)
    ) {
        GlideImage(
            modifier = Modifier
                .width(160.dp)
                .height(120.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(getColor().skyblue),
            imageModel = {  }
        )
        Spacer(Modifier.height(10.dp))
        Text(
            text = "무사 제주도 찐-맛집 안먹언?",
            color = getColor().black,
            style = body02Bold
        )
        Spacer(Modifier.height(10.dp))
        Text(
            text = "도민들의 로컬 맛집들, 그런거는 SNS에 엇나!",
            color = getColor().gray01,
            style = caption01
        )
    }
}

@Preview
@Composable
private fun HomeContentCardPreview() {

}