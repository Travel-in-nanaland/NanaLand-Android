package com.nanaland

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.nanaland.globalvalue.BOTTOM_NAVIGATION_BAR_HEIGHT
import com.nanaland.globalvalue.CALENDAR_VIEW_HEIGHT
import com.nanaland.globalvalue.DAILY_BRIEF_SCHEDULE_VIEW_HEIGHT
import com.nanaland.globalvalue.SCREEN_HEIGHT_WITHOUT_SYSTEM_BAR
import com.nanaland.globalvalue.SCREEN_WIDTH
import com.nanaland.globalvalue.STATUS_BAR_HEIGHT
import com.nanaland.globalvalue.TOP_BAR_HEIGHT
import com.nanaland.ui.theme.CustomTheme
import com.nanaland.ui.theme.LocalColor
import com.nanaland.ui.theme.NanaLandTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        setContent {
            NanaLandTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                        .onGloballyPositioned {
                            updateScreenSizeValue(it.size)
                        },
                    color = CustomTheme.colorScheme.surface
                ) {
                    
                }
            }
        }
    }

    // 모든 영역의 단위는 기본적으로 pixel
    @SuppressLint("InternalInsetResource", "DiscouragedApi")
    private fun updateScreenSizeValue(size: IntSize) {
        val resources = application.resources
        val metrics = resources.displayMetrics
        val density = metrics.density
        val xdpi = metrics.xdpi
        val ydpi = metrics.ydpi
        val statusBarResourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
        val systemNavigationBarResourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android")

        // getDimension(): dimen.xml에 정의한 dp값을 기기에 맞게 px로 변환하여 반올림한 값을 int로 반환한다.
        val screenHeight = metrics.heightPixels
        val screenWidth = metrics.widthPixels
        val statusBarHeight = resources.getDimension(statusBarResourceId)
        val systemNavigationBarHeight = resources.getDimension(systemNavigationBarResourceId)
        // 상단 상태바, 시스템 네비게이션 바 제외한 화면 높이
//        GlobalValue.screenHeightWithoutStatusBar = screenHeight.toFloat()
        STATUS_BAR_HEIGHT = statusBarHeight
        SCREEN_HEIGHT_WITHOUT_SYSTEM_BAR = size.height - statusBarHeight - systemNavigationBarHeight
        SCREEN_WIDTH = screenWidth.toFloat()
        // 하단 네비게이션 바 높이는 전체 화면의 1/15
        BOTTOM_NAVIGATION_BAR_HEIGHT = SCREEN_HEIGHT_WITHOUT_SYSTEM_BAR / 15
        // 상단 영역 높이는 전체 화면의 1/15
        TOP_BAR_HEIGHT = SCREEN_HEIGHT_WITHOUT_SYSTEM_BAR / 15
        // 캘린더 뷰의 높이는 상단 상태바, 상단 영역, 하단 네비게이션 바, 시스템 네비게이션 바를 제외한 영역의 2/5
        CALENDAR_VIEW_HEIGHT = SCREEN_HEIGHT_WITHOUT_SYSTEM_BAR * 26 / 75
        // 일별 간략 정보 뷰의 높이는 상단 상태바, 상단 영역, 하단 네비게이션 바, 시스템 네비게이션 바를 제외한 영역의 3/5
        DAILY_BRIEF_SCHEDULE_VIEW_HEIGHT = SCREEN_HEIGHT_WITHOUT_SYSTEM_BAR * 39 / 75
        Log.e("ScreenValue", "statusBarHeight: $statusBarHeight ${statusBarHeight / density}\n" +
                "systemNavigationBarHeight: $systemNavigationBarHeight ${systemNavigationBarHeight / density}\n" +
                "screenHeightWithoutStatusBar: ${SCREEN_HEIGHT_WITHOUT_SYSTEM_BAR} ${SCREEN_HEIGHT_WITHOUT_SYSTEM_BAR / density}\n" +
                "screenWidth: ${SCREEN_WIDTH} ${SCREEN_WIDTH / density}\n" +
                "bottomNavBarHeight: ${BOTTOM_NAVIGATION_BAR_HEIGHT} ${BOTTOM_NAVIGATION_BAR_HEIGHT / density}\n" +
                "topBarHeight: ${TOP_BAR_HEIGHT} ${TOP_BAR_HEIGHT / density}\n" +
                "calendarViewHeight: ${CALENDAR_VIEW_HEIGHT} ${CALENDAR_VIEW_HEIGHT / density}\n" +
                "dailyScheduleViewHeight: ${DAILY_BRIEF_SCHEDULE_VIEW_HEIGHT} ${DAILY_BRIEF_SCHEDULE_VIEW_HEIGHT / density}"
        )
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NanaLandTheme {
        Greeting("Android")
    }
}