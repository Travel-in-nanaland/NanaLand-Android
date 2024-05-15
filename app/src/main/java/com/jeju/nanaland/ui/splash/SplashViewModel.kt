package com.jeju.nanaland.ui.splash

import android.util.Log
import androidx.lifecycle.ViewModel
import com.jeju.nanaland.globalvalue.type.SplashCheckingState
import com.jeju.nanaland.util.network.NetworkManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val networkManager: NetworkManager
) : ViewModel() {

    private val _checkingState = MutableStateFlow(SplashCheckingState.Network)
    val checkingState = _checkingState.asStateFlow()
    private val _isNetworkConnected = MutableStateFlow(false)
    val isNetworkConnected = _isNetworkConnected.asStateFlow()

    fun updateCheckingState(state: SplashCheckingState) {
        _checkingState.update { state }
    }

    fun checkNetworkState() {
        Log.e("checkNetworkState", "${networkManager.isNetworkConnected}")
        _isNetworkConnected.update { networkManager.isNetworkConnected }
    }

    fun checkLanguageState() {

    }
}