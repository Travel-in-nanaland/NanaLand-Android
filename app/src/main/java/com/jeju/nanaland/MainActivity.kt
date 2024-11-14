package com.jeju.nanaland

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntSize
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.jeju.nanaland.domain.navigation.NavViewModel
import com.jeju.nanaland.domain.navigation.Navigator
import com.jeju.nanaland.globalvalue.constant.BOTTOM_NAVIGATION_BAR_HEIGHT
import com.jeju.nanaland.globalvalue.constant.SCREEN_HEIGHT_WITHOUT_SYSTEM_BAR
import com.jeju.nanaland.globalvalue.constant.SCREEN_WIDTH
import com.jeju.nanaland.globalvalue.constant.SYSTEM_NAVIGATION_BAR_HEIGHT
import com.jeju.nanaland.globalvalue.constant.SYSTEM_STATUS_BAR_HEIGHT
import com.jeju.nanaland.globalvalue.constant.TOP_BAR_HEIGHT
import com.jeju.nanaland.globalvalue.constant.TOTAL_SCREEN_HEIGHT
import com.jeju.nanaland.ui.MainNavigation
import com.jeju.nanaland.ui.theme.NanaLandTheme
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.intent.DeepLinkData
import com.jeju.nanaland.util.log.LogUtil
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject lateinit var navHelper: Navigator
    private val navViewModel : NavViewModel by viewModels()


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

        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)

        val deepLinkData = DeepLinkData()
        LogUtil.e("deepLink", "${intent.data}")
        if (Intent.ACTION_VIEW == intent.action) {
            val data = intent.data
            if (data.toString().contains("nanaland")) {
                LogUtil.e("deepLink", "${data}")
                val category = data?.getQueryParameter("category") ?: ""
                LogUtil.e("deepLink", "${category}")
                val id = data?.getQueryParameter("id") ?: ""
                LogUtil.e("deepLink", "${id}")
                val language = data?.getQueryParameter("lang") ?: ""
                LogUtil.e("deepLink", "${language}")
                deepLinkData.language = language
                deepLinkData.category = category
                deepLinkData.contentId = id.toInt()
            }
        }
//
//        val keyHash = Utility.getKeyHash(this)
//        Toast.makeText(this, keyHash, Toast.LENGTH_LONG).show()
//        LogUtil.e("keyHash", keyHash)

        // 개발용 액세스 토큰
//        viewModel.saveAccessToken("aa")
//        viewModel.saveAccessToken("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxMiIsImF1dGgiOiJST0xFX01FTUJFUiIsImV4cCI6MTc0NjYyODIyNH0.DUvVBtqjb432nT8M0IScjJTJD-GqR3G-srfNHFgfWUir2gtnxokb5JWQ8xONzat9HcciCqQ7OIF7t0D5pCVrTg")
        // 공유용 액세스 토큰
//        viewModel.saveAccessToken("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxMyIsImF1dGgiOiJST0xFX01FTUJFUiIsImV4cCI6MTc0NjYyODQ2N30.F5eYloUAFMrSNTIg2Vz0z1SLhomQDN791ZHIxoYmEf8sR2gYPjzuyea8g8D0Ur8NEKAHijfvM9PML-jUIBCLTg")

//        viewModel.saveAccessToken("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIyIiwiYXV0aCI6IlJPTEVfTUVNQkVSIiwiZXhwIjoxNzQ1ODkwNTYzfQ.EIzWYHeyYlGlofOXpxpHOotsheqIuXSfKx1FmB2EsW4hNkMfcQ-_m8EHG6W_bgj4vBtXKRb7if4KRL2nm79Zaw")
        // 개발용 refresh token
//        viewModel.saveRefreshToken("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxMiIsImF1dGgiOiJST0xFX01FTUJFUiIsImV4cCI6MTcxNTg2NzA4OH0.W2BAeUJqN3mUtFxqqUOTTmbbLoYIxroMWnY9lk9n0cVx21KerUG0zfvSQaDWdG8rGRlWTdGG8XxmcjNHxMkC9Q")
        // 공유용 토큰
//        viewModel.saveRefreshToken("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI2IiwiYXV0aCI6IlJPTEVfTUVNQkVSIiwiZXhwIjoxNzE1MzU1MDQ3fQ.0qaNAfkEohRBwOAUjiv-3Ob2-tfOUL_XhaVKeFcpp3qQxyVPlbTRz8Q0hzruHNRO5b-p3RymfQxc3CRw24Hvaw")

        setContent {
            val navController: NavHostController = rememberNavController()
            LaunchedEffect(key1 = true) {
                lifecycleScope.launch {
                    repeatOnLifecycle(Lifecycle.State.STARTED) {
                        navHelper.navAction.collect {
                            navHelper.runnableNavigate(navController, it)
                        }
                    }
                }
            }

            NanaLandTheme {
                val density = LocalDensity.current.density
                Surface(
                    color = getColor().surface,
                    contentColor = getColor().surface,
                    modifier = Modifier
                        .fillMaxSize()
                        .onGloballyPositioned {
                            updateScreenSizeValue(it.size, density)
                        },
                ) {
                    MainNavigation(
                        deepLinkData = deepLinkData,
                        navController = navController,
                        navViewModel = navViewModel
                    )
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
        LogUtil.e("dpi", "metrics.density: ${metrics.density}\n" +
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
        LogUtil.e("ScreenValue", "totalScreenHeight: $TOTAL_SCREEN_HEIGHT\n" +
                "statusBarHeight: $SYSTEM_STATUS_BAR_HEIGHT ${statusBarHeight}\n" +
                "systemNavigationBarHeight: $SYSTEM_NAVIGATION_BAR_HEIGHT ${systemNavigationBarHeight}\n" +
                "screenHeightWithoutSystemBar: $SCREEN_HEIGHT_WITHOUT_SYSTEM_BAR ${SCREEN_HEIGHT_WITHOUT_SYSTEM_BAR}\n" +
                "screenWidth: $SCREEN_WIDTH ${SCREEN_WIDTH}\n" +
                "bottomNavBarHeight: $BOTTOM_NAVIGATION_BAR_HEIGHT ${BOTTOM_NAVIGATION_BAR_HEIGHT}\n" +
                "topBarHeight: $TOP_BAR_HEIGHT ${TOP_BAR_HEIGHT}\n"
        )
    }

    private var backPressedTime = 0L

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            if (System.currentTimeMillis() - backPressedTime <= 2000) {
                finish()
            } else {
                backPressedTime = System.currentTimeMillis()
                Toast.makeText(this@MainActivity, com.jeju.nanaland.util.resource.getString(R.string.toast_one_more_exit), Toast.LENGTH_SHORT).show()
            }
        }
    }
}