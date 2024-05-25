package com.jeju.nanaland.ui.typetest

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeju.nanaland.domain.request.member.UpdateUserTypeRequest
import com.jeju.nanaland.domain.usecase.member.UpdateUserTypeUseCase
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

    fun updateUserType(moveToTypeTestCompletionScreen: () -> Unit) {
        val type = when ("${selectionList[0]}${selectionList[2]}${selectionList[4]}") {
            "111" -> "GAMGYUL_ICECREAM"
            "112" -> "GAMGYUL_RICECAKE"
            "113" -> "GAMGYUL"
            "114" -> "GAMGYUL_CIDER"
            "121" -> "GAMGYUL_AFFOKATO"
            "122" -> "GAMGYUL_HANGWA"
            "123" -> "GAMGYUL_JUICE"
            "124" -> "GAMGYUL_CHOCOLATE"
            "211" -> "GAMGYUL_COCKTAIL"
            "212" -> "TANGERINE_PEEL_TEA"
            "213" -> "GAMGYUL_YOGURT"
            "214" -> "GAMGYUL_FLATCCINO"
            "221" -> "GAMGYUL_LATTE"
            "222" -> "GAMGYUL_SIKHYE"
            "223" -> "GAMGYUL_ADE"
            else -> "GAMGYUL_BUBBLE_TEA"
        }
        
        val requestData = UpdateUserTypeRequest(type = type)
        
        updateUserTypeUseCase(requestData)
            .onEach { networkResult ->  
                networkResult.onSuccess { code, data ->
                    moveToTypeTestCompletionScreen()
                }.onError { code, message ->

                }.onException {

                }
            }
            .catch { LogUtil.e("flow error", "updateUserTypeUseCase") }
            .launchIn(viewModelScope)
    }
}