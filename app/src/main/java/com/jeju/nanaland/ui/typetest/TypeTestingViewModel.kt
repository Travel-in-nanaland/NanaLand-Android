package com.jeju.nanaland.ui.typetest

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeju.nanaland.domain.request.member.UpdateUserTypeRequest
import com.jeju.nanaland.domain.usecase.member.UpdateUserTypeUseCase
import com.jeju.nanaland.globalvalue.constant.TravelType
import com.jeju.nanaland.util.log.LogUtil
import com.jeju.nanaland.util.network.onError
import com.jeju.nanaland.util.network.onException
import com.jeju.nanaland.util.network.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class TypeTestingViewModel @Inject constructor(
    private val updateUserTypeUseCase: UpdateUserTypeUseCase
) : ViewModel() {
    private val _level = MutableStateFlow(1)
    val level = _level.asStateFlow()
    val selectionList = mutableStateListOf(0, 0, 0, 0, 0)

    fun updateLevel(level: Int) {
        if (level < 1 || level > 5) return
        _level.update { level }
    }

    fun updateUserType(moveToTypeTestCompletionScreen: (TravelType) -> Unit) {
        val type = when ("${selectionList[0]}${selectionList[2]}${selectionList[4]}") {
            "111" -> TravelType.GAMGYUL_ICECREAM
            "112" -> TravelType.GAMGYUL_RICECAKE
            "113" -> TravelType.GAMGYUL
            "114" -> TravelType.GAMGYUL_CIDER
            "121" -> TravelType.GAMGYUL_AFFOKATO
            "122" -> TravelType.GAMGYUL_HANGWA
            "123" -> TravelType.GAMGYUL_JUICE
            "124" -> TravelType.GAMGYUL_CHOCOLATE
            "221" -> TravelType.GAMGYUL_COCKTAIL
            "222" -> TravelType.TANGERINE_PEEL_TEA
            "223" -> TravelType.GAMGYUL_YOGURT
            "224" -> TravelType.GAMGYUL_FLATCCINO
            "211" -> TravelType.GAMGYUL_LATTE
            "212" -> TravelType.GAMGYUL_SIKHYE
            "213" -> TravelType.GAMGYUL_ADE
            else -> TravelType.GAMGYUL_BUBBLE_TEA
        }
        
        val requestData = UpdateUserTypeRequest(type = type)
        
        updateUserTypeUseCase(requestData)
            .onEach { networkResult ->  
                networkResult.onSuccess { code, message, data ->
                    moveToTypeTestCompletionScreen(type)
                }.onError { code, message ->

                }.onException {

                }
            }
            .catch { LogUtil.e("flow error", "updateUserTypeUseCase") }
            .launchIn(viewModelScope)
    }
}