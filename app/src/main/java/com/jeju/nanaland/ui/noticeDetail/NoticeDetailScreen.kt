package com.jeju.nanaland.ui.noticeDetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jeju.nanaland.ui.component.common.CustomSurface
import com.jeju.nanaland.ui.component.common.topbar.TopBarCommon
import com.jeju.nanaland.ui.theme.body02
import com.jeju.nanaland.ui.theme.caption01
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.ui.theme.title02Bold
import com.jeju.nanaland.util.ui.UiState
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun NoticeDetailScreen(
    moveToBackScreen: () -> Unit,
    viewModel: BoardViewModel = hiltViewModel()
) {
    val data = viewModel.noticeData.collectAsStateWithLifecycle()
    CustomSurface {
        TopBarCommon(
            title = "",
            onBackButtonClicked = moveToBackScreen
        )
        (data.value as? UiState.Loading)?.let { }
        (data.value as? UiState.Failure)?.let { }
        (data.value as? UiState.Success)?.let {

            Column(
                modifier = Modifier.verticalScroll(rememberScrollState())
            ) {
                Column(
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {

                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        text = it.data.title,
                        style = title02Bold,
                        color = getColor().black
                    )

                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = it.data.createdAt.replace('-','.'),
                        style = caption01,
                        color = getColor().black
                    )
                }

                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 16.dp),
                    thickness = (0.5).dp
                )

                Column(
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    it.data.noticeContents.forEach { content ->
                        content.image?.let { ImgRow(url = it.originUrl) }
                        content.content?.let { TxtRow(txt = it) }
                    }
                }
            }
        }
    }
}

@Composable
private fun ImgRow(url: String) {
    GlideImage(
        modifier = Modifier
            .padding(bottom = 16.dp)
            .fillMaxWidth()
            .aspectRatio(1f)
            .clip(RoundedCornerShape(8.dp)),
        imageModel = { url },
    )
}
@Composable
private fun TxtRow(txt: String) {
    Text(
        modifier = Modifier.padding(bottom = 16.dp),
        text = txt,
        style = body02,
        color = getColor().black
    )
}