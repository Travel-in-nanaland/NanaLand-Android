package com.nanaland

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntSize
import androidx.lifecycle.ViewModelProvider
import com.nanaland.globalvalue.constant.BOTTOM_NAVIGATION_BAR_HEIGHT
import com.nanaland.globalvalue.constant.SCREEN_HEIGHT_WITHOUT_SYSTEM_BAR
import com.nanaland.globalvalue.constant.SCREEN_WIDTH
import com.nanaland.globalvalue.constant.SYSTEM_STATUS_BAR_HEIGHT
import com.nanaland.globalvalue.constant.SYSTEM_NAVIGATION_BAR_HEIGHT
import com.nanaland.globalvalue.constant.TOP_BAR_HEIGHT
import com.nanaland.globalvalue.constant.TOTAL_SCREEN_HEIGHT
import com.nanaland.ui.MainNavigation
import com.nanaland.ui.theme.getColor
import com.nanaland.ui.theme.NanaLandTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainActivityViewModel by lazy {
        ViewModelProvider(this)[MainActivityViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        // 임시 액세스 토큰 저장
        viewModel.saveAccessToken("aaa")
//        viewModel.saveAccessToken("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIyIiwiYXV0aCI6IlJPTEVfTUVNQkVSIiwiZXhwIjoxNzQ1ODkwNTYzfQ.EIzWYHeyYlGlofOXpxpHOotsheqIuXSfKx1FmB2EsW4hNkMfcQ-_m8EHG6W_bgj4vBtXKRb7if4KRL2nm79Zaw")
        viewModel.saveRefreshToken("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiYXV0aCI6IlJPTEVfTUVNQkVSIiwiZXhwIjoxNzE0OTY2NjA2fQ.qe2FwQHelLL8mLZBkGXBSa74IzRiZ-TnVXbfJwtQ1PpI3BDPnugTvaE_LtsR_aUjUATG-fLZ8x_bShET9h6JVA")


        setContent {
            NanaLandTheme {
                val density = LocalDensity.current.density
                Surface(
                    color = getColor().surface,
                    contentColor = getColor().surface,
                    modifier = Modifier.fillMaxSize()
                        .onGloballyPositioned {
                            updateScreenSizeValue(it.size, density)
                        },
                ) {
                    MainNavigation()
                }
            }
        }
    }

    // 모든 영역의 단위는 기본적으로 dp.
    @SuppressLint("InternalInsetResource", "DiscouragedApi")
    private fun updateScreenSizeValue(size: IntSize, density: Float) {
        val resources = application.resources
        val metrics = resources.displayMetrics
//        metrics.densityDpi = 360
        val xdpi = metrics.xdpi
        val ydpi = metrics.ydpi
        Log.e("dpi", "metrics.density: ${metrics.density}\n" +
                "densityDpi: ${metrics.densityDpi}\n" +
                "xDpi: ${xdpi}\n" +
                "yDpi: ${ydpi}\n" +
                "LocalDensity.current.density: ${density}\n")
        val statusBarResourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
        val systemNavigationBarResourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android")

        // getDimension(): dimen.xml에 정의한 dp값을 기기에 맞게 px로 변환하여 반올림한 값을 int로 반환한다.
        val screenHeight = metrics.heightPixels
        val screenWidth = metrics.widthPixels
        val statusBarHeight = resources.getDimension(statusBarResourceId)
        val systemNavigationBarHeight = resources.getDimension(systemNavigationBarResourceId)
        // 상단 상태바, 시스템 네비게이션 바 제외한 화면 높이
//        GlobalValue.screenHeightWithoutStatusBar = screenHeight.toFloat()
        SYSTEM_STATUS_BAR_HEIGHT = statusBarHeight / density
        SYSTEM_NAVIGATION_BAR_HEIGHT = systemNavigationBarHeight / density
        TOTAL_SCREEN_HEIGHT = size.height / density
        SCREEN_HEIGHT_WITHOUT_SYSTEM_BAR = (size.height - statusBarHeight - systemNavigationBarHeight) / density
        SCREEN_WIDTH = (screenWidth.toFloat()) / density
        // 하단 네비게이션 바 높이
//        BOTTOM_NAVIGATION_BAR_HEIGHT = (SCREEN_HEIGHT_WITHOUT_SYSTEM_BAR / 10) / density
        BOTTOM_NAVIGATION_BAR_HEIGHT = 56f
        // 상단 영역 높이
//        TOP_BAR_HEIGHT = (SCREEN_HEIGHT_WITHOUT_SYSTEM_BAR / 10) / density
        TOP_BAR_HEIGHT = 56f
        Log.e("ScreenValue", "totalScreenHeight: $TOTAL_SCREEN_HEIGHT\n" +
                "statusBarHeight: $SYSTEM_STATUS_BAR_HEIGHT ${statusBarHeight}\n" +
                "systemNavigationBarHeight: $SYSTEM_NAVIGATION_BAR_HEIGHT ${systemNavigationBarHeight}\n" +
                "screenHeightWithoutSystemBar: $SCREEN_HEIGHT_WITHOUT_SYSTEM_BAR ${SCREEN_HEIGHT_WITHOUT_SYSTEM_BAR}\n" +
                "screenWidth: $SCREEN_WIDTH ${SCREEN_WIDTH}\n" +
                "bottomNavBarHeight: $BOTTOM_NAVIGATION_BAR_HEIGHT ${BOTTOM_NAVIGATION_BAR_HEIGHT}\n" +
                "topBarHeight: $TOP_BAR_HEIGHT ${TOP_BAR_HEIGHT}\n"
        )
    }
}