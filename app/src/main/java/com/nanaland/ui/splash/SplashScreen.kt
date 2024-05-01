package com.nanaland.ui.splash

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.nanaland.globalvalue.type.SplashCheckingState
import com.nanaland.ui.theme.getColor
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    moveToMainScreen: () -> Unit,
    viewModel: SplashViewModel = hiltViewModel()
) {
    val checkingState = viewModel.checkingState.collectAsState().value
    val isNetworkConnected = viewModel.isNetworkConnected.collectAsState().value
    SplashScreen(
        checkingState = checkingState,
        isNetworkConnected = isNetworkConnected,
        checkNetworkState = viewModel::checkNetworkState,
        moveToMainScreen = moveToMainScreen,
        isContent = true
    )
}

@Composable
private fun SplashScreen(
    checkingState: SplashCheckingState,
    isNetworkConnected: Boolean,
    checkNetworkState: () -> Unit,
    moveToMainScreen: () -> Unit,
    isContent: Boolean
) {
    LaunchedEffect(checkingState) {
        when (checkingState) {
            SplashCheckingState.Network -> {
                checkNetworkState()
                delay(1000)
                moveToMainScreen()
            }
            SplashCheckingState.Language -> {

            }
            SplashCheckingState.Authorization -> {

            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable {
                moveToMainScreen()
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "스플래시 화면",
            color = getColor().black
        )
    }
}

@Preview
@Composable
private fun SplashScreenPreview() {

}