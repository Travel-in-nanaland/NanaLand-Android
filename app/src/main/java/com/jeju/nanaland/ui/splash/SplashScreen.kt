package com.jeju.nanaland.ui.splash

import android.app.Activity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.jeju.nanaland.R
import com.jeju.nanaland.globalvalue.type.SplashCheckingState
import com.jeju.nanaland.ui.splash.component.NetworkConnectionDialog
import com.jeju.nanaland.util.intent.DeepLinkData
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    deepLinkData: DeepLinkData,
    moveToMainScreen: () -> Unit,
    moveToLanguageInitScreen: () -> Unit,
    moveToSignInScreen: () -> Unit,
    viewModel: SplashViewModel = hiltViewModel()
) {
    val checkingState = viewModel.checkingState.collectAsState().value
    val isNetworkConnected = viewModel.isNetworkConnected.collectAsState().value
    val isNetworkConnectionDialogShowing = viewModel.isNetworkConnectionDialogShowing.collectAsState().value
    SplashScreen(
        checkingState = checkingState,
        isNetworkConnected = isNetworkConnected,
        isNetworkConnectionDialogShowing = isNetworkConnectionDialogShowing,
        checkNetworkState = viewModel::checkNetworkState,
        checkLanguageState = {
            viewModel.checkLanguageState(
                deepLinkData,
                it
            )
        },
        checkSignInState = viewModel::checkSignInState,
        moveToMainScreen = moveToMainScreen,
        moveToLanguageInitScreen = moveToLanguageInitScreen,
        moveToSignInScreen = moveToSignInScreen,
        isContent = true
    )
}

@Composable
private fun SplashScreen(
    checkingState: SplashCheckingState,
    isNetworkConnected: Boolean,
    isNetworkConnectionDialogShowing: Boolean,
    checkNetworkState: () -> Unit,
    checkLanguageState: (() -> Unit) -> Unit,
    checkSignInState: (() -> Unit, () -> Unit) -> Unit,
    moveToMainScreen: () -> Unit,
    moveToLanguageInitScreen: () -> Unit,
    moveToSignInScreen: () -> Unit,
    isContent: Boolean
) {
    val context = LocalContext.current
    val systemUiController = rememberSystemUiController()
    val fcmToken = remember { mutableStateOf("") }
    LaunchedEffect(checkingState) {
//        FirebaseMessaging.getInstance().token.addOnCompleteListener(
//            OnCompleteListener { task ->
//                if (!task.isSuccessful) {
//                    Log.e("FCM-token", "Fetching FCM registration token failed", task.exception)
//                    return@OnCompleteListener
//                }
//
//                val token = task.result
//                Log.e("FCM-token", token)
//                fcmToken.value = token
//
//                val msg = token.toString()
////                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
//            }
//        )
        when (checkingState) {
            SplashCheckingState.Network -> {
                systemUiController.setStatusBarColor(
                    color = Color(0x00000000),
                    darkIcons = false
                )
                systemUiController.setNavigationBarColor(
                    color = Color(0x00000000),
                    darkIcons = false
                )

                delay(1500)

                systemUiController.setStatusBarColor(
                    color = Color(0x00000000),
                    darkIcons = true
                )
                systemUiController.setNavigationBarColor(
                    color = Color(0x00000000),
                    darkIcons = true
                )
                checkNetworkState()
//                moveToMainScreen()
            }
            SplashCheckingState.Language -> {
                checkLanguageState(moveToLanguageInitScreen)
            }
            SplashCheckingState.Authorization -> {
                checkSignInState(moveToMainScreen, moveToSignInScreen)
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        val composition by rememberLottieComposition(
            LottieCompositionSpec.RawRes(R.raw.splash)
        )
        val progress by animateLottieCompositionAsState(composition)
//        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.splash1))
//        LottieAnimation(composition)
        LottieAnimation(
            modifier = Modifier.fillMaxSize(),
            composition = composition,
            progress = { progress },
            contentScale = ContentScale.Crop
        )
//        TextField(
//            modifier = Modifier.padding(top = 40.dp),
//            value = fcmToken.value,
//            onValueChange = {}
//        )
    }
    if (isNetworkConnectionDialogShowing) {
        NetworkConnectionDialog(
            exit = { (context as Activity).finish() },
            retry = {
                checkNetworkState()
            }
        )
    }
}

@Preview
@Composable
private fun SplashScreenPreview() {

}