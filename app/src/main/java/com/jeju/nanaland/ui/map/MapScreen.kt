package com.jeju.nanaland.ui.searchInContent

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.component.common.CustomSurface
import com.jeju.nanaland.ui.component.common.dialog.BottomSheetSelectDialog
import com.jeju.nanaland.ui.component.common.topbar.TopBarCommon
import com.jeju.nanaland.ui.theme.body02
import com.jeju.nanaland.ui.theme.bodyBold
import com.jeju.nanaland.ui.theme.caption01
import com.jeju.nanaland.ui.theme.caption01SemiBold
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.ui.theme.shadowBottomNav
import com.jeju.nanaland.util.resource.getString
import com.jeju.nanaland.util.ui.clickableNoEffect
import com.kakao.vectormap.KakaoMap
import com.kakao.vectormap.KakaoMapReadyCallback
import com.kakao.vectormap.LatLng
import com.kakao.vectormap.MapLifeCycleCallback
import com.kakao.vectormap.MapView
import com.kakao.vectormap.camera.CameraUpdateFactory
import com.kakao.vectormap.label.LabelOptions
import com.kakao.vectormap.label.LabelStyle
import com.kakao.vectormap.label.LabelStyles
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreen(
    name: String,
    localLocate: String?,
    koreaLocate: String,
    lat: Double,
    lng: Double,
    moveToBackScreen: () -> Unit,
) {
    var isShowLocationApp by remember { mutableStateOf(false) }

    CustomSurface {
        Column {
            TopBarCommon(
                title = "",
                onBackButtonClicked = moveToBackScreen,
            )
            Box(Modifier.weight(1f)) {
                MapView(
                    lat = lat,
                    lng = lng,
                )
                Column(
                    Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                ) {
                    FAB(
                        Modifier
                            .align(Alignment.End)
                            .padding(horizontal = 16.dp, vertical = 12.dp)
                    ) {
                        isShowLocationApp = true
                    }
                    BottomInfo(
                        name = name,
                        localLocate = localLocate,
                        koreaLocate = koreaLocate
                    )
                }
            }
        }
    }

    if(isShowLocationApp)
        BottomSheetSelectDialog(
            onDismiss = { isShowLocationApp = false },
            items = getMapApps(koreaLocate).toTypedArray()
        )
}

@Composable
fun MapView(
    modifier: Modifier = Modifier,
    lat: Double,
    lng: Double,
) {
    val context = LocalContext.current
    val mapView = remember { MapView(context) }
    val latLng = remember { LatLng.from(lat, lng) }
    AndroidView(
        modifier = modifier.fillMaxSize(),
        factory = {
            mapView.apply {
                mapView.start(
                    object : MapLifeCycleCallback() {
                        // 지도 생명 주기 콜백: 지도가 파괴될 때 호출
                        override fun onMapDestroy() {
                            // 필자가 직접 만든 Toast생성 함수
//                            makeToast(context = it, message = "지도를 불러오는데 실패했습니다.")
                        }

                        // 지도 생명 주기 콜백: 지도 로딩 중 에러가 발생했을 때 호출
                        override fun onMapError(exception: Exception?) {
                            // 필자가 직접 만든 Toast생성 함수
//                            makeToast(context = it, message = "지도를 불러오는 중 알 수 없는 에러가 발생했습니다.\n onMapError: $exception")
                        }
                    },
                    object : KakaoMapReadyCallback() {
                        override fun onMapReady(kakaoMap: KakaoMap) {
                            kakaoMap.moveCamera(CameraUpdateFactory.newCenterPosition(latLng))

                            val marker = LabelOptions
                                .from(latLng)
                                .setStyles(
                                    LabelStyles.from(
                                        LabelStyle.from(R.drawable.ic_kakao) // Todo
                                    )
                                )

                            kakaoMap.labelManager?.layer?.addLabel(marker)
                        }

//                        override fun getPosition(): LatLng {
//                            return latLng
//                        }
                    },
                )
            }
        },
    )
}

@Composable
private fun BottomInfo(
    modifier: Modifier = Modifier,
    name: String,
    localLocate: String?,
    koreaLocate: String,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .shadowBottomNav()
            .background(
                getColor().white,
                shape = RoundedCornerShape(12.dp, 12.dp, 0.dp, 0.dp)
            )
            .padding(16.dp)
    ) {
        Text(
            text = name,
            style = bodyBold,
            color = getColor().black
        )
        if(localLocate != null && localLocate != koreaLocate)
            Text(
                text = localLocate,
                style = caption01,
                color = getColor().gray01
            )

        Spacer(Modifier.height(12.dp))
        Text(
            text = koreaLocate,
            style = body02,
            color = getColor().black
        )
    }
}

@Composable
private fun FAB(
    modifier: Modifier = Modifier,
    onClick: ()-> Unit
) {
    Row(
        modifier = modifier
            .background(
                color = getColor().white,
                shape = RoundedCornerShape(50)
            )
            .clickableNoEffect(onClick)
            .padding(vertical = 8.dp, horizontal = (13.5).dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = getString(R.string.map_other_app),
            style = caption01SemiBold,
            color = getColor().main
        )
        Spacer(Modifier.width(8.dp))
        Icon(
            modifier = Modifier.size(12.dp),
            painter = painterResource(id = R.drawable.ic_arrow_right),
            contentDescription = null,
            tint = getColor().main
        )
    }
}

@Composable
private fun getMapApps(
    koreaLocate: String
): List<Pair<String, ()->Unit>> {
    val context = LocalContext.current
    val googlePackage = "com.google.android.apps.maps"
    val kakaoPackage = "net.daum.android.map"
    val naverPackage = "com.nhn.android.nmap"


    val mapApps = remember {
        val mutableList = mutableListOf<Pair<String, ()->Unit>>()

        try {
//            context.packageManager.getPackageInfo(googlePackage, 0)

            mutableList.add(getString(R.string.map_app_google) to {
                context.startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("geo:0,0?q=$koreaLocate")
                    ).apply { setPackage(googlePackage) }
                )
            })
        } catch (e: PackageManager.NameNotFoundException) {}

        try { // todo
            context.packageManager.getPackageInfo(naverPackage, 0)

            mutableList.add(getString(R.string.map_app_naver) to {
                context.startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(
                            "nmap://search?query="
                                    + URLEncoder.encode(koreaLocate, StandardCharsets.UTF_8.toString())
                                    + "&appname=${context.packageName}"
                        )
                    ).apply { setPackage(naverPackage) }
                )
            })
        } catch (e: PackageManager.NameNotFoundException) {}

        try {
            context.packageManager.getPackageInfo(kakaoPackage, 0)

            mutableList.add(getString(R.string.map_app_kakao) to {
                context.startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("kakaomap://search?q=$koreaLocate")
                    ).apply { setPackage(kakaoPackage) }
                )
            })
        } catch (e: PackageManager.NameNotFoundException) {}

        return@remember mutableList
    }

    return mapApps
}