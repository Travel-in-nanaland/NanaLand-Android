package com.jeju.nanaland.ui.component.common.dialog

import androidx.annotation.RawRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.theme.NanaLandTheme
import com.jeju.nanaland.ui.theme.body01
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.resource.getString

/**
 * 로딩 다이얼로그
 *
 * API 요청 시 오래 걸리며, 동기화가 필요한 작업(사진이 들어갈 수 있는 리뷰 작성 등)에 사용
 *
 * @param isVisible 다이얼로그를 보여주는지 여부
 * @param onCancelByBackPress `null` 취소 불가, `not null` 취소 가능하며
 * back key 사용 시 호출
 * @param loadingGIF 상단의 애니메이션 파일 리소스
 * @param loadingGifSize 상단의 애니메이션 사이즈
 * @param waitText 첫 줄에 들어갈 공통 글
 * @param descText 다음 줄에 들어갈 글, `null` 랜덤
 * @param textStyle 설명란의 텍스트 스타일
 * @param textColor 설명란의 텍스트 컬러
 */
@Composable
fun SubmitLoadingDialog(
    isVisible: Boolean,
    onCancelByBackPress: (() -> Unit)? = null,
    @RawRes loadingGIF: Int = R.raw.loading_for_submit,
    loadingGifSize: DpSize = DpSize(168.dp, 140.dp),
    waitText: String = getString(R.string.loading_wait_text),
    descText: String? = null,
    textStyle: TextStyle = body01,
    textColor: Color = getColor().white,
) {
    if(!isVisible) return

    val descRandomText = remember(descText) {
        getString(
            listOf(
                R.string.loading_wait_text_desc1,
                R.string.loading_wait_text_desc2,
                R.string.loading_wait_text_desc3,
                R.string.loading_wait_text_desc4
            ).random()
        )
    }
    val gif by rememberLottieComposition(LottieCompositionSpec.RawRes(loadingGIF))

    Dialog(
        onDismissRequest = { onCancelByBackPress?.let { it() } },
        properties = DialogProperties(
            dismissOnClickOutside = false,
            dismissOnBackPress = onCancelByBackPress != null
        )
    ) {
        NanaLandTheme {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LottieAnimation(
                    modifier = Modifier.size(loadingGifSize),
                    composition = gif,
                    iterations = LottieConstants.IterateForever
                )
                Text(
                    text =  waitText,
                    style = textStyle,
                    color = textColor
                )
                Text(
                    text =  descText ?: descRandomText,
                    style = textStyle,
                    color = textColor
                )
            }
        }
    }
}
