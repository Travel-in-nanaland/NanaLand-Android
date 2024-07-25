package com.jeju.nanaland.ui.typetest

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeju.nanaland.domain.request.member.UpdateUserTypeRequest
import com.jeju.nanaland.domain.usecase.member.UpdateUserTypeUseCase
import com.jeju.nanaland.globalvalue.constant.TYPE_GAMGYUL
import com.jeju.nanaland.globalvalue.constant.TYPE_GAMGYUL_ADE
import com.jeju.nanaland.globalvalue.constant.TYPE_GAMGYUL_AFFOKATO
import com.jeju.nanaland.globalvalue.constant.TYPE_GAMGYUL_BUBBLE_TEA
import com.jeju.nanaland.globalvalue.constant.TYPE_GAMGYUL_CHOCOLATE
import com.jeju.nanaland.globalvalue.constant.TYPE_GAMGYUL_CIDER
import com.jeju.nanaland.globalvalue.constant.TYPE_GAMGYUL_COCKTAIL
import com.jeju.nanaland.globalvalue.constant.TYPE_GAMGYUL_FLATCCINO
import com.jeju.nanaland.globalvalue.constant.TYPE_GAMGYUL_HANGWA
import com.jeju.nanaland.globalvalue.constant.TYPE_GAMGYUL_ICECREAM
import com.jeju.nanaland.globalvalue.constant.TYPE_GAMGYUL_JUICE
import com.jeju.nanaland.globalvalue.constant.TYPE_GAMGYUL_LATTE
import com.jeju.nanaland.globalvalue.constant.TYPE_GAMGYUL_RICECAKE
import com.jeju.nanaland.globalvalue.constant.TYPE_GAMGYUL_SIKHYE
import com.jeju.nanaland.globalvalue.constant.TYPE_GAMGYUL_YOGURT
import com.jeju.nanaland.globalvalue.constant.TYPE_TANGERINE_PEEL_TEA
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

    fun updateUserType(moveToTypeTestCompletionScreen: (String) -> Unit) {
        val type = when ("${selectionList[0]}${selectionList[2]}${selectionList[4]}") {
            "111" -> TYPE_GAMGYUL_ICECREAM
            "112" -> TYPE_GAMGYUL_RICECAKE
            "113" -> TYPE_GAMGYUL
            "114" -> TYPE_GAMGYUL_CIDER
            "121" -> TYPE_GAMGYUL_AFFOKATO
            "122" -> TYPE_GAMGYUL_HANGWA
            "123" -> TYPE_GAMGYUL_JUICE
            "124" -> TYPE_GAMGYUL_CHOCOLATE
            "221" -> TYPE_GAMGYUL_COCKTAIL
            "222" -> TYPE_TANGERINE_PEEL_TEA
            "223" -> TYPE_GAMGYUL_YOGURT
            "224" -> TYPE_GAMGYUL_FLATCCINO
            "211" -> TYPE_GAMGYUL_LATTE
            "212" -> TYPE_GAMGYUL_SIKHYE
            "213" -> TYPE_GAMGYUL_ADE
            else -> TYPE_GAMGYUL_BUBBLE_TEA
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