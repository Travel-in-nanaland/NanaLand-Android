package com.nanaland

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nanaland.domain.usecase.authdatastore.GetAccessTokenUseCase
import com.nanaland.domain.usecase.authdatastore.GetRefreshTokenUseCase
import com.nanaland.domain.usecase.authdatastore.SaveAccessTokenUseCase
import com.nanaland.domain.usecase.authdatastore.SaveRefreshTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val saveAccessTokenUseCase: SaveAccessTokenUseCase,
    private val saveRefreshTokenUseCase: SaveRefreshTokenUseCase,
    private val getAccessTokenUseCase: GetAccessTokenUseCase,
    private val getRefreshTokenUseCase: GetRefreshTokenUseCase
) : ViewModel() {

    fun saveAccessToken(token: String) {
        viewModelScope.launch {
            saveAccessTokenUseCase(token)
            Log.e("accessTokenInit", "${getAccessTokenUseCase().first()}")
        }
    }

    fun saveRefreshToken(token: String) {
        viewModelScope.launch {
            saveRefreshTokenUseCase(token)
            Log.e("refreshTokenInit", "${getRefreshTokenUseCase().first()}")
        }
    }
}