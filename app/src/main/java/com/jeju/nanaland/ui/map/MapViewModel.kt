package com.jeju.nanaland.ui.map

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.jeju.nanaland.BuildConfig
import com.jeju.nanaland.domain.navigation.ROUTE
import com.jeju.nanaland.domain.usecase.review.GetKrAddressUseCase
import com.jeju.nanaland.globalvalue.type.LanguageType
import com.jeju.nanaland.util.language.getLanguage
import com.jeju.nanaland.util.network.NetworkResult
import com.kakao.vectormap.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val getKrAddressUseCase: GetKrAddressUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {


    private val _latLng:MutableStateFlow<LatLng?> = MutableStateFlow(null)
    val latLng = _latLng.asStateFlow()

    private val _krAddress = MutableStateFlow("")
    val krAddress = _krAddress.asStateFlow()

    private val _onError = MutableStateFlow(false)
    val onError = _onError.asStateFlow()

    init {
        val stateHandle: ROUTE.Content.Map = savedStateHandle.toRoute()
        viewModelScope.launch {
            val address =  if(getLanguage() == LanguageType.Korean)
                stateHandle.localLocate
            else (getKrAddressUseCase(
                stateHandle.id,
                stateHandle.category,
                stateHandle.number
            ) as? NetworkResult.Success)?.data

            if(address.isNullOrBlank()) {
                _onError.value = true
                return@launch
            }

            _krAddress.update { address }
            setLatLng(address)
        }
    }

    private fun setLatLng(address: String) {
        val url = "https://dapi.kakao.com/v2/local/search/address.json?query=$address"
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val request = Request.Builder()
                    .url(url)
                    .addHeader("Authorization", "KakaoAK "+BuildConfig.KAKAO_REST_KEY)
                    .build()
                val response = OkHttpClient().newCall(request).execute()
                val result = response.body?.string()
                if (response.isSuccessful) {
                    val addressJS = JSONObject(result ?: "{}")
                        .getJSONArray("documents")
                        .optJSONObject(0)
                        ?.getJSONObject("address")!!

                    val (x, y) = Pair(addressJS.getDouble("x"), addressJS.getDouble("y"))
                    _latLng.update { LatLng.from(y, x) }
                } else {
                    throw Exception(result)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _onError.value = true
            }
        }
    }
}