package com.jeju.nanaland.ui.market

import android.content.Intent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jeju.nanaland.R
import com.jeju.nanaland.domain.entity.market.MarketContent
import com.jeju.nanaland.ui.component.common.CustomSurface
import com.jeju.nanaland.ui.component.common.topbar.CustomTopBar
import com.jeju.nanaland.ui.component.detailscreen.other.DetailScreenDescription
import com.jeju.nanaland.ui.component.detailscreen.other.DetailScreenInformation
import com.jeju.nanaland.ui.component.detailscreen.other.DetailScreenInformationModificationProposalButton
import com.jeju.nanaland.ui.component.detailscreen.other.DetailScreenTopBannerImage
import com.jeju.nanaland.ui.component.detailscreen.other.MoveToTopButton
import com.jeju.nanaland.util.language.getLanguage
import com.jeju.nanaland.util.resource.getString
import com.jeju.nanaland.util.ui.ScreenPreview
import com.jeju.nanaland.util.ui.UiState
import kotlinx.coroutines.launch

@Composable
fun MarketContentScreen(
    contentId: Int?,
    isSearch: Boolean,
    updatePrevScreenListFavorite: (Int, Boolean) -> Unit,
    moveToBackScreen: () -> Unit,
    moveToInfoModificationProposalScreen: () -> Unit,
    moveToSignInScreen: () -> Unit,
    viewModel: MarketContentViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.getMarketContent(contentId, isSearch)
    }
    val marketContent = viewModel.marketContent.collectAsState().value
    MarketContentScreen(
        contentId = contentId,
        marketContent = marketContent,
        toggleFavorite = viewModel::toggleFavorite,
        updatePrevScreenListFavorite = updatePrevScreenListFavorite,
        moveToBackScreen = moveToBackScreen,
        moveToInfoModificationProposalScreen = moveToInfoModificationProposalScreen,
        moveToSignInScreen = moveToSignInScreen,
        isContent = true
    )
}

@Composable
private fun MarketContentScreen(
    contentId: Int?,
    marketContent: UiState<MarketContent>,
    toggleFavorite: (Int, (Int, Boolean) -> Unit) -> Unit,
    updatePrevScreenListFavorite: (Int, Boolean) -> Unit,
    moveToBackScreen: () -> Unit,
    moveToInfoModificationProposalScreen: () -> Unit,
    moveToSignInScreen: () -> Unit,
    isContent: Boolean
) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()
    CustomSurface {
        CustomTopBar(
            title = getString(R.string.common_전통시장),
            onBackButtonClicked = moveToBackScreen
        )
        when (marketContent) {
            is UiState.Loading -> {}
            is UiState.Success -> {
                Box(Modifier.fillMaxSize()) {
                    Column(Modifier.verticalScroll(scrollState)) {
                        DetailScreenTopBannerImage(imageUri = marketContent.data.images[0]?.originUrl)

                        Spacer(Modifier.height(24.dp))

                        Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
                            DetailScreenDescription(
                                isFavorite = marketContent.data.favorite,
                                tag = marketContent.data.addressTag,
                                title = marketContent.data.title,
                                content = marketContent.data.content,
                                onFavoriteButtonClicked = { toggleFavorite(marketContent.data.id, updatePrevScreenListFavorite) },
                                onShareButtonClicked = {
                                    val sendIntent: Intent = Intent().apply {
                                        action = Intent.ACTION_SEND
                                        putExtra(Intent.EXTRA_TEXT, "http://13.125.110.80:8080/share/${getLanguage()}?category=market&id=${contentId}")
                                        type = "text/plain"
                                    }
                                    val shareIntent = Intent.createChooser(sendIntent, null)
                                    context.startActivity(shareIntent)
//                                    val defaultText = TextTemplate(
//                                    text = """
//                                        나나랜드 콘텐츠 공유.
//                                        """.trimIndent(),
//                                        buttonTitle = "커스텀 버튼 제목",
//                                        link = Link(
//                                            webUrl = "nanaland://main",
//                                            mobileWebUrl = "nanaland://main"
//                                        )
//                                    )
//                                    // 피드 메시지 보내기
//
//                                    // 카카오톡 설치여부 확인
//                                    if (ShareClient.instance.isKakaoTalkSharingAvailable(context)) {
//                                        // 카카오톡으로 카카오톡 공유 가능
//                                        ShareClient.instance.shareDefault(context, defaultText) { sharingResult, error ->
//                                            if (error != null) {
//                                                LogUtil.e("kakaoShare", "카카오톡 공유 실패")
//                                            }
//                                            else if (sharingResult != null) {
//                                                Log.d("kakaoShare", "카카오톡 공유 성공 ${sharingResult.intent}")
//                                                context.startActivity(sharingResult.intent)
//
//                                                // 카카오톡 공유에 성공했지만 아래 경고 메시지가 존재할 경우 일부 컨텐츠가 정상 동작하지 않을 수 있습니다.
//                                                Log.w("kakaoShare", "Warning Msg: ${sharingResult.warningMsg}")
//                                                Log.w("kakaoShare", "Argument Msg: ${sharingResult.argumentMsg}")
//                                            }
//                                        }
//                                    } else {
//                                        // 카카오톡 미설치: 웹 공유 사용 권장
//                                        // 웹 공유 예시 코드
//                                        val sharerUrl = WebSharerClient.instance.makeDefaultUrl(defaultText)
//
//                                        // CustomTabs으로 웹 브라우저 열기
//
//                                        // 1. CustomTabsServiceConnection 지원 브라우저 열기
//                                        // ex) Chrome, 삼성 인터넷, FireFox, 웨일 등
//                                        try {
//                                            KakaoCustomTabsClient.openWithDefault(context, sharerUrl)
//                                        } catch(e: UnsupportedOperationException) {
//                                            // CustomTabsServiceConnection 지원 브라우저가 없을 때 예외처리
//                                        }
//
//                                        // 2. CustomTabsServiceConnection 미지원 브라우저 열기
//                                        // ex) 다음, 네이버 등
//                                        try {
//                                            KakaoCustomTabsClient.open(context, sharerUrl)
//                                        } catch (e: ActivityNotFoundException) {
//                                            // 디바이스에 설치된 인터넷 브라우저가 없을 때 예외처리
//                                        }
//                                    }
                                },
                                moveToSignInScreen = moveToSignInScreen,
                            )

                            Spacer(Modifier.height(32.dp))

                            if (!marketContent.data.address.isNullOrEmpty()) {
                                DetailScreenInformation(
                                    drawableId = R.drawable.ic_location_outlined,
                                    title = getString(R.string.detail_screen_common_주소),
                                    content = marketContent.data.address
                                )

                                Spacer(Modifier.height(24.dp))
                            }

                            if (!marketContent.data.contact.isNullOrEmpty()) {
                                DetailScreenInformation(
                                    drawableId = R.drawable.ic_phone_outlined,
                                    title = getString(R.string.detail_screen_common_연락처),
                                    content = marketContent.data.contact
                                )

                                Spacer(Modifier.height(24.dp))
                            }

                            if (!marketContent.data.time.isNullOrEmpty()) {
                                DetailScreenInformation(
                                    drawableId = R.drawable.ic_clock_outlined,
                                    title = getString(R.string.detail_screen_common_이용_시간),
                                    content = marketContent.data.time
                                )

                                Spacer(Modifier.height(24.dp))
                            }

                            if (!marketContent.data.amenity.isNullOrEmpty()) {
                                DetailScreenInformation(
                                    drawableId = R.drawable.ic_amenity_outlined,
                                    title = getString(R.string.detail_screen_common_편의시설),
                                    content = marketContent.data.amenity
                                )

                                Spacer(Modifier.height(24.dp))
                            }

                            if (!marketContent.data.homepage.isNullOrEmpty()) {
                                DetailScreenInformation(
                                    drawableId = R.drawable.ic_clip_outlined,
                                    title = getString(R.string.detail_screen_common_홈페이지),
                                    content = marketContent.data.homepage
                                )

                                Spacer(Modifier.height(24.dp))
                            }

                            Spacer(Modifier.height(16.dp))

                            DetailScreenInformationModificationProposalButton {
                                moveToInfoModificationProposalScreen()
                            }
                        }

                        Spacer(Modifier.height(80.dp))
                    }

                    MoveToTopButton {
                        coroutineScope.launch { scrollState.animateScrollTo(0) }
                    }
                }
            }
            is UiState.Failure -> {}
        }
    }
}

@ScreenPreview
@Composable
private fun MarketContentScreenPreview() {

}